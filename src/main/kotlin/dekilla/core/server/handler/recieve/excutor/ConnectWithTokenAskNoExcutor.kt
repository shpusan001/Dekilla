package dekilla.core.server.handler.recieve.excutor

import dekilla.core.container.ServerContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.socket.SocketUtil

class ConnectWithTokenAskNoExcutor : ServerRecieveExcutor {

    override fun excute(sockDto: SockDto) {
        val sockRepository: SockRepository = ServerContainer.sockRepository()
        val socketUtil: SocketUtil = UtilConatiner.socketUtil()

        val requesterToken: String = sockDto.data

        val requesterSocket = sockRepository.get(requesterToken)!!

        val noMessage: SockDto = SockDto(
            "CONNECT_WITH_TOKEN_NO",
            "#",
            "",
            null
        )

        socketUtil.send(requesterSocket.socket, noMessage)
    }
}