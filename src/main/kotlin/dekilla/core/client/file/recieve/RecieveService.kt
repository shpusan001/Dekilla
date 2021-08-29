package dekilla.core.client.file.recieve

import dekilla.core.client.file.FileController
import dekilla.core.container.ClientContainer
import java.io.File
import java.io.FileOutputStream

class RecieveService {

    private val fileController: FileController = ClientContainer.fileController()
    
    constructor(fileName: String, fileSize: String) {
        this.fileName = fileName
        this.fileSize = fileSize.toLong()
        this.folderPath = fileController.downloadFolder!!.path
        file = File("${folderPath}\\${fileName}")
        fileOutputStream = file!!.outputStream()
    }

    var file: File? = null
    var fileName: String = "null"
    var folderPath: String = "null"

    var fileOutputStream: FileOutputStream? = null

    var fileSize: Long = 0
    var currentFileSize: Long = 0
}