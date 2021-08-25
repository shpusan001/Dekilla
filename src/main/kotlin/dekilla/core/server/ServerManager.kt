package dekilla.core.server

import dekilla.core.server.repository.SockRepository
import dekilla.core.server.runnable.accept.AcceptRunnable
import dekilla.core.server.runnable.accept.DefaultAcceptRunnable
import dekilla.core.server.runnable.recieve.DefaultServerRecieveRunnable
import dekilla.core.server.runnable.recieve.ServerRecieveRunnable
import dekilla.core.util.socket.SocketUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.ServerSocket

@Service
class ServerManager {

    companion object {
        var port: Int = 33333
    }

    private lateinit var serverSocket: ServerSocket
    private lateinit var acceptThread: Thread
    private lateinit var acceptRunnable: AcceptRunnable
    private lateinit var recieveThread: Thread
    private lateinit var recieveRunnable: ServerRecieveRunnable

    private val sockRepository: SockRepository
    private val socketUtil: SocketUtil

    @Autowired
    constructor(sockRepository: SockRepository, socketUtil: SocketUtil) {
        this.sockRepository = sockRepository
        this.socketUtil = socketUtil
    }

    fun bind(port: Int) {
        serverSocket = ServerSocket(port)
    }

    fun accept() {
        acceptRunnable = DefaultAcceptRunnable(serverSocket, sockRepository)
        acceptThread = Thread(acceptRunnable)
        acceptThread.start()
    }

    fun processing() {
        recieveRunnable = DefaultServerRecieveRunnable(sockRepository)
        recieveThread = Thread(recieveRunnable)
        recieveThread.start()
    }

    fun close() {
        acceptThread.interrupt()
        recieveThread.interrupt()
        sockRepository.close()
    }
}