package App.GUI;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import App.BackEnd.LocalData;
import App.BackEnd.Storage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

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

    private JButton botonRegistro;
    private JLabel exceptionLabel;

    private JButton botonVolver;

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
        edadField = new JFormattedTextField();
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

        botonRegistro = new JButton("Finalizar");
        botonRegistro.setBounds(15, 320, 100, 25);
        panel.add(botonRegistro);

        exceptionLabel = new JLabel("");
        exceptionLabel.setBounds(150, 320, 400, 25);
        panel.add(exceptionLabel);

        botonVolver = new JButton("Volver");
        botonVolver.setBounds(15, 350, 100, 25);
        panel.add(botonVolver);
        botonVolver.addActionListener(actionEvent -> {
            dispose();
            new MainMenu().setVisible(true);
        });

        botonRegistro.addActionListener(actionEvent -> {
            Map<String, String> data = new HashMap<String, String>();

            try {
                if (!nombreField.getText().isEmpty() && !apellidoField.getText().isEmpty()
                        && !dniField.getText().isEmpty() && !edadField.getText().isEmpty()
                        && !userField.getText().isEmpty() && !passwordField.getPassword().toString().isEmpty()) {

                    data.put("nombre", nombreField.getText());
                    data.put("apellido", apellidoField.getText());
                    data.put("username", userField.getText());
                    data.put("contraseña", new String(passwordField.getPassword()));
                    try {
                        Integer.parseInt(dniField.getText());
                        data.put("dni", dniField.getText());
                        try {
                            Integer.parseInt(edadField.getText());
                            data.put("edad", edadField.getText());

                            if (LocalData.userExists(data)) {
                                throw new Exception("El nombre de usuario ya existe");
                            } else {
                                LocalData.addToUsers(data);
                                Storage.guardarRegistro(LocalData.getUsers());

                                exceptionLabel.setText("Registrado");
                                exceptionLabel.setForeground(Color.GREEN);
                            }

                        } catch (NumberFormatException e) {
                            exceptionLabel.setText("Edad solo acepta valores numericos");
                            exceptionLabel.setForeground(Color.RED);
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Excepcion");
                        exceptionLabel.setText("DNI solo acepta valores numericos");
                        exceptionLabel.setForeground(Color.RED);
                    }

                } else {
                    throw new Exception("Complete todos los datos");
                }
            } catch (Exception e) {
                System.out.println(e);
                exceptionLabel.setText(e.getMessage());
                exceptionLabel.setForeground(Color.RED);
            }

        });

        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

}