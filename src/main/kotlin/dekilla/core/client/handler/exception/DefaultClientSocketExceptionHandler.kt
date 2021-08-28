package dekilla.core.client.handler.exception

import dekilla.core.util.Log.DekillaLog
import dekilla.core.util.socket.WrappedSocket
import org.springframework.stereotype.Service
import javax.swing.JOptionPane


class DefaultClientSocketExceptionHandler : ClientSocketExceptionHandler {

    override fun connectionFaild() {
        JOptionPane.showMessageDialog(null, "Failed to connect to server.")
    }

    override fun connectionLost(wrappedSocket: WrappedSocket) {
        JOptionPane.showMessageDialog(
            null,
            "My token: ${wrappedSocket.id}, Disconnected from the server. Restart Dekilla."
        )
    }

    override fun ipInputNotNumber() {
        JOptionPane.showMessageDialog(null, "Enter number in the Ip.")
    }
}