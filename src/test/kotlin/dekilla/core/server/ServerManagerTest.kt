package dekilla.core.server

import dekilla.core.AppConfig
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.SocketUtil
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
}