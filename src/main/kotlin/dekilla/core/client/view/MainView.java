package dekilla.core.client.view;

import javax.swing.*;
import java.awt.*;

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

    public void create() {
        JFrame frame = new JFrame("Dekilla");
        frame.setContentPane(container);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String args[]) {
        new MainView().create();
    }
}
