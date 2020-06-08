package App.GUI;

import java.awt.Color;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import App.Data.Provider;
import App.DevGUI.DevScreen;

public class Login extends JPanel {

    private static final long serialVersionUID = 6014867817271271729L;

    private JLabel userLabel;
    private JTextField userField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton botonLogin;
    private JLabel exceptionLabel;
    private JButton botonVolver;

    public Login(final JFrame frame, final JPanel previousJPanel) {

        setLayout(null);

        userLabel = new JLabel("Usuario");
        userLabel.setBounds(10, 20, 80, 25);
        add(userLabel);
        userField = new JTextField();
        userField.setBounds(100, 20, 165, 25);
        add(userField);

        passwordLabel = new JLabel("Contraseña");
        passwordLabel.setBounds(10, 60, 80, 25);
        add(passwordLabel);
        passwordField = new JPasswordField();
        passwordField.setBounds(100, 60, 165, 25);
        add(passwordField);

        botonLogin = new JButton("LogIn");
        botonLogin.setBounds(10, 100, 80, 25);
        add(botonLogin);

        exceptionLabel = new JLabel("");
        exceptionLabel.setBounds(120, 100, 400, 25);
        add(exceptionLabel);

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(10, 130, 80, 25);
        add(botonVolver);
        botonVolver.addActionListener(actionEvent -> {
            frame.setSize(800, 600);
            frame.setContentPane(previousJPanel);
            frame.revalidate();
        });

        botonLogin.addActionListener(actionEvent -> {
            try {
                if (userField.getText().isEmpty() || passwordField.getPassword().length == 0) {
                    throw new Exception("Datos Incompletos");
                } else {
                    if (Provider.getUsers().isEmpty()) {
                        throw new Exception("No existen Usuarios");
                    } else {
                        for (Map<String, String> map : Provider.getUsers()) {
                            if (map.get("username").equals(userField.getText())) {
                                if (map.get("contraseña").equals(new String(passwordField.getPassword()))) {
                                    Provider.setCurrentUser(map);
                                    frame.dispose();
                                    new HomePage().setVisible(true);

                                } else {
                                    exceptionLabel.setText("Contraseña incorrecta");
                                }
                            } else if (userField.getText().equals("dev")
                                    && new String(passwordField.getPassword()).equals("2468")) {
                                frame.dispose();
                                new DevScreen().setVisible(true);
                            } else {
                                exceptionLabel.setText("El usuario no existe");
                            }
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                exceptionLabel.setText(e.getMessage());
                exceptionLabel.setForeground(Color.RED);
            }
        });

        frame.setSize(350, 200);
    }
}