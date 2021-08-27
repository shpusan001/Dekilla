package dekilla

import dekilla.core.client.view.MainView
import dekilla.core.container.ViewContainer

class ClientDrive {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val mainView: MainView = ViewContainer.mainView()
            mainView.create()
        }
    }
}

