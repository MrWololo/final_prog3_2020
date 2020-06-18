package App.GUI;

import java.time.LocalDate;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.github.lgooddatepicker.components.DatePicker;

//import App.BackEnd.Avion;
import App.Data.Provider;
import App.TableUtils.TableUtils;
import net.miginfocom.swing.MigLayout;

public class ViajesMenu extends JPanel {
    private static final long serialVersionUID = -1724375478164890L;

    private JButton backButton;
    private DatePicker datePicker;
    private JTable table;
    private String[][] biArray = new String[0][0];

    private String[] columnasTable = { "Fecha", "Origen", "destino", "Pasajeros", "Avion" };

    private JScrollPane scrollPane;

    public void getByUsers(LocalDate fecha) {
        biArray = new String[0][0];

        for (Map<String, String> map : Provider.getUsers()) {
            String usuario = map.get("username");

            biArray = append(biArray, Provider.getViajesTable(usuario, fecha));

        }
    }

    public static String[][] append(String[][] a, String[][] b) {
        System.out.println(a.length);
        System.out.println(b.length);
        if (a.length != 0) {
            if (b.length != 0) {
                String[][] result = new String[a.length + b.length][];

                System.arraycopy(a, 0, result, 0, a.length);

                System.arraycopy(b, 0, result, a.length, b.length);
                return result;
            }
            return a;
        }
        return b;
    }

    public ViajesMenu(final JFrame frame, final JPanel previousJPanel) {

        getByUsers(null);

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

        if (!Provider.getViajes().isEmpty()) {
            datePicker = new DatePicker();
            datePicker.addDateChangeListener(actionEvent -> {
                getByUsers(datePicker.getDate());
                System.out.println(datePicker.getDate());
                TableUtils.populatetable(table, columnasTable, biArray);
            });
            add(datePicker, "cell 0 1, grow");
        }

    }
}