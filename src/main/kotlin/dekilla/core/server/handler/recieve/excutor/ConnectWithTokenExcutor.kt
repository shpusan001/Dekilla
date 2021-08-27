package dekilla.core.server.handler.recieve.excutor

import dekilla.core.AppConfig
import dekilla.core.container.ServerContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConnectWithTokenExcutor : ServerRecieveExcutor {

    override fun excute(sockDto: SockDto) {
        val sockRepository: SockRepository = ServerContainer.sockRepository()
        val socketUtil: SocketUtil = UtilConatiner.socketUtil()

        val requesterToken: String = sockDto.data.split(sockDto.seperator)[0]
        val targetToken: String = sockDto.data.split(sockDto.seperator)[1]

        val requesterSocket: WrappedSocket = sockRepository.get(requesterToken)!!
        val targetSocket: WrappedSocket? = sockRepository.get(targetToken)

        if (targetSocket != null) {
            val askMessage: SockDto = SockDto(
                "CONNECT_WITH_TOKEN_ASK",
                "#",
                "$requesterToken#$targetToken",
                null
            )
            
            try {
                socketUtil.send(targetSocket.socket, askMessage)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        } else {
            val failedMessage: SockDto = SockDto(
                "CONNECT_WITH_TOKEN_FAILD",
                "#",
                "",
                null
            )

            socketUtil.send(requesterSocket.socket, failedMessage)
        }
    }
}