package App.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUI implements ActionListener {

    int clickCount = 0;
    JLabel label;
    JButton button;
    JFrame frame;
    JPanel panel;

    public GUI() {
        frame = new JFrame();

        button = new JButton("Test");
        button.addActionListener(this);

        label = new JLabel("Clicks: 0");

        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(100, 100, 30, 100));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("AeroTaxi");
        frame.pack();
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        clickCount++;
        label.setText("Clicks: " + clickCount);
    }

}