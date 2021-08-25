package dekilla.core.client

import dekilla.core.AppConfig
import dekilla.core.client.handler.exception.ClientSocketExceptionHandler
import dekilla.core.client.handler.recieve.excutor.ClientRecieveExcutor
import dekilla.core.client.runnable.ClientRecieveRunnable
import dekilla.core.client.runnable.DefaultClientRecieveRunnable
import dekilla.core.domain.SockDto
import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.FileRecieveProcessingExcutor
import dekilla.core.util.socket.FileSendProcessingExcutor
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Controller
import org.springframework.stereotype.Service
import java.awt.image.WritableRaster
import java.io.File
import java.net.Socket

@Controller
class ClientManager {

    companion object {
        val ip: String = "127.0.0.1"
        val port: Int = 33333
    }

    private lateinit var socket: Socket
    private var wrappedSocket: WrappedSocket? = null

    private var targetSocket: Socket? = null
    private var targetWrappedSocket: WrappedSocket? = null

    private lateinit var recieveThread: Thread
    private lateinit var recieveRunnable: ClientRecieveRunnable

    private val socketUtil: SocketUtil
    private val clientSocketExceptionHandler: ClientSocketExceptionHandler
    private val fileRecieveProcessingExcutor: FileRecieveProcessingExcutor
    private val fileSendProcessingExcutor: FileSendProcessingExcutor

    @Autowired
    constructor(
        socketUtil: SocketUtil,
        clientSocketExceptionHandler: ClientSocketExceptionHandler,
        fileRecieveProcessingExcutor: FileRecieveProcessingExcutor,
        fileSendProcessingExcutor: FileSendProcessingExcutor
    ) {
        this.socketUtil = socketUtil
        this.clientSocketExceptionHandler = clientSocketExceptionHandler
        this.fileRecieveProcessingExcutor = fileRecieveProcessingExcutor
        this.fileSendProcessingExcutor = fileSendProcessingExcutor
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

    fun sendFile(file: File) {
        socketUtil.sendFile(socket, file, fileSendProcessingExcutor)
    }

    fun recieveFile(file: File) {
        socketUtil.sendFile(socket, file, fileRecieveProcessingExcutor)
    }
}