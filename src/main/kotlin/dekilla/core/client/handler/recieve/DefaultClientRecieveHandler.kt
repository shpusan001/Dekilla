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
        commandRepository.put("FILE_SEND_STOC", FileSendStoCExcutor())
        commandRepository.put("FILE_SEND_START_STOC", FileSendStartStocExcutor())
        commandRepository.put("FILE_SEND_END_STOC", FileSendEndStocExcutor())
        commandRepository.put("FILE_SEND_PERMISSION_YES_STOC", FileSendPermissionYesExcutor())
        commandRepository.put("FILE_SEND_PERMISSION_NO_STOC", FileSendPermissionNoExcutor())
        commandRepository.put("DISCONNECT_STOC", DisconnectExcutor())
        commandRepository.put("FILE_SEND_END_FAILED_STOC", FileSendEndFaildExcutor())
    }

    override fun addCommand(command: String, excutor: ClientRecieveExcutor) {
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