package App.GUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Contrato extends JPanel {
    private static final long serialVersionUID = 8496524709915595739L;

    private JButton backButton;
    private JButton finalizarButton;

    public Contrato(final JFrame frame, final JPanel previousJPanel) {

        setLayout(null);

        backButton = new JButton("volver");
        backButton.setBounds(10, 500, 80, 25);
        backButton.addActionListener(actionEvent -> {
            frame.setContentPane(previousJPanel);
            frame.revalidate();
        });

        add(backButton);

        finalizarButton = new JButton("Finalizar");
        finalizarButton.setBounds(10, 470, 100, 25);
        finalizarButton.addActionListener(actionEvent -> {

        });

        add(finalizarButton);

    }
}