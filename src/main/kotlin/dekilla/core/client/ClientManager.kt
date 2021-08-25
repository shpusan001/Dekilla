package dekilla.core.client

import dekilla.core.AppConfig
import dekilla.core.client.runnable.ClientRecieveRunnable
import dekilla.core.client.runnable.DefaultClientRecieveRunnable
import dekilla.core.domain.SockDto
import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Service
import java.net.Socket

@Service
class ClientManager {

    companion object {
        val ip: String = "127.0.0.1"
        val port: Int = 33333
    }

    private lateinit var socket: Socket
    private lateinit var wrappedSocket: WrappedSocket
    private lateinit var recieveThread: Thread
    private lateinit var recieveRunnable: ClientRecieveRunnable

    private val socketUtil: SocketUtil

    @Autowired
    constructor(socketUtil: SocketUtil) {
        this.socketUtil = socketUtil
    }

    fun connect() {
        //connect
        socket = Socket(ip, port)

        //id initialization from server
        var id: String
        val sockDto = socketUtil.recieve(socket) as SockDto
        if (sockDto.command == "SEND_ID") {
            id = sockDto.data
            wrappedSocket = WrappedSocket(socket, id)
        }
    }

    fun processing() {
        recieveRunnable = DefaultClientRecieveRunnable(wrappedSocket)
        recieveThread = Thread(recieveRunnable)
        recieveThread.start()
    }

    fun close() {
        recieveThread.interrupt()
        wrappedSocket.socket.close()
    }
}