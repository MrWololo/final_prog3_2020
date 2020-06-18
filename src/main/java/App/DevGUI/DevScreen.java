package App.DevGUI;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import App.GUI.AvionesMenu;
import App.GUI.MainMenu;

public class DevScreen extends JFrame {

    private static final long serialVersionUID = 6253383350544902274L;

    private JPanel panel;
    private JButton backButton;
    private JButton newButton;
    private JButton avionesButton;
    private JButton clientesButton;
    // private JButton deleteButton;
    // private JButton avionesButton;

    public DevScreen() {

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

        newButton = new JButton("Nuevo Avion");
        newButton.setBounds(100, 10, 100, 25);
        newButton.addActionListener(actionEvent -> {
            setContentPane(new NuevoAvion(this, panel));
            revalidate();
        });

        panel.add(newButton);

        /* ; */

        avionesButton = new JButton("Aviones");
        avionesButton.setBounds(10, 60, 80, 25);
        avionesButton.addActionListener(actionEvent -> {
            JPanel avionesPanel = new AvionesMenu(this, panel, true);

            setContentPane(avionesPanel);
            this.setSize(800, 600);
        });

        panel.add(avionesButton);

        clientesButton = new JButton("Clientes");
        clientesButton.setBounds(100, 60, 80, 25);
        clientesButton.addActionListener(actionEvent -> {
            setContentPane(new ListaClientes(this, panel));
            this.setSize(800, 600);
        });

        panel.add(clientesButton);

        setSize(250, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Developer options");
        setLocationRelativeTo(null);

    }
}