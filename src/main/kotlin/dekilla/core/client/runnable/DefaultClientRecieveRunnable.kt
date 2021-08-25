package dekilla.core.client.runnable

import dekilla.core.AppConfig
import dekilla.core.client.ClientManager
import dekilla.core.client.handler.exception.ClientSocketExceptionHandler
import dekilla.core.client.handler.recieve.ClientRecieveHandler
import dekilla.core.domain.SockDto
import dekilla.core.server.handler.exception.ServerSocketExceptionHandler
import dekilla.core.server.handler.recieve.ServerRecieveHandler
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.net.Socket

class DefaultClientRecieveRunnable : ClientRecieveRunnable {

    private val wrappedSocket: WrappedSocket

    private val socketUtil: SocketUtil
    private val clientRecieveHandler: ClientRecieveHandler
    private val clientSocketExceptionHandler: ClientSocketExceptionHandler

    constructor(wrappedSocket: WrappedSocket) {
        this.wrappedSocket = wrappedSocket

        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        socketUtil = ac.getBean(SocketUtil::class.java)
        clientRecieveHandler = ac.getBean(
            "defaultClientRecieveHandler",
            ClientRecieveHandler::class.java
        )
        clientSocketExceptionHandler = ac.getBean(
            "defaultClientSocketExceptionHandler",
            ClientSocketExceptionHandler::class.java
        )
    }

    override fun run() {
        val socket: Socket = wrappedSocket.socket
        while (!Thread.interrupted()) {
            try {
                val sockDto: SockDto = socketUtil.recieve(socket) as SockDto
                clientRecieveHandler.process(sockDto)
            } catch (e: Exception) {
                clientSocketExceptionHandler.connectionLost(wrappedSocket)
                Thread.currentThread().interrupt()
            }
        }
    }
}