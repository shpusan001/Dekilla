package dekilla.core.server.runnable

import dekilla.core.AppConfig
import dekilla.core.domain.SockDto
import dekilla.core.repository.SockRepository
import dekilla.core.util.generator.IdGenerator
import dekilla.core.util.socket.SocketUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.net.ServerSocket
import java.net.Socket

class DefaultAcceptRunnable : AcceptRunnable {

    private val serverSocket: ServerSocket
    private val sockRepository: SockRepository
    private lateinit var idGenerator: IdGenerator
    private lateinit var socketUtil: SocketUtil

    constructor(serverSocket: ServerSocket, sockRepository: SockRepository) {
        this.serverSocket = serverSocket
        this.sockRepository = sockRepository

        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)
        idGenerator = ac.getBean("numberIdGenerator", IdGenerator::class.java)
        socketUtil = ac.getBean(SocketUtil::class.java)
    }

    override fun run() {
        while (!Thread.interrupted()) {
            val socket: Socket = serverSocket.accept()

            if (!Thread.interrupted()) {
                val sockId: String = idGenerator.generate()
                sockRepository.add(sockId, socket)

                val sockDto: SockDto = SockDto(
                    "SEND_ID",
                    "#",
                    sockId
                )
                socketUtil.send(socket, sockDto)
            }
        }
    }
}