package App.GUI;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import App.BackEnd.Avion;
//import App.BackEnd.Avion;
import App.Data.Provider;
import App.TableUtils.TableUtils;
import net.miginfocom.swing.MigLayout;

public class AvionesMenu extends JPanel {
    private static final long serialVersionUID = -1724375478164890L;

    // private Avion avionSeleccionado = null;

    private JButton backButton;
    private JButton deleteButton;
    private JTable table;

    private String[] columnasTable = { "Nombre", "Combustible", "CostoKM", "Capacidad", "Velocidad", "Motor",
            "Catering" };

    private JScrollPane scrollPane;

    private String[][] biArray = Provider.getAvionesTable();

    public AvionesMenu(final JFrame frame, final JPanel previousJPanel, final boolean isDev) {

        setLayout(new MigLayout());

        if (!Provider.getAviones().isEmpty()) {
            table = new JTable();
            TableUtils.populatetable(table, columnasTable, biArray);
            table.setDefaultEditor(Object.class, null);

            scrollPane = new JScrollPane();
            scrollPane.setViewportView(table);

            add(scrollPane, "cell 0 0, width 1000");
        }

        backButton = new JButton("volver");
        backButton.addActionListener(actionEvent -> {
            frame.setContentPane(previousJPanel);
            frame.revalidate();
        });

        add(backButton, "cell 0 1, grow");

        if (isDev) {
            deleteButton = new JButton("Borrar Seleccionados");
            deleteButton.addActionListener(actionEvent -> {
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                ArrayList<Avion> aviones = Provider.getAviones();
                for (int i = table.getSelectedRows().length - 1; i > -1; i--) {
                    model.removeRow(i);
                    aviones.remove(i);
                }

                Provider.setAviones(aviones);

            });
            add(deleteButton, "cell 0 1, grow");
        }

    }
}