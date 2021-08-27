package dekilla.core.client.file

import dekilla.core.client.view.MainView
import dekilla.core.container.ClientContainer
import dekilla.core.container.ViewContainer
import java.io.File
import java.io.FileOutputStream

class FileRecieveService {

    val fileController: FileController = ClientContainer.fileController()

    var file: File? = null
    var fileName: String = "null"
    var folderPath: String = "null"

    var fileOutputStream: FileOutputStream? = null

    var fileSize: Long = 0
    var currentFileSize: Long = 0

    fun fileRecieveStart(fileName: String, fileSize: String) {
        this.fileName = fileName
        this.fileSize = fileSize.toLong()
        this.folderPath = fileController.downloadFolder!!.path


        file = File("${folderPath}\\${fileName}")
        fileOutputStream = file!!.outputStream()
    }

    fun fileRecieveEnd() {
        currentFileSize = 0
        fileOutputStream!!.close()
    }
}