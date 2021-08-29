package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.file.FileController
import dekilla.core.container.ClientContainer
import dekilla.core.domain.SockDto
import javax.swing.JOptionPane

class DisconnectExcutor : ClientRecieveExcutor {

    override fun excute(sockDto: SockDto) {

        val opponentId: String = sockDto.data

        ClientContainer.fileController().isLinked = false
        JOptionPane.showMessageDialog(null, "Disconnect with [${opponentId}]")

    }
}