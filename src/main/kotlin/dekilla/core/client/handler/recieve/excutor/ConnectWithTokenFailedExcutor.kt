package dekilla.core.client.handler.recieve.excutor

import dekilla.core.domain.SockDto
import javax.swing.JOptionPane

class ConnectWithTokenFailedExcutor : ClientRecieveExcutor {

    override fun excute(sockDto: SockDto) {
        JOptionPane.showMessageDialog(null, "연결에 실패했습니다");
    }
}