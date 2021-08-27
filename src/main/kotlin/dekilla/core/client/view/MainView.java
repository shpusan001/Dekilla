package dekilla.core.client.view;

import dekilla.core.client.ClientManager;
import dekilla.core.container.ClientContainer;
import dekilla.core.domain.SockDto;
import dekilla.core.util.socket.WrappedSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.Socket;

public class MainView {
    private JPanel container;
    private JTextField Tf_myToken;
    private JButton Bu_send;
    private JLabel La_myToken;
    private JTextField Tf_downPath;
    private JLabel La_downPath;
    private JLabel La_upPath;
    private JTextField Tf_upPath;
    private JButton Bu_downPath;
    private JButton Bu_upPath;
    private JLabel La_targetToken;
    private JTextField Tf_targetToken;
    private JTextPane Tp_status;
    private JButton Bu_connect;

    private ClientManager clientManager;

    public MainView() {
        this.clientManager = ClientContainer.Companion.clientManager();
    }

    public void create() {
        JFrame frame = new JFrame("Dekilla");
        frame.setContentPane(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        networkInit();

        WrappedSocket requesterSocket = clientManager.getWrappedSocket();

        Bu_connect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SockDto request = new SockDto(
                        "CONNECT_WITH_TOKEN",
                        "#",
                        requesterSocket.getId() + "#" + Tf_targetToken.getText(),
                        null
                );
                clientManager.sendData(request);
            }
        });

        Bu_downPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                chooser.showDialog(container, null);

                File dir = chooser.getSelectedFile();
                Tf_downPath.setText(dir.getPath());
            }
        });

        Bu_upPath.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                chooser.showDialog(container, null);

                File file = chooser.getSelectedFile();
                Tf_upPath.setText(file.getPath());
            }
        });
    }

    public void networkInit() {
        WrappedSocket wrappedSocket = clientManager.connect();
        Tf_myToken.setText(wrappedSocket.getId());
        clientManager.processing();
    }
}
