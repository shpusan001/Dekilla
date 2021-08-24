package dekilla.core.server.runnable.recieve

import dekilla.core.AppConfig
import dekilla.core.domain.SockDto
import dekilla.core.repository.SockRepository
import dekilla.core.server.handler.exception.SocketExceptionHandler
import dekilla.core.server.handler.recieve.ServerRecieveHandler
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.net.Socket
import java.util.concurrent.CopyOnWriteArrayList

class DefaultRecieveRunnable : RecieveRunnable {

    private val sockRepository: SockRepository
    private val sockList: CopyOnWriteArrayList<WrappedSocket>

    private val recieveThreadMap: HashMap<WrappedSocket, Thread> = HashMap()

    private val socketUtil: SocketUtil
    private val serverRecieveHandler: ServerRecieveHandler
    private val serverSocketExceptionHandler: SocketExceptionHandler

    constructor(sockRepository: SockRepository) {
        this.sockRepository = sockRepository
        this.sockList = sockRepository.getList()
        
        val ac: ApplicationContext = AnnotationConfigApplicationContext(AppConfig::class.java)

        socketUtil = ac.getBean(SocketUtil::class.java)
        serverRecieveHandler = ac.getBean(
            "defaultServerRecieveHandler",
            ServerRecieveHandler::class.java
        )
        serverSocketExceptionHandler = ac.getBean(
            "serverSocketExceptionHandler",
            SocketExceptionHandler::class.java
        )
    }

    override fun run() {
        while (!Thread.interrupted()) {
            for (wrappedSocket in sockList) {
                if (!recieveThreadMap.containsKey(wrappedSocket)) {
                    val recieveThread = Thread(RecieveUnit(wrappedSocket))
                    recieveThreadMap.put(wrappedSocket, recieveThread)
                    sockList.remove(wrappedSocket)
                    recieveThread.start()
                }
            }
        }
    }

    inner class RecieveUnit : Runnable {

        private val wrappedSocket: WrappedSocket

        constructor(wrappedSocket: WrappedSocket) {
            this.wrappedSocket = wrappedSocket
        }

        override fun run() {
            try {
                val sockDto: SockDto = socketUtil.recieve(wrappedSocket.socket) as SockDto
                serverRecieveHandler.process(sockDto)
                if (sockRepository.isContain(wrappedSocket)) {
                    sockList.add(wrappedSocket)
                }
            } catch (e: Exception) {
                serverSocketExceptionHandler.connectionLost(wrappedSocket)
            }
        }
    }
}