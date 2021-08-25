package dekilla.core.server

import dekilla.core.AppConfig
import dekilla.core.client.ClientManager
import dekilla.core.client.handler.exception.DefaultClientSocketExceptionHandler
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.FileRecieveProcessingExcutor
import dekilla.core.util.socket.FileSendProcessingExcutor
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.junit.jupiter.api.Test
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.net.Socket

class ServerManagerTest {

    @Test
    fun serverManagerTest() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val serverManager: ServerManager = ac.getBean("serverManager") as ServerManager
        serverManager.bind(9999)
        serverManager.accept()

        Thread(Runnable {
            val socket: Socket = Socket("127.0.0.1", 9999)
            val message = SocketUtil().recieve(socket) as SockDto
            println(message.command + " " + message.data)
        }).start()

        Thread.sleep(1000)

        serverManager.close()
    }

    @Test
    fun ServerManagerRecieveTest() {
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        val serverManager: ServerManager = ac.getBean("serverManager") as ServerManager
        serverManager.bind(33333)
        serverManager.accept()
        serverManager.processing()

        Thread(Runnable {
            val cm = ClientManager(
                SocketUtil(),
                DefaultClientSocketExceptionHandler(),
                FileRecieveProcessingExcutor(),
                FileSendProcessingExcutor()
            )
            val wrappedSocket: WrappedSocket = cm.connect()!!
            Thread.sleep(50)
            SocketUtil().send(
                wrappedSocket.socket, SockDto(
                    "NOTICE",
                    "#",
                    "test message1",
                    null
                )
            )
            cm.processing()
        }).start()

        Thread(Runnable {
            val cm = ClientManager(
                SocketUtil(),
                DefaultClientSocketExceptionHandler(),
                FileRecieveProcessingExcutor(),
                FileSendProcessingExcutor()
            )
            val wrappedSocket: WrappedSocket = cm.connect()!!
            Thread.sleep(100)
            SocketUtil().send(
                wrappedSocket.socket, SockDto(
                    "NOTICE",
                    "#",
                    "test message2",
                    null
                )
            )
            cm.processing()
        }).start()

        Thread.sleep(1000)
        serverManager.close()
    }
}