package App.GUI;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
//import javax.swing.BorderFactory;
//import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import App.BackEnd.Viaje;
import App.Data.Provider;
import App.Data.Storage;

public class HomePage extends JFrame {
    private static final long serialVersionUID = 8496524709915595739L;

    private JPanel panel;
    private JButton backButton;
    private JButton contratarButton;
    private JButton avionesButton;

    private JTable table;
    private String[] columnasTable = { "", "Fecha", "Origen", "destino", "Pasajeros", "Avion" };
    private JScrollPane scrollPane;

    private String[][] biArray = Provider.getViajesTable();

    public HomePage() {

        /*
         * if (biArray != null && biArray.length != 0) { for (String[] strings :
         * biArray) { for (String string : strings) { System.out.println(string); }
         * System.out.println("\n"); } }
         */

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
            table.getColumn("").setCellRenderer(new ButtonRenderer());
            table.getColumn("").setCellEditor(new ButtonEditor(new JCheckBox()));
            table.setRowHeight(25);
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

    class ButtonRenderer extends JButton implements TableCellRenderer {
        private static final long serialVersionUID = 3432759493366670389L;

        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(UIManager.getColor("Button.background"));
            }
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    class ButtonEditor extends DefaultCellEditor {

        private static final long serialVersionUID = -2491215962333012994L;
        protected JButton button;
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
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
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
                int column) {
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

}