package dekilla.core.server.handler.exception

import dekilla.core.server.repository.SockRepository
import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.WrappedSocket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

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
