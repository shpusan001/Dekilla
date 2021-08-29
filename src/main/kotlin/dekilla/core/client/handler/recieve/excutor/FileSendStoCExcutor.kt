package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.file.FileRecieveService
import dekilla.core.client.file.recieve.RecieveService
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

        val requesterId: String = sockDto.data


        val recieveService: RecieveService = fileRecieveService.getRecieveService(requesterId)!!

        val data: ByteArray = sockDto.obj as ByteArray
        recieveService.fileOutputStream!!.write(data)
        recieveService.currentFileSize += data.size

        val fileName: String = recieveService.fileName
        val fileSize: Long = recieveService.fileSize
        val currentFileSize: Long = recieveService.currentFileSize

        fileRecieveProcessingExcutor.excute(fileName, data.size, currentFileSize, fileSize)
        
    }
}