package dekilla.core.server.repository

interface DataSendPermissionRepository {

    fun add(senderId: String, recieverId: String): Boolean

    fun remove(senderId: String)

    fun authorization(senderId: String, recieverId: String): Boolean
}