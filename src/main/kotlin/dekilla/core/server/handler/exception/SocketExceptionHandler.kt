package dekilla.core.server.handler.exception;

import dekilla.core.util.socket.WrappedSocket

interface SocketExceptionHandler {

    fun connectionLost(wrappedSocket: WrappedSocket)
}
