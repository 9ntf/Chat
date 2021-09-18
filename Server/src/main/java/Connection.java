import java.io.*;
import java.net.Socket;

//Класс обрабатывающий все соединения и отправку сообщений
public class Connection{
    private Logger logger = Logger.getInstance();
    private BufferedReader in;
    private BufferedWriter out;
    private String name;

    public Connection(Socket socket) {
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //Пользователь вводит свое имя
            this.name = in.readLine();
            //В отдельном потоке запускаем рассылку по всем подключившимся
            new Thread(() -> {
                String word;
                try {
                    while (true) {
                        word = in.readLine();
                        logger.log(this.name + ": " +word);
                        for (Connection connection : Server.connections) {
                            connection.send(this.name + ": " +word);
                        }
                    }
                } catch (IOException ex) {
                    logger.log(ex.getMessage());
                    System.out.println(ex.getMessage());
                }
            }).start();
        } catch (IOException ex) {
            logger.log(ex.getMessage());
            System.out.println(ex.getMessage());
        }
    }

    //Метод отправки сообщения
    public boolean send(String message) {
        try {
            out.write(message + "\n\r");
            out.flush();
            return true;
        } catch (IOException ex) {
            logger.log(ex.getMessage());
            return false;
        }
    }
}