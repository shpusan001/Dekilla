package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.file.FileRecieveService
import dekilla.core.client.file.recieve.RecieveService
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.FileRecieveProcessingExcutor
import java.awt.Desktop
import java.io.File
import java.io.IOException
import javax.swing.JOptionPane

class FileSendEndFaildExcutor : ClientRecieveExcutor {

    val fileRecieveService: FileRecieveService = ClientContainer.fileRecieveService()
    val fileRecieveProcessingExcutor: FileRecieveProcessingExcutor = UtilConatiner.fileRecieveProcessingExcutor()

    override fun excute(sockDto: SockDto) {

        val requesterId: String = sockDto.data
        val recieveService: RecieveService = fileRecieveService.getRecieveService(requesterId)!!

        fileRecieveProcessingExcutor.end(recieveService.fileName)
        fileRecieveService.fileRecieveEnd(requesterId)

        JOptionPane.showMessageDialog(null, "Disconnected with [${requesterId}]")
    }
}