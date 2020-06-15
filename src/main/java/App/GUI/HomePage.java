package App.GUI;

import javax.swing.JButton;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import App.Data.Provider;

public class HomePage extends JFrame {
    private static final long serialVersionUID = 8496524709915595739L;

    private JPanel panel;
    private JButton backButton;
    private JButton contratarButton;
    private JButton avionesButton;

    private JTable table;
    private String[] columnasTable = { "Fecha", "Origen", "destino", "Pasajeros", "Avion" };
    private JScrollPane scrollPane;

    private String[][] biArray = Provider.getViajesTable();

    public HomePage() {

        if (biArray != null && biArray.length != 0) {
            for (String[] strings : biArray) {
                for (String string : strings) {
                    System.out.println(string);
                    System.out.println("here you cunt");
                }
                System.out.println("\n");
            }
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
            setContentPane(new Contrato(this, panel, table));
            revalidate();
        });

        panel.add(contratarButton);

        avionesButton = new JButton("Aviones");
        avionesButton.setBounds(10, 60, 80, 25);
        avionesButton.addActionListener(actionEvent -> {

        });

        panel.add(avionesButton);

        if (Provider.getViajes().get(Provider.getCurrentUser().get("username")) != null
                && !Provider.getViajes().get(Provider.getCurrentUser().get("username")).isEmpty()) {
            table = new JTable();
            populatetable(table, columnasTable, biArray);
            table.setDefaultEditor(Object.class, null);
            // table.setModel(tableModel);
            scrollPane = new JScrollPane();
            scrollPane.setBounds(100, 10, 650, 450);
            scrollPane.setViewportView(table);

            panel.add(scrollPane);
        }

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AeroTaxi");
        setLocationRelativeTo(null);

    }

    public static JTable populatetable(JTable table, String[] header, String[][] values) {
        DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();
        tablemodel.setRowCount(0);
        for (String col : header) {
            tablemodel.addColumn(col);
        }
        for (String[] row : values) {
            tablemodel.addRow(row);
        }
        table.setModel(tablemodel);
        return table;
    }
}