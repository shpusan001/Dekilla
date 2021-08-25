package dekilla.core.client.handler.recieve

import dekilla.core.domain.SockDto
import dekilla.core.server.handler.recieve.ServerRecieveExcutor

interface ClientRecieveHandler {
    fun addCommand(command: String, excutor: ClientRecieveExcutor)
    fun process(sockDto: SockDto)
}