package dekilla.core.server.handler.recieve

import dekilla.core.domain.SockDto
import dekilla.core.server.handler.recieve.excutor.NoticeExcutor
import dekilla.core.server.handler.recieve.excutor.ServerRecieveExcutor
import org.springframework.stereotype.Service

@Service
class DefaultServerRecieveHandler : ServerRecieveHandler {

    private val commandRepository: HashMap<String, ServerRecieveExcutor> = HashMap()

    constructor() {
        commandRepository.put("NOTICE", NoticeExcutor())
    }

    override fun addCommand(command: String, excutor: ServerRecieveExcutor) {
        commandRepository.put(command, excutor)
    }

    override fun process(sockDto: SockDto) {
        if (commandRepository.containsKey(sockDto.command)) {
            commandRepository.get(sockDto.command)!!.excute(sockDto)
        }
    }
}