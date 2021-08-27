package dekilla.core.client.handler.exception

import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.WrappedSocket
import org.springframework.stereotype.Service


class DefaultClientSocketExceptionHandler : ClientSocketExceptionHandler {

    override fun connectionFaild() {
        DekillaLog.log("연결 실패")
    }

    override fun connectionLost(wrappedSocket: WrappedSocket) {
        DekillaLog.log("${wrappedSocket.id} 연결 끊김")
    }
}