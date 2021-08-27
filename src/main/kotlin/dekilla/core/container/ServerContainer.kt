package dekilla.core.container

import dekilla.core.server.ServerManager
import dekilla.core.server.handler.exception.DefaultServerSocketExceptionHandler
import dekilla.core.server.handler.exception.ServerSocketExceptionHandler
import dekilla.core.server.handler.recieve.DefaultServerRecieveHandler
import dekilla.core.server.handler.recieve.ServerRecieveHandler
import dekilla.core.server.repository.DataSendPermissionRepository
import dekilla.core.server.repository.HashmapDataSendPermissionRepository
import dekilla.core.server.repository.HashmapSockRepository
import dekilla.core.server.repository.SockRepository
import dekilla.core.util.socket.SocketUtil

class ServerContainer {

    companion object {

        private var serverManager: ServerManager? = null
        private var serverSocketExceptionHandler: ServerSocketExceptionHandler? = null
        private var serverRecieveHandler: ServerRecieveHandler? = null
        private var sockRepository: SockRepository? = null
        private var dataSendPermissionRepository: DataSendPermissionRepository? = null

        fun serverManager(): ServerManager {
            if (serverManager == null)
                serverManager = ServerManager()
            return serverManager!!
        }

        fun serverSocketExceptionHandler(): ServerSocketExceptionHandler {
            if (serverSocketExceptionHandler == null)
                serverSocketExceptionHandler = DefaultServerSocketExceptionHandler()
            return serverSocketExceptionHandler!!
        }

        fun serverRecieveHandler(): ServerRecieveHandler {
            if (serverRecieveHandler == null)
                serverRecieveHandler = DefaultServerRecieveHandler()
            return serverRecieveHandler!!
        }

        fun sockRepository(): SockRepository {
            if (sockRepository == null)
                sockRepository = HashmapSockRepository()
            return sockRepository!!
        }

        fun dataSendPermissionRepository(): DataSendPermissionRepository {
            if (dataSendPermissionRepository == null)
                dataSendPermissionRepository = HashmapDataSendPermissionRepository()
            return dataSendPermissionRepository!!
        }
    }
}