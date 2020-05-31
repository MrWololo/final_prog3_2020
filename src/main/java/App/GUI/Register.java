package App.GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;

public class Register extends JFrame {

    private static final long serialVersionUID = -8793673541319825986L;

    private JPanel panel;

    private JLabel nombreLabel;
    private JTextField nombreField;
    private JLabel apellidoLabel;
    private JTextField apellidoField;
    private JLabel dniLabel;
    private JTextField dniField;
    private JLabel edadLabel;
    private JTextField edadField;
    private JLabel userLabel;
    private JTextField userField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;

    public Register() {
        panel = new JPanel();
        panel.setLayout(null);
        setLocationRelativeTo(null);

        add(panel, BorderLayout.CENTER);

        nombreLabel = new JLabel("Nombre");
        nombreLabel.setBounds(15, 20, 80, 25);
        panel.add(nombreLabel);
        nombreField = new JTextField();
        nombreField.setBounds(15, 40, 165, 25);
        panel.add(nombreField);

        apellidoLabel = new JLabel("Apellido");
        apellidoLabel.setBounds(195, 20, 80, 25);
        panel.add(apellidoLabel);
        apellidoField = new JTextField();
        apellidoField.setBounds(195, 40, 165, 25);
        panel.add(apellidoField);

        dniLabel = new JLabel("DNI");
        dniLabel.setBounds(15, 80, 80, 25);
        panel.add(dniLabel);
        dniField = new JTextField();
        dniField.setBounds(15, 100, 345, 25);
        panel.add(dniField);

        edadLabel = new JLabel("Edad");
        edadLabel.setBounds(15, 140, 80, 25);
        panel.add(edadLabel);
        edadField = new JTextField();
        edadField.setBounds(15, 160, 345, 25);
        panel.add(edadField);

        userLabel = new JLabel("Usuario");
        userLabel.setBounds(15, 200, 80, 25);
        panel.add(userLabel);
        userField = new JTextField();
        userField.setBounds(15, 220, 345, 25);
        panel.add(userField);

        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(15, 260, 80, 25);
        panel.add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(15, 280, 345, 25);
        panel.add(passwordField);

        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}