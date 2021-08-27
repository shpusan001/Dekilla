package dekilla.core.server.handler.recieve.excutor

import dekilla.core.container.ServerContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.server.repository.DataSendPermissionRepository
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket

class FileSendPermissionExcutor : ServerRecieveExcutor {

    override fun excute(sockDto: SockDto) {
        val sockRepository: SockRepository = ServerContainer.sockRepository()
        val socketUtil: SocketUtil = UtilConatiner.socketUtil()
        val dataSendPermissionRepository: DataSendPermissionRepository =
            ServerContainer.dataSendPermissionRepository()

        val dataArray: List<String> = sockDto.data.split(sockDto.seperator)

        val requestToken: String = dataArray.get(0)
        val targetToken: String = dataArray.get(1)

        val requestSocket: WrappedSocket = sockRepository.get(requestToken)!!

        if (dataSendPermissionRepository.authorization(requestToken, targetToken)) {
            val fileSendPermissionYesMessage: SockDto = SockDto(
                "FILE_SEND_PERMISSION_YES_STOC",
                "#",
                "",
                null
            )
            socketUtil.send(requestSocket.socket, fileSendPermissionYesMessage)
        } else {
            val fileSendPermissionNoMessage: SockDto = SockDto(
                "FILE_SEND_PERMISSION_NO_STOC",
                "#",
                "",
                null
            )
            socketUtil.send(requestSocket.socket, fileSendPermissionNoMessage)
        }
    }
}