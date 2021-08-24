import listener.file.FileRecieveProcessingListener
import listener.file.FileSendProcessingListener
import org.junit.jupiter.api.Test
import util.SocketUtil
import java.io.File
import java.net.ServerSocket
import java.net.Socket

class CommonTest {

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
            val file: File = File("testFile.txt")
            filesize = file.length()
            SocketUtil().sendFile(socket, file, FileSendProcessingListener())
        }).start()

        Thread.sleep(100)

        Thread(Runnable {
            val socket: Socket = Socket("127.0.0.1", 11111)
            SocketUtil().recieveFile(socket, "recieved.txt", filesize, FileRecieveProcessingListener())
        }).start()
    }
}