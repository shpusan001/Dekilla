package dekilla.core.client.file

import dekilla.core.client.ClientManager
import dekilla.core.container.ClientContainer
import dekilla.core.domain.SockDto
import java.io.File
import java.io.IOException
import javax.swing.JOptionPane
import javax.swing.JPanel

class FileController {

    private val fileSendService: FileSendService = ClientContainer.fileService()
    private val clientManager: ClientManager = ClientContainer.clientManager()

    var currentFile: File? = null
    var downloadFolder: File? = null

    fun sendPermissionRequest() {
        if (currentFile != null) {
            if (currentFile!!.exists()) {
                fileSendService.fileSendPermissionRequest()
            } else {
                JOptionPane.showMessageDialog(null, "선택한 전송할 파일이 존재하지 않습니다.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "전송할 파일이 선택하지 않았습니다")
        }
    }

    fun sendFile() {
        fileSendService.startSendFile(currentFile!!)
        fileSendService.sendFile(currentFile!!)
        fileSendService.endSendFile()
    }

    fun sendFile(file: File) {
        currentFile = file

        if (currentFile != null) {
            if (currentFile!!.exists()) {
                fileSendService.startSendFile(currentFile!!)
                fileSendService.sendFile(currentFile!!)
                fileSendService.endSendFile()
            }
        }
    }
}