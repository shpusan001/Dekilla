package dekilla.core.client.handler.exception

import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.WrappedSocket
import org.springframework.stereotype.Service

@Service
class DefaultClientSocketExceptionHandler : ClientSocketExceptionHandler {

    override fun connectionLost(wrappedSocket: WrappedSocket) {
        DekillaLog.log("${wrappedSocket.id} 연결 끊김")
    }
}