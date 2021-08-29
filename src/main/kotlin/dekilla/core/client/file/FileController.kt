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
    var isSending: Boolean = false
    var isLinked: Boolean = false

    fun sendPermissionRequest() {
        if (currentFile != null) {
            if (currentFile!!.exists()) {
                fileSendService.fileSendPermissionRequest()
            } else {
                JOptionPane.showMessageDialog(null, "The selected file to transfer does not exist.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No file selected for transfer.")
        }
    }

    fun sendFile() {
        Thread(Runnable {
            fileSendService.startSendFile(currentFile!!)
            fileSendService.sendFile(currentFile!!)
            fileSendService.endSendFile()
        }).start()
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