package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.ClientManager
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.SocketUtil
import javax.swing.JOptionPane

class ConnectWithTokenAskExcutor : ClientRecieveExcutor {

    override fun excute(sockDto: SockDto) {

        val socketUtil: SocketUtil = UtilConatiner.socketUtil()
        val clientManager: ClientManager = ClientContainer.clientManager()

        val requesterToken: String = sockDto.data.split(sockDto.seperator)[0]
        val targetToken: String = sockDto.data.split(sockDto.seperator)[1]

        val state: Int = JOptionPane.showConfirmDialog(null, "[${requesterToken}] requested a connection. Accept?")

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