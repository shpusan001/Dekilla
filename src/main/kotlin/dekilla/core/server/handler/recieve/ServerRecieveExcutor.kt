package dekilla.core.server.handler.recieve

import dekilla.core.domain.SockDto

interface ServerRecieveExcutor {
    fun excute(sockDto: SockDto)
}