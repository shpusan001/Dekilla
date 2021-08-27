package dekilla.core.server.handler.recieve

import dekilla.core.client.handler.recieve.excutor.FileSendPermissionYesExcutor
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
        commandRepository.put("FILE_SEND_CTOS", FileSendCtosExcutor())
        commandRepository.put("FILE_SEND_START_CTOS", FileSendStartCtosExcutor())
        commandRepository.put("FILE_SEND_END_CTOS", FileSendEndCtosExcutor())
        commandRepository.put("FILE_SEND_PERMISSION_CTOS", FileSendPermissionExcutor())
    }

    override fun addCommand(command: String, excutor: ServerRecieveExcutor) {
        commandRepository.put(command, excutor)
    }

    override fun process(sockDto: SockDto) {
        try {
            if (commandRepository.containsKey(sockDto.command)) {
                commandRepository.get(sockDto.command)!!.excute(sockDto)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}