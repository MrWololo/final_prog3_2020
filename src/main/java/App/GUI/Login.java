package App.GUI;

import java.awt.Color;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import App.BackEnd.LocalData;

public class Login extends JFrame {

    private static final long serialVersionUID = 6014867817271271729L;

    private JPanel panel;
    private JLabel userLabel;
    private JTextField userField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton botonLogin;
    private JLabel exceptionLabel;
    private JButton botonVolver;

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

        botonLogin = new JButton("LogIn");
        botonLogin.setBounds(10, 100, 80, 25);
        panel.add(botonLogin);

        exceptionLabel = new JLabel("");
        exceptionLabel.setBounds(120, 100, 400, 25);
        panel.add(exceptionLabel);

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(10, 130, 80, 25);
        panel.add(botonVolver);
        botonVolver.addActionListener(actionEvent -> {
            dispose();
            new MainMenu().setVisible(true);
        });

        botonLogin.addActionListener(actionEvent -> {
            try {
                if (userField.getText().isEmpty() || passwordField.getPassword().length == 0) {
                    throw new Exception("Datos Incompletos");
                } else {
                    if (LocalData.getUsers().isEmpty()) {
                        throw new Exception("No existen Usuarios");
                    } else {
                        for (Map<String, String> map : LocalData.getUsers()) {
                            if (map.get("username").equals(userField.getText())) {
                                if (map.get("contraseña").equals(new String(passwordField.getPassword()))) {
                                    LocalData.setCurrentUser(map);

                                    exceptionLabel.setText("Yes");

                                } else {
                                    throw new Exception("Contraseña incorrecta");
                                }
                            } else {
                                throw new Exception("El usuario no existe");
                            }
                        }
                    }

                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                exceptionLabel.setText(e.getMessage());
                exceptionLabel.setForeground(Color.RED);
            }
        });

        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}