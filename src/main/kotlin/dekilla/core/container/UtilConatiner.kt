package dekilla.core.container

import dekilla.core.util.generator.IdGenerator
import dekilla.core.util.generator.NumberIdGenerator
import dekilla.core.util.socket.FileRecieveProcessingExcutor
import dekilla.core.util.socket.FileSendProcessingExcutor
import dekilla.core.util.socket.SocketUtil

class UtilConatiner {

    companion object {
        private var socketUtil: SocketUtil? = null
        private var idGenerator: IdGenerator? = null
        private var fileRecieveProcessingExcutor: FileRecieveProcessingExcutor? = null
        private var fileSendProcessingExcutor: FileSendProcessingExcutor? = null

        fun socketUtil(): SocketUtil {
            if (socketUtil == null)
                socketUtil = SocketUtil()
            return socketUtil!!
        }

        fun idGenerator(): IdGenerator {
            if (idGenerator == null)
                idGenerator = NumberIdGenerator()
            return idGenerator!!
        }

        fun fileRecieveProcessingExcutor(): FileRecieveProcessingExcutor {
            if (fileRecieveProcessingExcutor == null)
                fileRecieveProcessingExcutor = FileRecieveProcessingExcutor()
            return fileRecieveProcessingExcutor!!
        }

        fun fileSendProcessingExcutor(): FileSendProcessingExcutor {
            if (fileSendProcessingExcutor == null)
                fileSendProcessingExcutor = FileSendProcessingExcutor()
            return fileSendProcessingExcutor!!
        }
    }
}