package dekilla.core.server.repository

import dekilla.core.container.ServerContainer

class HashmapDataSendPermissionRepository : DataSendPermissionRepository {


    val sendMap: HashMap<String, String> = HashMap()
    val recieveMap: HashMap<String, String> = HashMap()

    override fun add(senderId: String, recieverId: String): Boolean {
        if (sendMap.containsKey(senderId)) {
            remove(senderId)
            sendMap.put(senderId, recieverId)
            recieveMap.put(recieverId, senderId)
            return false
        } else {
            sendMap.put(senderId, recieverId)
            recieveMap.put(recieverId, senderId)
            return true
        }
    }

    override fun remove(senderId: String) {
        recieveMap.remove(sendMap.get(senderId))
        sendMap.remove(senderId)
    }

    override fun authorization(senderId: String, recieverId: String): Boolean {
        return sendMap.get(senderId) == recieverId || recieveMap.get(senderId) == recieverId
    }
}