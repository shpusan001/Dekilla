package dekilla.core.client.handler.recieve.excutor

import dekilla.core.domain.SockDto
import javax.swing.JOptionPane

class ConnectWithTokenNoExcutor : ClientRecieveExcutor {

    override fun excute(sockDto: SockDto) {
        JOptionPane.showMessageDialog(null, "연결이 거부당했습니다.")
    }
}