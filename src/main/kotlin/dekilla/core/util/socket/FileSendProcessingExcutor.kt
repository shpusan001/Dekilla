package dekilla.core.util.socket

import dekilla.core.client.view.MainView
import dekilla.core.container.ViewContainer
import javax.swing.JScrollBar
import javax.swing.JScrollPane
import javax.swing.JTextArea

class FileSendProcessingExcutor : FileProcessingExcutor {

    private val mainView: MainView = ViewContainer.mainView()
    private val Ta_status: JTextArea = mainView.ta_status

    override fun start(fileName: String, fileSize: Long) {
        Ta_status.append("[Send start] File name: [${fileName}], File size: ${fileSize}" + "\n")
        Ta_status.setCaretPosition(Ta_status.getDocument().getLength())
    }


    override fun excute(fileName: String, readBytes: Int, totalReadBytes: Long, fileSize: Long) {
        Ta_status.append("[Sending...] File name: [${fileName}], Progress(kb): [${totalReadBytes / 1000}/${fileSize / 1000}]" + "\n")
        Ta_status.setCaretPosition(Ta_status.getDocument().getLength())
    }

    override fun end(fileName: String) {
        Ta_status.append("[Send end] File name: [${fileName}]" + "\n");
        Ta_status.setCaretPosition(Ta_status.getDocument().getLength());
    }

}