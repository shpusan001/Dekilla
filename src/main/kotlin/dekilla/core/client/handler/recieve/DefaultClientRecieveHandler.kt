package dekilla.core.client.handler.recieve

import dekilla.core.client.handler.recieve.excutor.ClientRecieveExcutor
import dekilla.core.client.handler.recieve.excutor.NoticeExcutor
import dekilla.core.domain.SockDto
import org.springframework.stereotype.Service

@Service
class DefaultClientRecieveHandler : ClientRecieveHandler {
    private val commandRepository: HashMap<String, ClientRecieveExcutor> = HashMap()

    constructor() {
        commandRepository.put("NOTICE", NoticeExcutor())
    }

    override fun addCommand(command: String, excutor: ClientRecieveExcutor) {
        commandRepository.put(command, excutor)
    }

    override fun process(sockDto: SockDto) {
        if (commandRepository.containsKey(sockDto.command)) {
            commandRepository.get(sockDto.command)!!.excute(sockDto)
        }
    }
}