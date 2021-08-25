package dekilla.core.server.handler.recieve.excutor

import dekilla.core.domain.SockDto
import dekilla.core.util.Log.DekillaLog

class NoticeExcutor : ServerRecieveExcutor {
    override fun excute(sockDto: SockDto) {
        DekillaLog.log("Server: " + sockDto.data)
    }

}