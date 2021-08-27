package dekilla.core.server.handler.recieve.excutor

import dekilla.core.AppConfig
import dekilla.core.container.ServerContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.server.repository.DataSendPermissionRepository
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext

class ConnectWithTokenAskYesExcutor : ServerRecieveExcutor {
    override fun excute(sockDto: SockDto) {
        val sockRepository: SockRepository = ServerContainer.sockRepository()
        val socketUtil: SocketUtil = UtilConatiner.socketUtil()

        val requesterToken: String = sockDto.data.split(sockDto.seperator)[0]
        val targetToken: String = sockDto.data.split(sockDto.seperator)[1]

        val requestSocket: WrappedSocket = sockRepository.get(requesterToken)!!
        val targetSocket: WrappedSocket = sockRepository.get(targetToken)!!

        val dataSendPermissionRepository: DataSendPermissionRepository =
            ServerContainer.dataSendPermissionRepository()

        dataSendPermissionRepository.add(requesterToken, targetToken)

        val yesMessage: SockDto = SockDto(
            "CONNECT_WITH_TOKEN_YES",
            "#",
            targetToken,
            null
        )

        socketUtil.send(requestSocket.socket, yesMessage)

        DekillaLog.log("[Linked] To: [${requesterToken}] <===> [${targetToken}]")
    }
}