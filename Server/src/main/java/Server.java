import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//Класс сервер, ожидающий подключение пользователей
public class Server {
    private Logger logger = Logger.getInstance();
    private Settings settings = Settings.getInstance();
    public static List<Connection> connections = new ArrayList<>();

    //При создании экземпляра класса запускается сервер
    public Server() {
        try {
            ServerSocket serverSocket = new ServerSocket(settings.getServerPort());
            logger.log("Server started");
            System.out.println("Server Started");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                //Добавляем в коллекцию новое подключение
                connections.add(new Connection(clientSocket));
            }
        } catch (IOException ex) {
            logger.log(ex.getMessage());
        }
    }
}