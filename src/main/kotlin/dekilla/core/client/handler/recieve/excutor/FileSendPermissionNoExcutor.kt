package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.file.FileController
import dekilla.core.container.ClientContainer
import dekilla.core.domain.SockDto
import javax.swing.JOptionPane

class FileSendPermissionNoExcutor : ClientRecieveExcutor {

    private val fileController: FileController = ClientContainer.fileController()

    override fun excute(sockDto: SockDto) {
        JOptionPane.showMessageDialog(null, "You are not connected to the other client.")
    }
}