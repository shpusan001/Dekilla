package util

import listener.file.FileProcessingListener
import listener.file.FileSendProcessingListener
import org.springframework.stereotype.Component
import java.io.*
import java.net.Socket

@Component
class SocketUtil {

    private val DEFAULT_BUFFER_SIZE: Int = 10000

    /**
     * @param socket
     * @param obj
     * @throws IOException
     */
    fun send(socket: Socket, obj: Any) {
        val sockOutputStream = socket.getOutputStream()
        val sockObjectOutputStream = ObjectOutputStream(sockOutputStream)
        try {
            sockObjectOutputStream.writeObject(obj)
        } catch (e: IOException) {
            throw e
        } finally {
            sockOutputStream.close()
            sockObjectOutputStream.close()
        }
    }

    /**
     * @param socket
     * @throws IOException
     * @return Any
     */
    fun recieve(socket: Socket): Any {
        val sockInputStream = socket.getInputStream()
        val sockObjectInputStream = ObjectInputStream(sockInputStream)
        try {
            val obj = sockObjectInputStream.readObject()
            return obj
        } catch (e: IOException) {
            throw e
        } finally {
            sockInputStream.close()
            sockObjectInputStream.close()
        }
    }

    /**
     * @param socket
     * @param file
     * @param fileProcessingListener
     * @throws IOException
     */
    fun sendFile(socket: Socket, file: File, fpl: FileProcessingListener) {
        val fileInputStream = FileInputStream(file)
        val sockOutputStream = socket.getOutputStream()

        val fileSize: Long = file.length()
        var totalReadBytes: Long = 0
        var buffer: ByteArray? = ByteArray(DEFAULT_BUFFER_SIZE)
        var readBytes: Int

        try {
            fpl.start()

            while ((fileInputStream.read(buffer).also { readBytes = it }) > 0) {
                sockOutputStream.write(buffer, 0, readBytes)
                totalReadBytes += readBytes
                fpl.excute(totalReadBytes, fileSize)
            }
        } catch (e: IOException) {
            throw e
        } finally {
            fileInputStream.close()
            sockOutputStream.close()
            fpl.end()
        }
    }

    /**
     * @param socket
     * @param path
     * @param fileSize
     * @param fileProcessingListener
     * @throws IOException
     */
    fun recieveFile(socket: Socket, path: String, fileSize: Long, fpl: FileProcessingListener) {
        val fileOutputStream = FileOutputStream(path)
        val sockInputStream = socket.getInputStream()

        var buffer: ByteArray? = ByteArray(DEFAULT_BUFFER_SIZE)
        var totalReadBytes: Long = 0
        var readBytes: Int
        try {
            fpl.start()

            while ((sockInputStream.read(buffer).also { readBytes = it }) != -1) {
                fileOutputStream.write(buffer, 0, readBytes)
                totalReadBytes += readBytes
                fpl.excute(totalReadBytes, fileSize)
            }
        } catch (e: IOException) {
            throw  e
        } finally {
            fileOutputStream.close()
            sockInputStream.close()
            fpl.end()
        }
    }
}