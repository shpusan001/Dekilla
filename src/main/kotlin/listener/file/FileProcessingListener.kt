package listener.file

interface FileProcessingListener {

    fun start()
    fun excute(totalReadBytes: Long, fileSize: Long)
    fun end()
}