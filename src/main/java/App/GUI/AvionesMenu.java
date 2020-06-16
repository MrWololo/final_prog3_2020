package App.GUI;

import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import App.BackEnd.Avion;
import App.Data.Provider;
import App.TableUtils.TableUtils;
import net.miginfocom.swing.MigLayout;

public class AvionesMenu extends JPanel {
    private static final long serialVersionUID = -1724375478164890L;

    private Avion avionSeleccionado = null;

    private JButton backButton;

    private JTable table;

    private String[] columnasTable = { "Nombre", "Combustible", "CostoKM", "Capacidad", "Velocidad", "Motor",
            "Catering" };

    private JScrollPane scrollPane;

    private String[][] biArray = Provider.getAvionesTable();

    public AvionesMenu(final JFrame frame, final JPanel previousJPanel) {

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

        add(backButton, "gapleft 10, cell 0 10");
        
    }
}