package App.DevGUI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import App.Data.Provider;
import App.TableUtils.TableUtils;
import net.miginfocom.swing.MigLayout;

public class ListaClientes extends JPanel {
    private static final long serialVersionUID = 291440864350094342L;

    String[][] biArray = new String[Provider.getUsers().size()][];

    private JButton backButton;
    private JTable table;
    private String[] columnasTable = { "Nombre", "Apellido", "DNI", "Edad", "Username", "Contraseña" };
    private JScrollPane scrollPane;

    public ListaClientes(final JFrame frame, final JPanel previousJPanel) {
        for (int i = 0; i < Provider.getUsers().size(); i++) {

            Set<Entry<String, String>> entries = Provider.getUsers().get(i).entrySet();
            ArrayList<String> processedEntries = new ArrayList<>();

            for (Entry<String, String> entry : entries) {
                processedEntries.add(entry.getValue());
            }

            biArray[i] = Arrays.copyOf(processedEntries.toArray(new String[0]), entries.size());
        }

        setLayout(new MigLayout());

        table = new JTable();
        TableUtils.populatetable(table, columnasTable, biArray);
        table.setDefaultEditor(Object.class, null);

        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);

        add(scrollPane, "cell 0 0, width 1000");

        backButton = new JButton("volver");
        backButton.addActionListener(actionEvent -> {
            frame.setContentPane(previousJPanel);
            frame.revalidate();
        });

        add(backButton, "cell 0 1, grow");

    }
}