package dekilla.core.server.runnable.accept

import dekilla.core.container.ServerContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.generator.IdGenerator
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.springframework.stereotype.Component
import java.net.ServerSocket
import java.net.Socket

@Component
class DefaultAcceptRunnable : AcceptRunnable {

    private lateinit var serverSocket: ServerSocket
    private lateinit var sockRepository: SockRepository
    private lateinit var idGenerator: IdGenerator
    private lateinit var socketUtil: SocketUtil

    override fun setServerSocket(serverSocket: ServerSocket) {
        this.serverSocket = serverSocket
        this.sockRepository = ServerContainer.sockRepository()
        this.idGenerator = UtilConatiner.idGenerator()
        this.socketUtil = UtilConatiner.socketUtil()
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
                    sockId,
                    null
                )
                socketUtil.send(wrappedSocket.socket, sockDto)
            }
        }
    }
}