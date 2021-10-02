package dekilla.core.server

import dekilla.core.container.ServerContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.server.repository.SockRepository
import dekilla.core.server.runnable.accept.AcceptRunnable
import dekilla.core.server.runnable.accept.DefaultAcceptRunnable
import dekilla.core.server.runnable.recieve.DefaultServerRecieveRunnable
import dekilla.core.server.runnable.recieve.ServerRecieveRunnable
import dekilla.core.util.socket.SocketUtil
import java.net.ServerSocket

class ServerManager {

    companion object {
        var port: Int = 33333
    }

    lateinit var serverSocket: ServerSocket
    private lateinit var acceptThread: Thread
    private lateinit var acceptRunnable: AcceptRunnable
    private lateinit var recieveThread: Thread
    private lateinit var recieveRunnable: ServerRecieveRunnable

    private val sockRepository: SockRepository
    private val socketUtil: SocketUtil


    constructor() {
        this.sockRepository = ServerContainer.sockRepository()
        this.socketUtil = UtilConatiner.socketUtil()
    }

    fun bind(port: Int) {
        serverSocket = ServerSocket(port)
    }

    fun accept() {
        acceptRunnable = DefaultAcceptRunnable()
        acceptRunnable.setServerSocket(serverSocket)
        acceptThread = Thread(acceptRunnable)
        acceptThread.start()
    }

    fun processing() {
        recieveRunnable = DefaultServerRecieveRunnable()
        recieveThread = Thread(recieveRunnable)
        recieveThread.start()
    }

    fun close() {
        acceptThread.interrupt()
        recieveThread.interrupt()
        sockRepository.close()
    }
}