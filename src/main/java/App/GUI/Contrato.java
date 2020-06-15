package App.GUI;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.DefaultTableModel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;
import java.awt.Image;

import com.github.lgooddatepicker.components.DatePicker;

import App.Data.Provider;
import App.Data.Storage;
import net.miginfocom.swing.MigLayout;

import App.BackEnd.*;
import App.BackEnd.Aviones.Bronze;
import App.BackEnd.Aviones.Gold;
import App.BackEnd.Aviones.Silver;

public class Contrato extends JPanel {
    private static final long serialVersionUID = 8496524709915595739L;

    private BufferedImage image;
    private JButton backButton;
    private JButton finalizarButton;
    private JLabel exceptionLabel;

    private JLabel fechaLabel;
    private JLabel origenLabel;
    private JLabel destinoLabel;
    private DatePicker fechaField;
    private JComboBox<String> origenBox;
    private JComboBox<String> destinoBox;
    private JLabel pasajerosLabel;
    private JSpinner pasajerosField;
    private JLabel avionesLabel;
    private JComboBox<String> avionesBox;

    Avion avionSeleccionado = null;

    private JLabel tipoLabel;
    private JLabel combustibleLabel;
    private JLabel costoKMLabel;
    private JLabel capacidadLabel;
    private JLabel velocidadLabel;
    private JLabel motorLabel;
    private JLabel cateringLabel;
    private JLabel wifiLabel;

    private JLabel totalLabel;

    String[] lugares = { "Buenos Aires", "Montevideo", "Cordoba", "Santiago" };

