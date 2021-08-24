package dekilla.core.repository

import org.springframework.stereotype.Repository
import java.net.Socket

@Repository
class HashMapSockRepository : SockRepository {

    private val repository = HashMap<String, Socket>()

    override fun add(key: String, socket: Socket): Boolean {
        if (repository.containsKey(key)) {
            repository.remove(key)
            repository.put(key, socket)
            return false
        } else {
            repository.put(key, socket)
            return true
        }
    }

    override fun isContain(key: String): Boolean {
        return repository.containsKey(key)
    }

    override fun get(key: String): Socket? {
        return repository.get(key)
    }

    override fun remove(key: String): Boolean {
        if (repository.containsKey(key)) {
            repository.remove(key)
            return true
        } else {
            return false
        }
    }
}