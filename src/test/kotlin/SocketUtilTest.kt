import dekilla.core.util.socket.FileRecieveProcessingExcutor
import dekilla.core.util.socket.FileSendProcessingExcutor
import org.junit.jupiter.api.Test
import dekilla.core.util.socket.SocketUtil
import java.io.File
import java.net.ServerSocket
import java.net.Socket

class SocketUtilTest {

    @Test
    fun dataSendTest() {
        Thread(Runnable {
            val serverSock: ServerSocket = ServerSocket(9999)
            val socket: Socket = serverSock.accept()
            SocketUtil().send(socket, "hi")
        }).start()

        Thread.sleep(100)

        Thread(Runnable {
            val socket: Socket = Socket("127.0.0.1", 9999)
            val message = SocketUtil().recieve(socket)
            println(message)
        }).start()
    }

    @Test
    fun fileSendTest() {
        var filesize: Long = 0

        Thread(Runnable {
            val serverSock: ServerSocket = ServerSocket(11111)
            val socket: Socket = serverSock.accept()
            val file: File = File("image.png")
            filesize = file.length()
            SocketUtil().sendFile(socket, file, FileSendProcessingExcutor())
        }).start()

        Thread.sleep(100)

        Thread(Runnable {
            val socket: Socket = Socket("127.0.0.1", 11111)
            SocketUtil().recieveFile(socket, "recieved.txt", filesize, FileRecieveProcessingExcutor())
        }).start()
    }
}