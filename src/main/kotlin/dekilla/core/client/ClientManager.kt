package dekilla.core.client

import dekilla.core.client.handler.exception.ClientSocketExceptionHandler
import dekilla.core.client.runnable.ClientRecieveRunnable
import dekilla.core.client.runnable.DefaultClientRecieveRunnable
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.*
import java.net.Socket

class ClientManager {

    companion object {
        var ip: String = "xxx.xxx.xxx.xxx"
        var port: Int = 33333
    }

    private lateinit var socket: Socket

    var wrappedSocket: WrappedSocket? = null
    var connectedId: String = ""

    private lateinit var recieveThread: Thread
    private lateinit var recieveRunnable: ClientRecieveRunnable

    private val socketUtil: SocketUtil
    private val clientSocketExceptionHandler: ClientSocketExceptionHandler

    constructor() {
        this.socketUtil = UtilConatiner.socketUtil()
        this.clientSocketExceptionHandler = ClientContainer.clientSocketExceptionHandler()
    }

    fun connect(): WrappedSocket? {
        //connect
        try {
            socket = Socket(ip, port)
            //id initialization from server
            var id: String
            val sockDto = socketUtil.recieve(socket) as SockDto
            if (sockDto.command == "SEND_ID") {
                id = sockDto.data
                wrappedSocket = WrappedSocket(socket, id)
                return wrappedSocket
            }
        } catch (e: Exception) {
            clientSocketExceptionHandler.connectionFaild()
            throw e
        }
        return null
    }

    fun processing() {
        if (wrappedSocket != null) {
            recieveRunnable = DefaultClientRecieveRunnable(wrappedSocket!!)
            recieveThread = Thread(recieveRunnable)
            recieveThread.start()
        }
    }

    fun close() {
        recieveThread.interrupt()
        if (wrappedSocket != null) {
            wrappedSocket!!.socket.close()
        }
    }

    fun sendData(sockDto: SockDto) {
        socketUtil.send(socket, sockDto)
    }
}