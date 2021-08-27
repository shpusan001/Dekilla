package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.file.FileRecieveService
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.FileRecieveProcessingExcutor
import java.awt.Desktop
import java.io.File
import java.io.IOException


class FileSendEndStocExcutor : ClientRecieveExcutor {

    val fileRecieveService: FileRecieveService = ClientContainer.fileRecieveService()
    val fileRecieveProcessingExcutor: FileRecieveProcessingExcutor = UtilConatiner.fileRecieveProcessingExcutor()

    override fun excute(sockDto: SockDto) {
        fileRecieveProcessingExcutor.end(fileRecieveService.fileName)

        val foler: File = File(fileRecieveService.folderPath)

        var desktop: Desktop? = null
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop()
        }

        try {
            desktop!!.open(foler)
        } catch (e: IOException) {
        }

        fileRecieveService.fileRecieveEnd()
    }
}