    private Image getImage() {
        try {
            image = ImageIO.read(new File("src/main/java/App/GUI/assets/avion.png"));
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(getImage().getScaledInstance(800, 600, Image.SCALE_AREA_AVERAGING), 0, 0, null);
    }

    private void infoAvion(JPanel currentPanel) {
        if (avionesBox.getSelectedItem() != null && avionesBox.getSelectedItem() != "") {
            avionSeleccionado = Provider.getAviones().stream()
                    .filter((Avion avion) -> avion.getNombre().equals(avionesBox.getSelectedItem()))
                    .collect(Collectors.reducing((a, b) -> null)).get();

            if (avionSeleccionado instanceof Gold) {
                tipoLabel.setText("Tipo: Gold");
            } else if (avionSeleccionado instanceof Silver) {
                tipoLabel.setText("Tipo: Silver");
            } else if (avionSeleccionado instanceof Bronze) {
                tipoLabel.setText("Tipo: Bronze");
            }

            combustibleLabel.setText("Combustible: " + String.valueOf(avionSeleccionado.getCombustible()));
            costoKMLabel.setText("Costo por KM: " + String.valueOf(avionSeleccionado.getCostoKM()));
            capacidadLabel.setText("Capacidad: " + String.valueOf(avionSeleccionado.getCapacidad()) + " pasajeros");
            velocidadLabel.setText("Velocidad Maxima: " + String.valueOf(avionSeleccionado.getVelocidad()));
            motorLabel.setText("Motor " + avionSeleccionado.getMotor());
            if (avionSeleccionado.hasCatering()) {
                cateringLabel.setText("Con Catering");
            } else {
                cateringLabel.setText("Sin Catering");
            }
            if (avionSeleccionado instanceof Gold) {
                if (((Gold) avionSeleccionado).hasWifi()) {
                    wifiLabel.setText("Con Wifi");
                } else {
                    wifiLabel.setText("Sin Wifi");
                }
            } else {
                wifiLabel.setText("");
            }
        } else {
            tipoLabel.setText("");
            combustibleLabel.setText("");
            costoKMLabel.setText("");
            capacidadLabel.setText("");
            velocidadLabel.setText("");
            motorLabel.setText("");
            cateringLabel.setText("");
            wifiLabel.setText("");
        }
    }

    private boolean validar(JPanel currentPanel) {
        try {
            infoAvion(currentPanel);
            exceptionLabel.setText("");
            // If fecha anterior a hoy
            if (fechaField.getDate() != null && fechaField.getDate().isAfter(LocalDate.now())) {
                // if pasajeros mayor a 0 y menor o igual que la capacidad del avion
                // seleccionado
                if (pasajerosField.getValue() != null && (((Integer) pasajerosField.getValue()) != 0)
                        && (((Integer) pasajerosField.getValue()) <= avionSeleccionado.getCapacidad())) {

                    if (avionesBox.getSelectedItem() != null && avionesBox.getSelectedItem() != "") {
                        // If ya existe un viaje con el avion y la fecha seleccionados
                        if (!Provider.getViajes().isEmpty()) {
                            if (Provider.getViajes().get(Provider.getCurrentUser().get("username")) != null
                                    && Provider.getViajes().get(Provider.getCurrentUser().get("username")).stream()
                                            .anyMatch(viaje -> viaje.getAvion().getNombre()
                                                    .equals(avionSeleccionado.getNombre()))
                                    && Provider.getViajes().get(Provider.getCurrentUser().get("username")).stream()
                                            .anyMatch(viaje -> viaje.getFecha().equals(fechaField.getDate()))) {
                                totalLabel.setText("");
                                throw new Exception("El avion ya tiene un vuelo programado para esa fecha");
                            }
                        }

                        // If origen es diferente del destino
                        if (!origenBox.getSelectedItem().equals(destinoBox.getSelectedItem())) {
                            exceptionLabel.setText("");

                            String curso = origenBox.getSelectedItem() + "-" + destinoBox.getSelectedItem();

                            if (Provider.getDistancias().get(curso) == null) {
                                curso = destinoBox.getSelectedItem() + "-" + origenBox.getSelectedItem();
                            }

                            totalLabel.setText("Total: " + String
                                    .valueOf((avionSeleccionado.getCostoKM() * Provider.getDistancias().get(curso))
                                            + (((Integer) pasajerosField.getValue()) * 3500)
                                            + (avionSeleccionado.getTarifaTipoAvion())));

                            return true;

                        } else {
                            totalLabel.setText("");
                            throw new Exception("El origen y el destino no pueden ser el mismo");
                        }

                    } else {
                        totalLabel.setText("");
                    }

                } else {
                    totalLabel.setText("");
                    if (pasajerosField.getValue() != null
                            && ((Integer) pasajerosField.getValue()) > avionSeleccionado.getCapacidad()) {
                        throw new Exception("La cantidad de pasajeros supera la capacidad del avion");
                    }
                }

            } else {
                totalLabel.setText("");
                if (fechaField.getDate() != null) {
                    throw new Exception("La fecha no puede ser antes de hoy");
                }
            }

        } catch (NullPointerException e) {
            e.printStackTrace();
            exceptionLabel.setText(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            exceptionLabel.setText(e.getMessage());
        }
        return false;
    }

    public Contrato(final JFrame frame, final JPanel previousJPanel, final JTable viajesTable) {

        setLayout(new MigLayout());

        fechaLabel = new JLabel("Fecha");
        add(fechaLabel, "gapleft 10, cell 0 0, grow");
        fechaField = new DatePicker();
        fechaField.addDateChangeListener(actionEvent -> {
            validar(this);
        });
        add(fechaField, "gapleft 10, cell 0 1, grow");

        pasajerosLabel = new JLabel("Pasajeros");
        add(pasajerosLabel, "gapleft 10, gaptop 10, cell 0 2, grow");
        pasajerosField = new JSpinner(new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1));
        pasajerosField.addChangeListener(actionEvent -> {
            validar(this);
        });
        add(pasajerosField, "gapleft 10, cell 0 3, grow");

        origenLabel = new JLabel("Origen");
        add(origenLabel, "gapleft 20, cell 1 0, grow");
        origenBox = new JComboBox<String>(lugares);
        origenBox.addActionListener(actionEvent -> {
            validar(this);
        });
        add(origenBox, "gapleft 20, cell 1 1, grow");

        destinoLabel = new JLabel("Destino");
        add(destinoLabel, "gapleft 20, gaptop 10, cell 1 2, grow");
        destinoBox = new JComboBox<String>(lugares);
        destinoBox.addActionListener(actionEvent -> {
            validar(this);
        });
        add(destinoBox, "gapleft 20, cell 1 3, grow");

        // System.out.println(Provider.getAviones());
        avionesLabel = new JLabel("Seleccione un avion: ");
        add(avionesLabel, "gapleft 100, cell 2 2, grow");
        avionesBox = new JComboBox<String>(
                Provider.getAviones().stream().map((Avion avion) -> avion.getNombre()).toArray(String[]::new));
        avionesBox.insertItemAt("", 0);
        avionesBox.setSelectedIndex(-1);
        avionesBox.addActionListener(actionEvent -> {
            validar(this);
        });
        add(avionesBox, "gapleft 50, cell 2 3, grow");

        tipoLabel = new JLabel("");
        add(tipoLabel, "gapleft 20, cell 3 0, grow");
        combustibleLabel = new JLabel("");
        add(combustibleLabel, "gapleft 20, cell 4 0, grow");
        costoKMLabel = new JLabel("");
        add(costoKMLabel, "gapleft 20, cell 3 1, grow");
        capacidadLabel = new JLabel("");
        add(capacidadLabel, "gapleft 20, cell 4 1, grow");
        velocidadLabel = new JLabel("");
        add(velocidadLabel, "gapleft 20, cell 3 2, grow");
        motorLabel = new JLabel("");
        add(motorLabel, "gapleft 20, cell 4 2, grow");
        cateringLabel = new JLabel("");
        add(cateringLabel, "gapleft 20, cell 3 3, grow");
        wifiLabel = new JLabel("");
        add(wifiLabel, "gapleft 20, cell 4 3, grow");

        finalizarButton = new JButton("Finalizar");
        finalizarButton.addActionListener(actionEvent -> {
            if (validar(this)) {
                DefaultTableModel tableModel = (DefaultTableModel) viajesTable.getModel();
                tableModel.addRow(Provider.addViaje(new Viaje(fechaField.getDate(),
                        ((String) origenBox.getSelectedItem()), ((String) destinoBox.getSelectedItem()),
                        ((int) pasajerosField.getValue()), avionSeleccionado)).getValuesString());
                ;
                Storage.guardarViajes(Provider.getViajes());
                exceptionLabel.setText("Viaje guardado");

                viajesTable.revalidate();

            }
        });
        add(finalizarButton, "gapleft 10, gaptop 380, cell 0 9, grow");

        backButton = new JButton("volver");
        backButton.addActionListener(actionEvent -> {
            frame.setContentPane(previousJPanel);
            frame.revalidate();
            previousJPanel.revalidate();
            viajesTable.revalidate();
        });
        add(backButton, "gapleft 10, cell 0 10, grow");

        exceptionLabel = new JLabel("");
        add(exceptionLabel, "cell 2 10, span");

        totalLabel = new JLabel("");
        add(totalLabel, "gapleft 20, cell 3 10, grow");

    }
}