package dekilla.core.server.handler

import dekilla.core.domain.SockDto

interface ServerRecieveHandler {
    fun addCommand(command: String, excutor: ServerRecieveExcutor)
    fun process(sockDto: SockDto)
}