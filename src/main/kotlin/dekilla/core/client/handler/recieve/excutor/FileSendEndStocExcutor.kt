package dekilla.core.client.handler.recieve.excutor

import dekilla.core.client.file.FileRecieveService
import dekilla.core.container.ClientContainer
import dekilla.core.domain.SockDto

class FileSendEndStocExcutor : ClientRecieveExcutor {

    val fileRecieveService: FileRecieveService = ClientContainer.fileRecieveService()

    override fun excute(sockDto: SockDto) {
        fileRecieveService.fileRecieveEnd()
    }
}