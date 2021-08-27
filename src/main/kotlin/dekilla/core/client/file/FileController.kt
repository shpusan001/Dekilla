package dekilla.core.client.file

import dekilla.core.client.ClientManager
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.FileSendProcessingExcutor
import java.io.File
import java.io.FileInputStream
import java.io.IOException

class FileController {

    private val clientManager: ClientManager = ClientContainer.clientManager()
    private val fpl: FileSendProcessingExcutor = UtilConatiner.fileSendProcessingExcutor()

    fun sendFile(file: File) {
        val fileInputStream = FileInputStream(file)

        val fileSize: Long = file.length()
        var totalReadBytes: Long = 0
        var buffer: ByteArray? = ByteArray(DEFAULT_BUFFER_SIZE)
        var readBytes: Int

        try {
            fpl.start()

            while ((fileInputStream.read(buffer).also { readBytes = it }) > 0) {
                val sockDto: SockDto = SockDto(
                    "FILE_MODERATOR",
                    "#",
                    clientManager.wrappedSocket!!.id + "#" + clientManager.connectedId,
                    buffer
                )

                clientManager.sendData(sockDto)

                totalReadBytes += readBytes
                fpl.excute(totalReadBytes, fileSize)
            }
        } catch (e: IOException) {
            throw e
        } finally {
            fpl.end()
            fileInputStream.close()
        }
    }

    fun readFile(file: File) {
        
    }
}