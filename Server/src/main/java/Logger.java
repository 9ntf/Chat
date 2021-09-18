import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

//Класс логирующий все сообщения
public class Logger {
    private Date date = new Date();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
    private static Logger instance;
    private File logFile = new File("D:" + File.separator + "Java" + File.separator +
            "Projects" + File.separator + "Chat" + File.separator + "Server" + File.separator + "log.txt");

    private Logger() {
    }

    public static Logger getInstance() {
        if (instance == null) instance = new Logger();
        return instance;
    }

    //Метод логирующий работу программы в файл log.txt
    public boolean log(String message) {
        try (FileWriter writer = new FileWriter(logFile, true)) {
            writer.write("[" + dateFormat.format(date) + "] " + message + "\n");
            writer.flush();
            return true;
        } catch (IOException ex) {
            log(ex.getMessage());
            return false;
        }
    }
}