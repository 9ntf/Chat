import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//Класс настроек подключения
public class Settings {
    private Logger logger = Logger.getInstance();
    private String serverDNS;
    private int serverPort;
    private static Settings instance;
    private static File settingsFile = new File("D:" + File.separator + "Java" + File.separator +
            "Projects" + File.separator + "Chat" + File.separator + "Server" + File.separator + "settings.txt");

    private Settings() {
    }

    public static Settings getInstance() {
        if (instance == null) {
            instance = new Settings();
            instance.getSettings(settingsFile);
        }
        return instance;
    }

    //Метод получающий настройки из файла settings.txt и записывающий в объект
    public void getSettings(File fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String st = reader.readLine();
            String[] setting = st.split(" ");
            setServerDNS(setting[0]);
            setServerPort(Integer.parseInt(setting[1]));
        } catch (Exception ex) {
            logger.log(ex.getMessage());
        }
    }

    private void setServerDNS(String serverDNS) {
        this.serverDNS = serverDNS;
    }

    private void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public String getServerDNS() {
        return serverDNS;
    }

    public int getServerPort() {
        return serverPort;
    }

    @Override
    public String toString() {
        return "Настройки сервера: " +
                "Domain Name = " + serverDNS +
                ", Port = " + serverPort;
    }
}
