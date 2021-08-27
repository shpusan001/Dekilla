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
    private val fpl: FileSendProcessingExcutor = UtilConatiner.fileSendProcessingExcutor()

    fun fileSendPermissionRequest() {
        val sockId: String = clientManager.wrappedSocket!!.id
        val targetToken: String = clientManager.connectedId

        try {
            fpl.start()

            val sockDto: SockDto = SockDto(
                "FILE_SEND_PERMISSION_CTOS",
                "#",
                "${sockId}#${targetToken}",
                null
            )

            clientManager.sendData(sockDto)

        } catch (e: IOException) {
            throw e
        } finally {
            fpl.end()
        }
    }

    fun startSendFile(file: File) {
        val sockId: String = clientManager.wrappedSocket!!.id
        val targetToken: String = clientManager.connectedId

        try {
            fpl.start()

            val sockDto: SockDto = SockDto(
                "FILE_SEND_START_CTOS",
                "#",
                "${sockId}#${targetToken}#${file.name}#${file.length()}",
                null
            )

            clientManager.sendData(sockDto)

        } catch (e: IOException) {
            throw e
        } finally {
            fpl.end()
        }
    }

    fun sendFile(file: File) {
        val sockId: String = clientManager.wrappedSocket!!.id
        val targetToken: String = clientManager.connectedId

        val fileInputStream = FileInputStream(file)

        val fileSize: Long = file.length()
        var totalReadBytes: Long = 0
        var buffer: ByteArray? = ByteArray(DEFAULT_BUFFER_SIZE)
        var readBytes: Int

        try {
            fpl.start()

            while ((fileInputStream.read(buffer).also {
                    readBytes = it
                }) > 0) {
                val sockDto: SockDto = SockDto(
                    "FILE_SEND_CTOS",
                    "#",
                    "${sockId}#${targetToken}",
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

    fun endSendFile() {
        val sockId: String = clientManager.wrappedSocket!!.id
        val targetToken: String = clientManager.connectedId

        try {
            fpl.start()

            val sockDto: SockDto = SockDto(
                "FILE_SEND_END_CTOS",
                "#",
                "${sockId}#${targetToken}",
                null
            )

            clientManager.sendData(sockDto)

        } catch (e: IOException) {
            throw e
        } finally {
            fpl.end()
        }
    }
}
