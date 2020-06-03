package App.DevGUI;

import java.awt.Color;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import App.BackEnd.Aviones.Bronze;
import App.BackEnd.Aviones.Gold;
import App.BackEnd.Aviones.Silver;
import App.Data.Provider;
import net.miginfocom.swing.MigLayout;

public class NuevoAvion extends JPanel {

    private static final long serialVersionUID = -7413429818266538009L;
    private Random random = new Random();

    private String[] tiposDeAviones = { "Gold", "Silver", "Bronze" };
    private String tipoDeAvionSeleccionado = "Gold";
    private JComboBox<String> tipoAvion;

    private JLabel nombreAvionLabel;
    private JTextField nombreAvionField;

    private JLabel combustibleLabel;
    private JSpinner combustibleField;

    private JLabel costoKMLabel;
    private JSpinner costoKMField;

    private JLabel capacidadLabel;
    private JSpinner capacidadField;

    private JLabel velocidadLabel;
    private JSpinner velocidadField;

    private String[] tiposDeMotor = { "a reaccion", "a helice", "de pistones" };
    private String tipoDeMotorSeleccionado;
    private JLabel motorLabel;
    private JComboBox<String> motorBox;

    private JButton backButton;
    private JButton finalizarButton;
    private JLabel exceptionLabel;

    public NuevoAvion(final JFrame frame, final JPanel previousPanel) {

        frame.setSize(350, 250);
        setLayout(new MigLayout());

        tipoAvion = new JComboBox<>(tiposDeAviones);
        tipoAvion.setSelectedIndex(0);
        tipoAvion.addActionListener(actionEvent -> {
            JComboBox<?> box = (JComboBox<?>) actionEvent.getSource();
            tipoDeAvionSeleccionado = (String) box.getSelectedItem();
        });
        add(tipoAvion);

        nombreAvionLabel = new JLabel("Nombre: ");
        add(nombreAvionLabel, "gapleft 10");
        nombreAvionField = new JTextField(20);
        add(nombreAvionField, "wrap");

        combustibleLabel = new JLabel("Combustible: ");
        add(combustibleLabel, "gapleft 10");
        combustibleField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        add(combustibleField, "grow, wrap");

        costoKMLabel = new JLabel("Costo por KM: ");
        add(costoKMLabel, "gapleft 10");
        costoKMField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        add(costoKMField, "grow, wrap");

        capacidadLabel = new JLabel("Capacidad: ");
        add(capacidadLabel, "gapleft 10");
        capacidadField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        add(capacidadField, "grow, wrap");

        velocidadLabel = new JLabel("Velocidad Maxima: ");
        add(velocidadLabel, "gapleft 10");
        velocidadField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        add(velocidadField, "grow, wrap");

        motorLabel = new JLabel("Tipo de motor: ");
        add(motorLabel, "gapleft 10");
        motorBox = new JComboBox<>(tiposDeMotor);
        motorBox.addActionListener(actionEvent -> {
            JComboBox<?> box = (JComboBox<?>) actionEvent.getSource();
            tipoDeMotorSeleccionado = (String) box.getSelectedItem();
        });
        add(motorBox, "grow");

        finalizarButton = new JButton("Finalizar");
        finalizarButton.addActionListener(actionEvent -> {
            try {
                if (!nombreAvionField.getText().isEmpty()) {
                    if (Double.parseDouble(capacidadField.getValue().toString()) > 0
                            && Double.parseDouble(combustibleField.getValue().toString()) > 0
                            && Double.parseDouble(costoKMField.getValue().toString()) > 0
                            && Double.parseDouble(velocidadField.getValue().toString()) > 0) {
                        switch (tipoDeAvionSeleccionado) {
                            case "Gold":
                                Provider.addAvion(new Gold(nombreAvionField.getText(),
                                        (double) (Integer) combustibleField.getValue(),
                                        (double) (Integer) costoKMField.getValue(),
                                        (double) (Integer) capacidadField.getValue(),
                                        (double) (Integer) velocidadField.getValue(), tipoDeMotorSeleccionado,
                                        random.nextBoolean()));

                                System.out.println(Provider.getAviones());
                                exceptionLabel.setText("Avion Gold agregado");
                                break;
                            case "Silver":
                                Provider.addAvion(new Silver(nombreAvionField.getText(),
                                        (double) (Integer) combustibleField.getValue(),
                                        (double) (Integer) costoKMField.getValue(),
                                        (double) (Integer) capacidadField.getValue(),
                                        (double) (Integer) velocidadField.getValue(), tipoDeMotorSeleccionado));

                                System.out.println(Provider.getAviones());
                                exceptionLabel.setText("Avion Silver agregado");
                                break;

                            case "Bronze":
                                Provider.addAvion(new Bronze(nombreAvionField.getText(),
                                        (double) (Integer) combustibleField.getValue(),
                                        (double) (Integer) costoKMField.getValue(),
                                        (double) (Integer) capacidadField.getValue(),
                                        (double) (Integer) velocidadField.getValue(), tipoDeMotorSeleccionado));

                                System.out.println(Provider.getAviones());
                                exceptionLabel.setText("Avion Bronze agregado");
                                break;

                        }

                    } else {
                        throw new Exception("Ingrese un valor mayor a 0");
                    }

                } else {
                    throw new Exception("Datos incompletos");
                }
            } catch (NumberFormatException e) {
                System.out.println(e);
                exceptionLabel.setText("Aplique numeros donde corresponde");
                exceptionLabel.setForeground(Color.RED);
            } catch (Exception e) {
                System.out.println(e);
                exceptionLabel.setText(e.getMessage());
                exceptionLabel.setForeground(Color.RED);
            }
        });
        add(finalizarButton, "grow, wrap");

        backButton = new JButton("volver");
        backButton.addActionListener(actionEvent -> {
            frame.setContentPane(previousPanel);
            frame.revalidate();
        });
        add(backButton, "grow");

        exceptionLabel = new JLabel("");
        add(exceptionLabel);

    }

}