package App.TableUtils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import App.BackEnd.Viaje;
import App.Data.Provider;
import App.Data.Storage;

import java.awt.Component;

public class ButtonEditor extends DefaultCellEditor {

    private static final long serialVersionUID = -2491215962333012994L;
    protected JButton button;
    private String label;
    private boolean isPushed;
    private JTable table;

    public ButtonEditor(JTable importedTable, JCheckBox checkBox) {
        super(checkBox);

        table = importedTable;

        button = new JButton();
        button.setOpaque(true);
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(table.getSelectedRow());

            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        if (isSelected) {
            button.setForeground(table.getSelectionForeground());
            button.setBackground(table.getSelectionBackground());
        } else {
            button.setForeground(table.getForeground());
            button.setBackground(table.getBackground());
        }
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        isPushed = true;
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        if (isPushed && table.getSelectedRow() != -1) {
            int row = table.getSelectedRow();

            Map<String, ArrayList<Viaje>> viajesData = Provider.getViajes();
            ArrayList<Viaje> viajeList = viajesData.get(Provider.getCurrentUser().get("username"));

            viajeList.remove(row);

            viajesData.put(Provider.getCurrentUser().get("username"), viajeList);

            Provider.setViajes(viajesData);
            Storage.guardarViajes(viajesData);
        }
        isPushed = false;
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        isPushed = false;
        return super.stopCellEditing();
    }

}