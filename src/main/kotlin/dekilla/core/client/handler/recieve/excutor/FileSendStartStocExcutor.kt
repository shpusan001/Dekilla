package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.file.FileRecieveService
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.FileRecieveProcessingExcutor

class FileSendStartStocExcutor : ClientRecieveExcutor {

    val fileRecieveService: FileRecieveService = ClientContainer.fileRecieveService()
    val fileRecieveProcessingExcutor: FileRecieveProcessingExcutor = UtilConatiner.fileRecieveProcessingExcutor()

    override fun excute(sockDto: SockDto) {

        val fileName: String = sockDto.data.split(sockDto.seperator)[0]
        val fileSize: String = sockDto.data.split(sockDto.seperator)[1]

        fileRecieveService.fileRecieveStart(fileName, fileSize)

        fileRecieveProcessingExcutor.start(fileName, fileSize.toLong())
    }
}