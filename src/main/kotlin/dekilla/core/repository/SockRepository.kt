package dekilla.core.repository

import java.net.Socket

interface SockRepository {

    /**
     * @param key
     * @param socket
     * @return Boolean
     * 키가 중복되어 덮어쓰면 false, 중복되지 않고 추가되면 true
     */
    fun add(key: String, socket: Socket): Boolean

    /**
     * @param key
     * @return Boolean
     * 키가 포함되어 있지안으면 false, 키가 포함되어 있으면 true
     */
    fun isContain(key: String): Boolean

    /**
     * @param key
     * @return Socket
     * 해당 키에 속하는 소켓 반환
     */
    fun get(key: String): Socket?

    /**
     * @param key
     * @return Boolean
     * 삭제 대상이 없으면 false, 제데로 삭제되면 true
     */
    fun remove(key: String): Boolean
}