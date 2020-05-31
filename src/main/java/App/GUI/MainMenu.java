package App.GUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MainMenu extends JFrame {

    private static final long serialVersionUID = -5252222442637822026L;

    JFrame loginFrame = new Login();

    JLabel label;
    JButton loginButton;
    JPanel panel;

    public MainMenu() {

        loginButton = new JButton("Login");
        loginButton.addActionListener(actionEvent -> {
            dispose();
            loginFrame.setVisible(true);
        });

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 30, 100));
        panel.setLayout(new GridLayout(0, 1));

        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);

        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AeroTaxi");
        pack();
        setVisible(true);

    }

}