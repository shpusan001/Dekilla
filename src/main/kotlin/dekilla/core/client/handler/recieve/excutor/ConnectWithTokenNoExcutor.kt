package dekilla.core.client.handler.recieve.excutor

import dekilla.core.domain.SockDto
import javax.swing.JOptionPane

class ConnectWithTokenNoExcutor : ClientRecieveExcutor {

    override fun excute(sockDto: SockDto) {
        JOptionPane.showMessageDialog(null, "Connection refused.")
    }
}