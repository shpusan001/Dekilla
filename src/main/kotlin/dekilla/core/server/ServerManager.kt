package dekilla.core.server

import dekilla.core.repository.SockRepository
import dekilla.core.util.socketutil.SocketUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.InetSocketAddress
import java.net.ServerSocket

@Service
class ServerManager {

    companion object {
        var port: Int = 33333
    }

    private var serverSocket: ServerSocket

    private val sockRepository: SockRepository
    private val socketUtil: SocketUtil

    @Autowired
    constructor(sockRepository: SockRepository, socketUtil: SocketUtil) {
        this.sockRepository = sockRepository
        this.socketUtil = socketUtil

        serverSocket = ServerSocket()
    }

    fun bind(port: Int) {
        val inetSocketAddress: InetSocketAddress = InetSocketAddress(port)
        serverSocket.bind(inetSocketAddress)
    }
}