package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.ClientManager
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.SocketUtil
import javax.swing.JOptionPane

class ConnectWithTokenYesExcutor : ClientRecieveExcutor {

    override fun excute(sockDto: SockDto) {
        val socketUtil: SocketUtil = UtilConatiner.socketUtil()
        val clientManager: ClientManager = ClientContainer.clientManager()

        val targetId: String = sockDto.data
        clientManager.connectedId = targetId

        JOptionPane.showMessageDialog(null, "Link to ${targetId}")
    }
}