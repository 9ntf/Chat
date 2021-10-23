import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import server.Connection;

import java.io.IOException;

public class ConnectionTestMock {

    //Тест отправки сообщения
    @Test
    public void testSend_success() throws IOException {
        String testMessage = "Test message";

        Connection connection = Mockito.mock(Connection.class);
        Mockito.when(connection.send(testMessage)).thenReturn(true);

        Assertions.assertTrue(connection.send(testMessage));
    }
}
