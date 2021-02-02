package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModelVehicle extends AbstractTableModel {
    String[] columnsVehicle = { "Brand", "Model", "Condition", "Production Year", "Mileage", "Engine Size"};
    public Object[][] data = null;

    TableModelVehicle(String nameSalon, CarShowroomContainer container){

        CarShowroom toDisplay = null;
        ArrayList<Object> dataArr = new ArrayList<Object>();
        for(CarShowroom c : container.salons.values()) {
            if(c.getSalonName() == nameSalon) {
                toDisplay = c;
            }
        }

        for(Vehicle ve : toDisplay.carList) {
            Object[] rowDataArr = {ve.getBrand(), ve.getModel(), ve.getCondition(), ve.getProduction_year(), ve.getMileage(), ve.getEngineSize()};
            dataArr.add(rowDataArr);
            //  i++;
        }
        data = dataArr.toArray(new Object[0][]);

    }

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
}
