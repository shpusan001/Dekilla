package dekilla.core.client.handler.recieve.excutor

import dekilla.core.AppConfig
import dekilla.core.client.ClientManager
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import java.net.Socket
import javax.swing.JOptionPane

class ConnectWithTokenAskExcutor : ClientRecieveExcutor {

    override fun excute(sockDto: SockDto) {

        val socketUtil: SocketUtil = UtilConatiner.socketUtil()
        val clientManager: ClientManager = ClientContainer.clientManager()

        val requesterToken: String = sockDto.data.split(sockDto.seperator)[0]
        val targetToken: String = sockDto.data.split(sockDto.seperator)[1]

        val state: Int = JOptionPane.showConfirmDialog(null, "${requesterToken}님이 연결을 요청했습니다. 수락하시겠습니까?")

        if (state == JOptionPane.OK_OPTION) {

            val okMessage: SockDto = SockDto(
                "CONNECT_WITH_TOKEN_ASK_YES",
                "#",
                "$requesterToken#$targetToken",
                null
            )

            clientManager.sendData(okMessage)
        } else {

            val noMessage: SockDto = SockDto(
                "CONNECT_WITH_TOKEN_ASK_NO",
                "#",
                "$requesterToken",
                null
            )

            clientManager.sendData(noMessage)
        }
    }
}