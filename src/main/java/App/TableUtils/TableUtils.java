package App.TableUtils;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.*;

import App.BackEnd.Avion;
import App.BackEnd.Viaje;
import App.Data.Provider;

public class TableUtils {
    public static JTable populatetable(JTable table, String[] header, String[][] values) {
        DefaultTableModel tablemodel = (DefaultTableModel) table.getModel();

        tablemodel.setColumnCount(0);
        tablemodel.getDataVector().removeAllElements();
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

    public static void calcularTotal(JPanel panel, JLabel totalLabel) {
        if (!Provider.getViajes().get(Provider.getCurrentUser().get("username")).isEmpty()) {
            totalLabel = new JLabel();
            totalLabel.setBounds(625, 500, 150, 25);

            double total = 0;
            for (Viaje viaje : Provider.getViajes().get(Provider.getCurrentUser().get("username"))) {

                String curso = viaje.getOrigen() + "-" + viaje.getDestino();

                if (Provider.getDistancias().get(curso) == null) {
                    curso = viaje.getDestino() + "-" + viaje.getOrigen();
                }

                Avion avionSeleccionado = viaje.getAvion();

                total += avionSeleccionado.getValorTotal(viaje, curso);
            }
            System.out.println(totalLabel.getText());
            totalLabel.setText("Total Gastado: " + String.valueOf(total));
            ;

            for (Component element : panel.getComponents()) {
                if (element instanceof JLabel) {
                    JLabel label = (JLabel) element;
                    if (!label.getText().equals("")) {
                        panel.remove(element);
                    }

                }
            }

            panel.add(totalLabel, "cell 1 5, align right");
            panel.revalidate();
            panel.repaint();
            System.out.println(totalLabel.getText());
        }

    }
}
