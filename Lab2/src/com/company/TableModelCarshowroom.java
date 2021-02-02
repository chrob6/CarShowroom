package com.company;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class TableModelCarshowroom extends AbstractTableModel {

    String[] columnsCarShowrooms = { "Salon name", "Current cars", "Max Cars"};
    public Object[][] data = null;

    TableModelCarshowroom(CarShowroomContainer container) {
            ArrayList<Object> dataArr = new ArrayList<Object>();

            for(CarShowroom c : container.salons.values()) {
               Object[] rowDataArr = {c.getSalonName(), c.getCurrentCarsAmount(), c.getMaxCars()
                        };
               dataArr.add(rowDataArr);

            }
            data = dataArr.toArray(new Object[0][]);
        }


    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnsCarShowrooms.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }
}
