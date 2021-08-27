package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.file.FileRecieveService
import dekilla.core.container.ClientContainer
import dekilla.core.domain.SockDto
import java.io.FileOutputStream

class FileSendStoCExcutor : ClientRecieveExcutor {

    val fileRecieveService: FileRecieveService = ClientContainer.fileRecieveService()

    override fun excute(sockDto: SockDto) {
        val fileOutputStream: FileOutputStream = fileRecieveService.fileOutputStream!!

        val data: ByteArray = sockDto.obj as ByteArray
        fileOutputStream.write(data)
        fileRecieveService.currentFileSize += data.size
    }
}