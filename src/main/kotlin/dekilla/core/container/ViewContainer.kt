package dekilla.core.container

import dekilla.core.client.view.HostConnectView
import dekilla.core.client.view.MainView

class ViewContainer {

    companion object {

        private var mainView: MainView? = null
        private var hostConnectView: HostConnectView? = null

        fun mainView(): MainView {
            if (mainView == null)
                mainView = MainView()
            return mainView!!
        }

        fun hostConnectView(): HostConnectView {
            if (hostConnectView == null)
                hostConnectView = HostConnectView()
            return hostConnectView!!
        }
    }
}