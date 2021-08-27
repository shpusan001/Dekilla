package dekilla.core.client.file

import dekilla.core.container.ClientContainer
import java.io.File
import javax.swing.JOptionPane
import javax.swing.JPanel

class FileController {

    val fileSendService: FileSendService = ClientContainer.fileService()

    var currentFile: File? = null

    var downloadFolder: File? = null

    fun sendFile() {
        if (currentFile != null && downloadFolder != null) {
            if (currentFile!!.exists() && downloadFolder!!.exists()) {
                fileSendService.startSendFile(currentFile!!)
                fileSendService.sendFile(currentFile!!)
                fileSendService.endSendFile()
            } else {
                JOptionPane.showMessageDialog(null, "선택한 전송할 파일이나 다운로드폴더가 존재하지 않습니다.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "전송할 파일과 다운로드폴더를 선택하지 않았습니다")
        }
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