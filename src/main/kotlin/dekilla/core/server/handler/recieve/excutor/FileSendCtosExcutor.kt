package dekilla.core.server.handler.recieve.excutor

import dekilla.core.container.ServerContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket

class FileSendCtosExcutor : ServerRecieveExcutor {

    override fun excute(sockDto: SockDto) {
        try {
            val sockRepository: SockRepository = ServerContainer.sockRepository()
            val socketUtil: SocketUtil = UtilConatiner.socketUtil()

            val requesterToken: String = sockDto.data.split(sockDto.seperator)[0]
            val targetToken: String = sockDto.data.split(sockDto.seperator)[1]

            val targetSocket: WrappedSocket? = sockRepository.get(targetToken)

            if (targetSocket != null) {
                val fileData: ByteArray = sockDto.obj as ByteArray
                val fileSendMessage: SockDto = SockDto(
                    "FILE_SEND_STOC",
                    "#",
                    requesterToken,
                    fileData
                )

                synchronized(this) {
                    try {
                        socketUtil.send(targetSocket.socket, fileSendMessage)
                    } catch (e: Exception) {
                    }
                }
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
