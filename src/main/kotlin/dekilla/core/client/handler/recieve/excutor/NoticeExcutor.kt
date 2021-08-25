package dekilla.core.client.handler.recieve.excutor

import dekilla.core.domain.SockDto
import dekilla.core.util.Log.DekillaLog

class NoticeExcutor : ClientRecieveExcutor {
    override fun excute(sockDto: SockDto) {
        DekillaLog.log("Client: " + sockDto.data)
    }
}