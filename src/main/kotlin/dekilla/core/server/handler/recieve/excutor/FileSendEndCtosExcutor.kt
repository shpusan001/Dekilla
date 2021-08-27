package dekilla.core.server.handler.recieve.excutor

import dekilla.core.container.ServerContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket

class FileSendEndCtosExcutor : ServerRecieveExcutor {

    override fun excute(sockDto: SockDto) {
        val sockRepository: SockRepository = ServerContainer.sockRepository()
        val socketUtil: SocketUtil = UtilConatiner.socketUtil()

        val dataArray: List<String> = sockDto.data.split(sockDto.seperator)

        val requestToken: String = dataArray.get(0)
        val targetToken: String = dataArray.get(1)

        val targetSocket: WrappedSocket = sockRepository.get(targetToken)!!


        val fileSendEndMessage: SockDto = SockDto(
            "FILE_SEND_END_STOC",
            "#",
            "",
            null
        )

        socketUtil.send(targetSocket.socket, fileSendEndMessage)
        DekillaLog.log(
            "[Sending end] To: [${requestToken}] => [${targetToken}]"
        )
    }
}