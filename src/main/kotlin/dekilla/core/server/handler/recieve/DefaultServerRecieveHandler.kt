package dekilla.core.server.handler.recieve

import dekilla.core.client.handler.recieve.excutor.ConnectWithTokenFailedExcutor
import dekilla.core.domain.SockDto
import dekilla.core.server.handler.recieve.excutor.*
import org.springframework.stereotype.Service

@Service
class DefaultServerRecieveHandler : ServerRecieveHandler {

    private val commandRepository: HashMap<String, ServerRecieveExcutor> = HashMap()

    constructor() {
        commandRepository.put("NOTICE", NoticeExcutor())
        commandRepository.put("CONNECT_WITH_TOKEN", ConnectWithTokenExcutor())
        commandRepository.put("CONNECT_WITH_TOKEN_ASK_YES", ConnectWithTokenAskYesExcutor())
        commandRepository.put("CONNECT_WITH_TOKEN_ASK_NO", ConnectWithTokenAskNoExcutor())
        commandRepository.put("FILE_MODERATOR", FileModeratorExcutor())
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