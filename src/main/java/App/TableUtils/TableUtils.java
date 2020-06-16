package App.TableUtils;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class TableUtils {
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




