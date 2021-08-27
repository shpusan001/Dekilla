package dekilla.core.container

import dekilla.core.client.ClientManager
import dekilla.core.client.file.FileController
import dekilla.core.client.file.FileRecieveService
import dekilla.core.client.file.FileSendService
import dekilla.core.client.handler.exception.ClientSocketExceptionHandler
import dekilla.core.client.handler.exception.DefaultClientSocketExceptionHandler
import dekilla.core.client.handler.recieve.ClientRecieveHandler
import dekilla.core.client.handler.recieve.DefaultClientRecieveHandler

class ClientContainer {

    companion object {

        private var clientManager: ClientManager? = null
        private var clientRecieveHandler: ClientRecieveHandler? = null
        private var clientSocketExceptionHandler: ClientSocketExceptionHandler? = null
        private var fileController: FileController? = null
        private var fileSendService: FileSendService? = null
        private var fileRecieveService: FileRecieveService? = null

        fun clientManager(): ClientManager {
            if (clientManager == null)
                clientManager = ClientManager()
            return clientManager!!
        }

        fun clientRecieveHandler(): ClientRecieveHandler {
            if (clientRecieveHandler == null)
                clientRecieveHandler = DefaultClientRecieveHandler()
            return clientRecieveHandler!!
        }

        fun clientSocketExceptionHandler(): ClientSocketExceptionHandler {
            if (clientSocketExceptionHandler == null)
                clientSocketExceptionHandler = DefaultClientSocketExceptionHandler()
            return clientSocketExceptionHandler!!
        }

        fun fileController(): FileController {
            if (fileController == null)
                fileController = FileController()
            return fileController!!
        }

        fun fileService(): FileSendService {
            if (fileSendService == null)
                fileSendService = FileSendService()
            return fileSendService!!
        }

        fun fileRecieveService(): FileRecieveService {
            if (fileRecieveService == null)
                fileRecieveService = FileRecieveService()
            return fileRecieveService!!
        }
    }
}