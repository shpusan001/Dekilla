package dekilla.core.util.socketutil

interface FileProcessingExcutor {

    fun start()
    fun excute(totalReadBytes: Long, fileSize: Long)
    fun end()
}