package dekilla.core.client.handler.recieve.excutor

import dekilla.core.AppConfig
import dekilla.core.client.ClientManager
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.SocketUtil
import dekilla.core.util.socket.WrappedSocket
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import javax.swing.JOptionPane

class ConnectWithTokenYesExcutor : ClientRecieveExcutor {

    override fun excute(sockDto: SockDto) {
        val socketUtil: SocketUtil = UtilConatiner.socketUtil()
        val clientManager: ClientManager = ClientContainer.clientManager()

        val targetId: String = sockDto.data
        clientManager.connectedId = targetId

        JOptionPane.showMessageDialog(null, "${targetId} 와 연결되었습니다");
    }
}