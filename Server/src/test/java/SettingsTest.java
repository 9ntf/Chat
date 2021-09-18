import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

public class SettingsTest {
    Settings settings = Settings.getInstance();

    //Тест метода работы getSettings()
    @Test
    public void testSettings_success() {
        File settingsFile = new File("D:" + File.separator + "Java" + File.separator +
                "Projects" + File.separator + "Chat" + File.separator + "Server" + File.separator + "settings.txt");
        Assertions.assertTrue(settings.getSettings(settingsFile));
    }

    //Тест проверяющий что метод getSettings() правильно берет настройки из файла
    @Test
    public void testSettings_param() throws IOException {
        File settingsFile = new File("D:" + File.separator + "Java" + File.separator +
                "Projects" + File.separator + "Chat" + File.separator + "Server" + File.separator + "settings.txt");
        String expectedServerDNS = "localhost";
        int expectedServerPort = 2222;
        BufferedReader reader = new BufferedReader(new FileReader(settingsFile));
        String st = reader.readLine();
        String[] setting = st.split(" ");
        String serverDNS = setting[0];
        int serverPort = Integer.parseInt(setting[1]);

        Assertions.assertEquals(expectedServerDNS, serverDNS);
        Assertions.assertEquals(expectedServerPort, serverPort);
    }
}
