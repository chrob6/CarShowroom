package com.lab7;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelCart extends AbstractTableModel {


    String[] columnNames = { "Brand", "Model", "Condition", "Production Year", "Mileage", "Engine Size","Price"};
    public Object[][] data = null;

    public ModelCart() {

    }

    public ModelCart(CarShowroom cart) {
        try {
            ArrayList<Object> dataArr = new ArrayList<Object>();

            for (Vehicle veh : cart.carList) {
                Object[] rowDataArr = {veh.getBrand(), veh.getModel(), "new", veh.getProduction_year(), veh.getMileage(), veh.getEngineSize(), veh.getPrice()};
                dataArr.add(rowDataArr);
                //  i++;
            }
            data = dataArr.toArray(new Object[0][]);
        }
        catch (NullPointerException ex) {
            System.out.print("Empty cart");
        }
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
}
