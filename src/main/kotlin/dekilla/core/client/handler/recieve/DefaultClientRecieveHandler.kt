package dekilla.core.client.handler.recieve

import dekilla.core.client.handler.recieve.excutor.*
import dekilla.core.domain.SockDto

class DefaultClientRecieveHandler : ClientRecieveHandler {
    private val commandRepository: HashMap<String, ClientRecieveExcutor> = HashMap()

    constructor() {
        commandRepository.put("NOTICE", NoticeExcutor())
        commandRepository.put("CONNECT_WITH_TOKEN_ASK", ConnectWithTokenAskExcutor())
        commandRepository.put("CONNECT_WITH_TOKEN_YES", ConnectWithTokenYesExcutor())
        commandRepository.put("CONNECT_WITH_TOKEN_NO", ConnectWithTokenNoExcutor())
        commandRepository.put("CONNECT_WITH_TOKEN_FAILD", ConnectWithTokenFailedExcutor())
        commandRepository.put("FILE_SEND_FAILD", FileSendFaildExcutor())
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