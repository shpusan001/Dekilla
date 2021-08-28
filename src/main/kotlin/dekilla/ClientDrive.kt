package dekilla

import dekilla.core.client.view.HostConnectView
import dekilla.core.client.view.MainView
import dekilla.core.container.ViewContainer

class ClientDrive {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val hostConnectView: HostConnectView = ViewContainer.hostConnectView()
            hostConnectView.create()
        }
    }
}

