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

        val datas = sockDto.data.split(sockDto.seperator)

        val fileName: String = datas[0]
        val fileSize: String = datas[1]
        val requesterId: String = datas[2]


        fileRecieveService.fileRecieveStart(fileName, fileSize, requesterId)

        fileRecieveProcessingExcutor.start(fileName, fileSize.toLong())
    }
}