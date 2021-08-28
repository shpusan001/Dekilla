package dekilla.core.client.view;

import dekilla.core.client.ClientManager;
import dekilla.core.client.handler.exception.ClientSocketExceptionHandler;
import dekilla.core.container.ClientContainer;
import dekilla.core.container.ViewContainer;
import dekilla.core.util.socket.WrappedSocket;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HostConnectView {

    private JPanel container;
    private JLabel La_intro;
    private JTextField Tf_host;
    private JTextField Tf_port;
    private JButton Bu_connect;
    private JLabel La_host;
    private JLabel La_port;

    ClientManager clientManager = ClientContainer.Companion.clientManager();
    private ClientSocketExceptionHandler clientSocketExceptionHandler = ClientContainer.Companion.clientSocketExceptionHandler();

    public void create() {
        JFrame frame = new JFrame("Dekilla");
        frame.setContentPane(container);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        Bu_connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClientManager.Companion.setIp(Tf_host.getText());
                    ClientManager.Companion.setPort(Integer.parseInt(Tf_port.getText()));

                    WrappedSocket wrappedSocket = clientManager.connect();
                    clientManager.processing();
                    MainView mainView = ViewContainer.Companion.mainView();
                    mainView.create(wrappedSocket.getId());
                    frame.dispose();
                } catch (NumberFormatException nfex) {
                    clientSocketExceptionHandler.ipInputNotNumber();
                } catch (Exception ex) {
                }
            }
        });
    }
}
