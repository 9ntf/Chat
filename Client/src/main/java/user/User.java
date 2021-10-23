package user;

import java.io.*;
import java.net.Socket;
import logger.Logger;
import settings.Settings;

public class User {
    private Logger logger = Logger.getInstance();
    private Settings settings = Settings.getInstance();
    private BufferedReader reader;
    private BufferedReader in;
    private BufferedWriter out;

    //При создании экземпляра класса запускается поток Клиента
    public User() {
        try {
            final Socket clientSocket = new Socket(settings.getServerDNS(), settings.getServerPort());
            reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Please enter your name:");
            String name = reader.readLine();
            out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            //Отправляем имя клиента, оно же имя соединения
            out.write(name + "\n");
            out.flush();
            System.out.println("You are connected to Chat!");
            logger.log(name + " connected to Chat!");
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //В отдельном потоке запускаем обработчик входящих сообщений
            new Thread(() -> {
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String message;
                    while (true) {
                        message = in.readLine();
                        if ("/end".equals(message)) {
                            Thread.currentThread().interrupt();
                            clientSocket.close();
                            break;
                        }
                        //Выводим входящее собщение на экран
                        System.out.println(message);
                    }
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    logger.log(ex.getMessage());
                } finally {
                    try {
                        clientSocket.close();
                    } catch (IOException ex) {
                        logger.log(ex.getMessage());
                    }
                }
            }).start();
            String outMsg;
            while (true) {
                outMsg = reader.readLine();
                out.write(outMsg + "\n");
                out.flush();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            logger.log(ex.getMessage());
        }
    }
}