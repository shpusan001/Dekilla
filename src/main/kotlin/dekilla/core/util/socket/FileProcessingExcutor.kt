package dekilla.core.util.socket

interface FileProcessingExcutor {

    fun start()
    fun excute(totalReadBytes: Long, fileSize: Long)
    fun end()
}