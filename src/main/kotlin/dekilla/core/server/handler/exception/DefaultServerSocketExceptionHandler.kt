package dekilla.core.server.handler.exception

import dekilla.core.container.ServerContainer
import dekilla.core.server.ServerManager
import dekilla.core.server.handler.recieve.excutor.ServerRecieveExcutor
import dekilla.core.server.repository.DataSendPermissionRepository
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.WrappedSocket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

class DefaultServerSocketExceptionHandler : ServerSocketExceptionHandler {

    private val sockRepository: SockRepository
    private val dataSendPermissionRepository: DataSendPermissionRepository

    constructor() {
        this.sockRepository = ServerContainer.sockRepository()
        this.dataSendPermissionRepository = ServerContainer.dataSendPermissionRepository()
    }

    override fun connectionLost(wrappedSocket: WrappedSocket) {
        val sockId = wrappedSocket.id

        sockRepository.remove(sockId)
        dataSendPermissionRepository.remove(sockId)

        wrappedSocket.socket.close()
        DekillaLog.log("Disconnected from [${sockId}]")
    }
}
