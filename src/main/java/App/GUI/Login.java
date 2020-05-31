package App.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {

    private static final long serialVersionUID = 6014867817271271729L;

    private JPanel panel;
    private JLabel userLabel;
    private JTextField userField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;

    public Login() {

        panel = new JPanel();
        panel.setLayout(null);
        setLocationRelativeTo(null);

        add(panel);

        userLabel = new JLabel("Usuario");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);
        userField = new JTextField();
        userField.setBounds(100, 20, 165, 25);
        panel.add(userField);

        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(10, 60, 80, 25);
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 60, 165, 25);
        panel.add(passwordField);

        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}