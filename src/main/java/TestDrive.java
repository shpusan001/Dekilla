import org.springframework.boot.SpringBootConfiguration;
import dekilla.core.util.socket.SocketUtil;

import java.net.Socket;

@SpringBootConfiguration
public class TestDrive {

    public static void main(String[] args) {
        new SocketUtil().send(new Socket(), new Object());
    }
}
