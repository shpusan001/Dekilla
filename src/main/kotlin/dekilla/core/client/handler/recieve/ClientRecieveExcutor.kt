package dekilla.core.client.handler.recieve

import dekilla.core.domain.SockDto

interface ClientRecieveExcutor {
    fun excute(sockDto: SockDto)
}