package dekilla.core.client.file

import dekilla.core.container.ClientContainer
import org.junit.jupiter.api.Test
import java.io.File

class FileControllerTest {

    @Test
    fun fileControllerTest() {
        ClientContainer.clientManager().connect()
        ClientContainer.clientManager().processing()
        FileController().sendFile(File("test.txt"))
    }
}