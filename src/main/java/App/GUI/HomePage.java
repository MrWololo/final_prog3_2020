package App.GUI;

import javax.swing.JButton;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import App.Data.Provider;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 8496524709915595739L;

    private JPanel panel;
    private JButton backButton;
    private JButton contratarButton;
    private JButton avionesButton;

    public HomePage() {
        System.out.println(Provider.getViajes().get(Provider.getCurrentUser().get("username")));
        panel = new JPanel();
        panel.setLayout(null);

        add(panel);
        backButton = new JButton("Salir");
        backButton.setBounds(10, 10, 80, 25);
        backButton.addActionListener(actionEvent -> {
            dispose();
            new MainMenu().setVisible(true);
        });
        panel.add(backButton);

        contratarButton = new JButton("Contratar Vuelo");
        contratarButton.setBounds(625, 470, 150, 25);
        contratarButton.addActionListener(actionEvent -> {
            setContentPane(new Contrato(this, panel));
            revalidate();
        });

        panel.add(contratarButton);

        /*
         * avionesButton = new JButton("Aviones"); avionesButton.setBounds(10, 60, 80,
         * 25); avionesButton.addActionListener(actionEvent -> {
         * 
         * });
         * 
         * panel.add(avionesButton);
         */

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AeroTaxi");
        setLocationRelativeTo(null);

    }
}