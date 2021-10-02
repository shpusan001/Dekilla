package dekilla

import dekilla.core.container.ServerContainer
import dekilla.core.server.ServerManager
import java.io.BufferedReader
import java.io.InputStreamReader


class ServerDirve {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val serverManager: ServerManager = ServerContainer.serverManager()
            val br: BufferedReader = BufferedReader(InputStreamReader(System.`in`))

            while (true) {
                try {
                    print("Setting port (0~65535):")
                    var port: Int = br.readLine().toInt()

                    if (!(port in 0..65535)) throw Exception()

                    ServerManager.port = port
                    break
                } catch (e: Exception) {
                    println("Wrong setting")
                    continue
                }
            }

            println("Dekilla server start")
            serverManager.bind(ServerManager.port)
            serverManager.accept()
            serverManager.processing()
        }
    }
}

