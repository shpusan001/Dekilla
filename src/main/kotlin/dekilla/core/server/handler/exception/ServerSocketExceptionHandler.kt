package dekilla.core.server.handler.exception

import dekilla.core.AppConfig
import dekilla.core.repository.SockRepository
import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.WrappedSocket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.getBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Service
import java.net.Socket

@Service
class ServerSocketExceptionHandler : SocketExceptionHandler {

    private val sockRepository: SockRepository

    @Autowired
    constructor(sockRepository: SockRepository) {
        this.sockRepository = sockRepository
    }

    override fun connectionLost(wrappedSocket: WrappedSocket) {
        val sockId = wrappedSocket.id
        sockRepository.remove(sockId)
        wrappedSocket.socket.close()
        DekillaLog.log("${sockId} 와의 연결이 끊어졌습니다.")

    }
}
