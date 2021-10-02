package dekilla.core.server.handler.exception

import dekilla.core.container.ServerContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.server.ServerManager
import dekilla.core.server.handler.recieve.excutor.ServerRecieveExcutor
import dekilla.core.server.repository.DataSendPermissionRepository
import dekilla.core.server.repository.HashmapDataSendPermissionRepository
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

class DefaultServerSocketExceptionHandler : ServerSocketExceptionHandler {

    private val sockRepository: SockRepository
    private val dataSendPermissionRepository: HashmapDataSendPermissionRepository
    val socketUtil: SocketUtil

    constructor() {
        this.sockRepository = ServerContainer.sockRepository()
        this.dataSendPermissionRepository =
            ServerContainer.dataSendPermissionRepository() as HashmapDataSendPermissionRepository
        this.socketUtil = UtilConatiner.socketUtil()
    }

    override fun connectionLost(wrappedSocket: WrappedSocket) {
        val sockId = wrappedSocket.id

        val senderId: String? = dataSendPermissionRepository.recieveMap.get(sockId)
        val targetId: String? = dataSendPermissionRepository.sendMap.get(sockId)

        //받는 클라이언트가 나갔을때
        if (senderId != null && senderId != targetId) {
            val senderSocket: WrappedSocket = sockRepository.get(senderId)!!

            val fileSendMessage: SockDto = SockDto(
                "DISCONNECT_STOC",
                "#",
                sockId,
                null
            )
            socketUtil.send(senderSocket.socket, fileSendMessage)
        }

        // 보내는 클라이언트가 나갔을 때
        if (targetId != null && senderId != targetId) {
            val recieverSocket: WrappedSocket = sockRepository.get(targetId)!!
            val fileSendEndMessage: SockDto = SockDto(
                "FILE_SEND_END_FAILED_STOC",
                "#",
                "${sockId}",
                null
            )

            socketUtil.send(recieverSocket.socket, fileSendEndMessage)
        }

        sockRepository.remove(sockId)
        dataSendPermissionRepository.remove(sockId)


        wrappedSocket.socket.close()
        DekillaLog.log("[${sockId}] is disconnected")
    }
}
