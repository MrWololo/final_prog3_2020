package App.GUI;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame {

    private static final long serialVersionUID = -5252222442637822026L;

    // private JFrame loginFrame = new Login();
    // private JPanel registerPanel = new Register();

    private BufferedImage image;
    private JLabel label;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel panel;

    public MainMenu() {

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 30, 100));
        panel.setLayout(null);
        getContentPane().add(panel);

        try {
            image = ImageIO.read(new File("src/main/java/App/GUI/assets/logo.png"));
            label = new JLabel(new ImageIcon(image.getScaledInstance(800, 600, Image.SCALE_AREA_AVERAGING)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        loginButton = new JButton("Login");
        loginButton.addActionListener(actionEvent -> {
            setContentPane(new Login(this, panel));
        });

        registerButton = new JButton("Registrarse");
        registerButton.addActionListener(actionEvent -> {
            setContentPane(new Register(this, panel));
            revalidate();
        });

        loginButton.setBounds(10, 470, 100, 25);
        panel.add(loginButton);
        registerButton.setBounds(10, 500, 120, 25);
        panel.add(registerButton);
        label.setBounds(0, -50, 800, 650);
        panel.add(label);

        add(panel, BorderLayout.CENTER);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AeroTaxi");
        // pack();
        setLocationRelativeTo(null);
        setVisible(true);

    }

}