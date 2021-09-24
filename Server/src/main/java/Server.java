import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

//Класс сервер, ожидающий подключение пользователей
public class Server {
    private Logger logger = Logger.getInstance();
    private Settings settings = Settings.getInstance();
    private List<Connection> connections = new ArrayList<>();

    //Метод стартующий сервер
    public void start() {
        try {
            final ServerSocket serverSocket = new ServerSocket(settings.getServerPort());
            logger.log("Server started");
            System.out.println("Server Started");
            while (true) {
                final Socket clientSocket = serverSocket.accept();
                //Добавляем в коллекцию новое подключение
                connections.add(new Connection(clientSocket, this));
            }
        } catch (IOException ex) {
            logger.log(ex.getMessage());
        }
    }

    public List<Connection> getConnections() {
        return connections;
    }
}