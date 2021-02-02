package com.lab7;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class ModelVehicle extends AbstractTableModel {
    String[] columnsVehicle = { "Brand", "Model", "Condition", "Production Year", "Mileage", "Engine Size","Price"};
    public Object[][] data = null;

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnsVehicle.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    ModelVehicle(String nameSalon, CarShowroomContainer container){

        CarShowroom toDisplay = null;
        ArrayList<Object> dataArr = new ArrayList<Object>();
        for(CarShowroom cont : container.salons.values()) {
            if(cont.getSalonName() == nameSalon) {
                toDisplay = cont;
            }
        }

        if(toDisplay != null) {
            for (Vehicle veh : toDisplay.carList) {
                Object[] rowDataArr = {veh.getBrand(), veh.getModel(), "new", veh.getProduction_year(), veh.getMileage(), veh.getEngineSize(), veh.getPrice()};
                dataArr.add(rowDataArr);
                //  i++;
            }
        }

        data = dataArr.toArray(new Object[0][]);

    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }


}
