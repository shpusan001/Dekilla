package dekilla

import dekilla.core.AppConfig
import dekilla.core.container.ServerContainer
import dekilla.core.server.ServerManager
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.stereotype.Component


class ServerDirve {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val serverManager: ServerManager = ServerContainer.serverManager()

            serverManager.bind(33333)
            serverManager.accept()
            serverManager.processing()
        }
    }
}

