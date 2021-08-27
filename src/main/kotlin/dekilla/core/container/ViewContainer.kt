package dekilla.core.container

import dekilla.core.client.view.MainView

class ViewContainer {

    companion object {

        private var mainView: MainView? = null

        fun mainView(): MainView {
            if (mainView == null)
                mainView = MainView()
            return mainView!!
        }
    }
}