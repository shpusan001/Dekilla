package dekilla.core.client.file

import dekilla.core.client.file.recieve.RecieveService
import dekilla.core.client.view.MainView
import dekilla.core.container.ClientContainer
import dekilla.core.container.ViewContainer
import java.io.File
import java.io.FileOutputStream

class FileRecieveService {

    val recieveServiceMap: HashMap<String, RecieveService> = HashMap()

    fun fileRecieveStart(fileName: String, fileSize: String, requesterId: String) {
        recieveServiceMap.put(requesterId, RecieveService(fileName, fileSize))
    }

    fun getRecieveService(requesterId: String): RecieveService? {
        return recieveServiceMap.get(requesterId)!!
    }

    fun fileRecieveEnd(requesterId: String) {
        recieveServiceMap.get(requesterId)!!.fileOutputStream!!.close()
        recieveServiceMap.remove(requesterId)
    }
}