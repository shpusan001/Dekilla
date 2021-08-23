import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class CommonTest {
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
