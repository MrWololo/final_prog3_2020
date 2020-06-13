package App.GUI;

import javax.swing.JButton;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import App.Data.Provider;
import App.Data.Storage;
import App.BackEnd.*;

public class HomePage extends JFrame {

    private static final long serialVersionUID = 8496524709915595739L;

    private JPanel panel;
    private JButton backButton;
    private JButton contratarButton;
    private JButton avionesButton;

    private JTable table;
    private String[] columnasTable = { "Fecha", "Origen", "destino", "Pasajeros", "Avion" };

    private String[][] biArray = Storage.fetchViajes().get(Provider.getCurrentUser().get("username")).stream()
            .map((Viaje value) -> value.getValuesString()).toArray(size -> new String[size][]);

    public HomePage() {

        for (String[] strings : biArray) {
            for (String string : strings) {
                System.out.println(string);
            }
            System.out.println("\n");
        }

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

        avionesButton = new JButton("Aviones");
        avionesButton.setBounds(10, 60, 80, 25);
        avionesButton.addActionListener(actionEvent -> {

        });

        panel.add(avionesButton);

        table = new JTable(biArray, columnasTable);
        table.setDefaultEditor(Object.class, null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(100, 10, 650, 450);
        scrollPane.setViewportView(table);

        panel.add(scrollPane);

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AeroTaxi");
        setLocationRelativeTo(null);

    }
}