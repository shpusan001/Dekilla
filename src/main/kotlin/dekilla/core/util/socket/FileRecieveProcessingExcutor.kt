package dekilla.core.util.socket;

import dekilla.core.client.view.MainView
import dekilla.core.container.ViewContainer
import javax.swing.JTextArea

class FileRecieveProcessingExcutor : FileProcessingExcutor {
    private val mainView: MainView = ViewContainer.mainView()
    private val Ta_status: JTextArea = mainView.ta_status

    override fun start(fileName: String, fileSize: Long) {
        Ta_status.append("[Download start] File name: [${fileName}], File size: ${fileSize}" + "\n")
        Ta_status.setCaretPosition(Ta_status.getDocument().getLength())
    }


    override fun excute(fileName: String, readBytes: Int, totalReadBytes: Long, fileSize: Long) {
        Ta_status.append("[Downloading...] File name: [${fileName}], Progress: [${totalReadBytes}/${fileSize}]" + "\n")
        Ta_status.setCaretPosition(Ta_status.getDocument().getLength())
    }

    override fun end(fileName: String) {
        Ta_status.append("[Download end] File name: [${fileName}]" + "\n");
        Ta_status.setCaretPosition(Ta_status.getDocument().getLength());
    }
}
