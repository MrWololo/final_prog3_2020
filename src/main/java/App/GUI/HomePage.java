package App.GUI;

import javax.swing.JButton;
import javax.swing.JCheckBox;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import App.Data.Provider;

import App.TableUtils.ButtonEditor;
import App.TableUtils.ButtonRenderer;
import App.TableUtils.TableUtils;
import net.miginfocom.swing.MigLayout;

public class HomePage extends JFrame {
    private static final long serialVersionUID = 8496524709915595739L;

    private JPanel panel;
    private JButton backButton;
    private JButton contratarButton;
    private JButton avionesButton;
    private JButton ;
    private JLabel exceptionLabel;
    private JLabel totalLabel;

    private JTable table = new JTable();
    private String[] columnasTable = { "", "Fecha", "Origen", "destino", "Pasajeros", "Avion" };
    private JScrollPane scrollPane;

    private String[][] biArray = Provider.getViajesTable(Provider.getCurrentUser().get("username"));

    public HomePage() {

        /*
         * if (biArray != null && biArray.length != 0) { for (String[] strings :
         * biArray) { for (String string : strings) { System.out.println(string); }
         * System.out.println("\n"); } }
         */

        panel = new JPanel();
        panel.setLayout(new MigLayout());

        add(panel);
        backButton = new JButton("Salir");
        // backButton.setBounds(10, 10, 80, 25);

        backButton.addActionListener(actionEvent -> {
            dispose();
            new MainMenu().setVisible(true);
        });
        panel.add(backButton, "cell 0 3, width 150");

        avionesButton = new JButton("Aviones");
        avionesButton.setBounds(10, 60, 80, 25);
        avionesButton.addActionListener(actionEvent -> {
            setContentPane(new AvionesMenu(this, panel, false));
            revalidate();
        });

        panel.add(avionesButton, "cell 1 3, width 150, align right");

        exceptionLabel = new JLabel("");
        exceptionLabel.setBounds(10, 470, 400, 25);

        panel.add(exceptionLabel, "cell 0 1, grow");

        contratarButton = new JButton("Contratar Vuelo");
        contratarButton.setBounds(625, 470, 150, 25);
        contratarButton.addActionListener(actionEvent -> {
            setContentPane(new Contrato(this, panel, totalLabel, table));
            revalidate();
        });

        panel.add(contratarButton, "cell 1 1, align right, width 150");

        if (Provider.getViajes().get(Provider.getCurrentUser().get("username")) != null
                && !Provider.getViajes().get(Provider.getCurrentUser().get("username")).isEmpty()) {

            table = new JTable();
            totalLabel = new JLabel("");

            /*
             * Arrays.sort(biArray, new Comparator<String[]>() {
             * 
             * @Override public int compare(String[] o1, String[] o2) {
             * 
             * return o1.[]; }
             * 
             * });
             */

            TableUtils.populatetable(table, columnasTable, biArray);
            table.setDefaultEditor(Object.class, null);
            table.getColumn("").setCellRenderer(new ButtonRenderer());
            table.getColumn("")
                    .setCellEditor(new ButtonEditor(table, panel, exceptionLabel, totalLabel, new JCheckBox()));
            table.setRowHeight(25);
            scrollPane = new JScrollPane();
            scrollPane.setBounds(100, 10, 650, 450);
            scrollPane.setViewportView(table);

            panel.add(scrollPane, "cell 0 0, span 2, grow, width 800");

            TableUtils.calcularTotal(panel, totalLabel);

        }

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("AeroTaxi");
        setLocationRelativeTo(null);

    }

}