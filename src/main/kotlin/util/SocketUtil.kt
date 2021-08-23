package util

import org.springframework.stereotype.Component
import java.io.*
import java.net.Socket

@Component
class SocketUtil {

    private val DEFAULT_BUFFER_SIZE : Int = 10000;

    /**
     * @param socket
     * @param obj
     * @throws IOException
     */
    fun send(socket:Socket, obj:Any) {
        val sockOutputStream = socket.getOutputStream()
        val sockObjectOutputStream = ObjectOutputStream(sockOutputStream)
        try {
            sockObjectOutputStream.writeObject(obj)
        }catch (e:IOException){
            throw e
        }finally {
            sockOutputStream.close()
            sockObjectOutputStream.close()
        }
    }

    /**
     * @param socket
     * @throws IOException
     * @return Any
     */
    fun recieve(socket: Socket):Any{
        val sockInputStream = socket.getInputStream()
        val sockObjectInputStream = ObjectInputStream(sockInputStream)
        try {
            val obj = sockObjectInputStream.readObject()
            return obj
        }catch (e:IOException){
            throw e
        }finally {
            sockInputStream.close()
            sockObjectInputStream.close()
        }
    }

    /**
     * @param socket
     * @param file
     * @throws IOException
     */
    fun sendFile(socket: Socket, file:File){
        val fileInputStream = FileInputStream(file)

        val sockOutputStream = socket.getOutputStream()
        val fileSize = file.length()
        var totalReadBytes = 0
        var buffer:ByteArray?= ByteArray(DEFAULT_BUFFER_SIZE)
        var readBytes:Int

        try {
            while((fileInputStream.read(buffer).also { readBytes = it }) > 0){
                sockOutputStream.write(buffer, 0, readBytes)
                totalReadBytes += readBytes
            }
        }catch (e:IOException){
            throw e
        }finally {
            fileInputStream.close()
            sockOutputStream.close()
        }
    }

    fun recieveFile(socket: Socket){

    }
}