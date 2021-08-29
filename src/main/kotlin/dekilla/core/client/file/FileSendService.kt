package dekilla.core.client.file;

import dekilla.core.client.ClientManager
import dekilla.core.client.view.MainView
import dekilla.core.container.ClientContainer
import dekilla.core.container.UtilConatiner
import dekilla.core.container.ViewContainer
import dekilla.core.domain.SockDto
import dekilla.core.util.socket.FileSendProcessingExcutor

import java.io.File
import java.io.FileInputStream
import java.io.IOException

public class FileSendService {

    private val clientManager: ClientManager = ClientContainer.clientManager()
    private val mainView: MainView = ViewContainer.mainView()
    private val fspl: FileSendProcessingExcutor = UtilConatiner.fileSendProcessingExcutor()

    fun fileSendPermissionRequest() {
        val sockId: String = clientManager.wrappedSocket!!.id
        val targetToken: String = clientManager.connectedId

        try {
            val sockDto: SockDto = SockDto(
                "FILE_SEND_PERMISSION_CTOS",
                "#",
                "${sockId}#${targetToken}",
                null
            )

            clientManager.sendData(sockDto)

        } catch (e: IOException) {
            throw e
        }
    }

    fun startSendFile(file: File) {
        val sockId: String = clientManager.wrappedSocket!!.id
        val targetToken: String = clientManager.connectedId

        try {
            val sockDto: SockDto = SockDto(
                "FILE_SEND_START_CTOS",
                "#",
                "${sockId}#${targetToken}#${file.name}#${file.length()}",
                null
            )

            clientManager.sendData(sockDto)

            ClientContainer.fileController().isSending = true
            ClientContainer.fileController().isLinked = true

        } catch (e: IOException) {
            throw e
        }
    }

    fun sendFile(file: File) {
        val sockId: String = clientManager.wrappedSocket!!.id
        val targetToken: String = clientManager.connectedId

        val fileName: String = file.name
        val fileInputStream = FileInputStream(file)

        val fileSize: Long = file.length()
        var totalReadBytes: Long = 0
        var buffer: ByteArray? = ByteArray(4 * DEFAULT_BUFFER_SIZE)
        var readBytes: Int

        try {
            fspl.start(fileName, fileSize)

            while ((fileInputStream.read(buffer).also {
                    readBytes = it
                }) > 0) {
                val sockDto: SockDto = SockDto(
                    "FILE_SEND_CTOS",
                    "#",
                    "${sockId}#${targetToken}",
                    buffer
                )

                if (!ClientContainer.fileController().isLinked) break

                clientManager.sendData(sockDto)

                totalReadBytes += readBytes
                fspl.excute(fileName, readBytes, totalReadBytes, fileSize)
            }
        } catch (e: IOException) {
            throw e
        } finally {
            fspl.end(fileName)
            fileInputStream.close()
        }
    }

    fun endSendFile() {
        val sockId: String = clientManager.wrappedSocket!!.id
        val targetToken: String = clientManager.connectedId

        try {
            val sockDto: SockDto = SockDto(
                "FILE_SEND_END_CTOS",
                "#",
                "${sockId}#${targetToken}",
                null
            )

            clientManager.sendData(sockDto)

            ClientContainer.fileController().isSending = false
            ClientContainer.fileController().isLinked = false
        } catch (e: IOException) {
            throw e
        } finally {
        }
    }
}
