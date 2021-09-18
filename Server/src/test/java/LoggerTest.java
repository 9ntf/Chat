import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LoggerTest {
    Logger logger = Logger.getInstance();

    //Тест работы метода log()
    @Test
    public void testLog_success() {
        String test = "Test log Assertions.assertTrue()";
        Assertions.assertTrue(logger.log(test));
    }
}
