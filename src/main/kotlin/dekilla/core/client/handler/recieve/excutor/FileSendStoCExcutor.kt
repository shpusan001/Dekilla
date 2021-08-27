package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.file.FileRecieveService
import dekilla.core.client.view.MainView
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.container.ViewContainer
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.FileRecieveProcessingExcutor
import java.io.FileOutputStream
import javax.swing.JTextArea

class FileSendStoCExcutor : ClientRecieveExcutor {

    val fileRecieveService: FileRecieveService = ClientContainer.fileRecieveService()
    val fileRecieveProcessingExcutor: FileRecieveProcessingExcutor = UtilConatiner.fileRecieveProcessingExcutor()

    override fun excute(sockDto: SockDto) {
        val fileOutputStream: FileOutputStream = fileRecieveService.fileOutputStream!!

        val data: ByteArray = sockDto.obj as ByteArray
        fileOutputStream.write(data)
        fileRecieveService.currentFileSize += data.size

        val fileName: String = fileRecieveService.fileName
        val fileSize: Long = fileRecieveService.fileSize
        val currentFileSize: Long = fileRecieveService.currentFileSize

        fileRecieveProcessingExcutor.excute(fileName, data.size, currentFileSize, fileSize)
    }
}