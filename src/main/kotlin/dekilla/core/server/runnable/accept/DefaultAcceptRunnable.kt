package dekilla.core.server.runnable.accept

import dekilla.core.AppConfig
import dekilla.core.domain.SockDto
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.generator.IdGenerator
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
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
                DekillaLog.log("${sockId}님 접속")
                val wrappedSocket = WrappedSocket(socket, sockId)
                sockRepository.add(sockId, wrappedSocket)

                val sockDto: SockDto = SockDto(
                    "SEND_ID",
                    "#",
                    sockId
                )
                socketUtil.send(wrappedSocket.socket, sockDto)
            }
        }
    }
}