package dekilla.core.util.socket

interface FileProcessingExcutor {

    fun start(fileName: String, fileSize: Long)
    fun excute(fileName: String, readBytes: Int, totalReadBytes: Long, fileSize: Long)
    fun end(fileName: String)
}