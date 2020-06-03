package App.GUI;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.Image;

import com.github.lgooddatepicker.components.DatePicker;

import net.miginfocom.swing.MigLayout;

public class Contrato extends JPanel {
    private static final long serialVersionUID = 8496524709915595739L;

    private BufferedImage image;
    private JButton backButton;
    private JButton finalizarButton;

    private JLabel fechaLabel;
    private JLabel origenLabel;
    private JLabel destinoLabel;
    private DatePicker fechaField;
    private JComboBox<String> origenBox;
    private JComboBox<String> destinoBox;
    private JLabel pasajerosLabel;
    private JSpinner pasajerosField;

    String[] array = { "haaaaa", "heeeee", "hooooo" };

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

    public Contrato(final JFrame frame, final JPanel previousJPanel) {

        setLayout(new MigLayout());

        fechaLabel = new JLabel("Fecha");
        add(fechaLabel, "gapleft 10, cell 0 0, grow");
        fechaField = new DatePicker();
        fechaField.addDateChangeListener(null);
        add(fechaField, "gapleft 10, cell 0 1, grow");

        pasajerosLabel = new JLabel("Pasajeros");
        add(pasajerosLabel, "gapleft 10, gaptop 10, cell 0 2, grow");
        pasajerosField = new JSpinner();
        add(pasajerosField, "gapleft 10, cell 0 3, grow");

        origenLabel = new JLabel("Origen");
        add(origenLabel, "gapleft 20, cell 1 0, grow");
        origenBox = new JComboBox<String>(array);
        add(origenBox, "gapleft 20, cell 1 1, grow");

        destinoLabel = new JLabel("Destino");
        add(destinoLabel, "gapleft 20, gaptop 10, cell 1 2, grow");
        destinoBox = new JComboBox<String>(array);
        add(destinoBox, "gapleft 20, cell 1 3, grow");

        finalizarButton = new JButton("Finalizar");
        finalizarButton.addActionListener(actionEvent -> {
        });
        add(finalizarButton, "gapleft 10, gaptop 380, cell 0 9, grow");

        backButton = new JButton("volver");
        backButton.addActionListener(actionEvent -> {
            frame.setContentPane(previousJPanel);
            frame.revalidate();
        });
        add(backButton, "gapleft 10, cell 0 10, grow");

    }
}