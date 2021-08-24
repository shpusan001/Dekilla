import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;

public class JavaCommonTest {
    @Test
    public void givenWritingStringToFile_whenUsingFileOutputStream_thenCorrect()
            throws IOException {
        String str = "Hello";
        FileOutputStream outputStream = new FileOutputStream("hi");
        byte[] strToBytes = str.getBytes();
        outputStream.write(strToBytes);
        outputStream.close();
    }
}
