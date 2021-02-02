package com.lab7;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelCarshowroom extends AbstractTableModel {

    String[] columnsCarShowrooms = { "Salon name", "Max Cars"};
    public Object[][] data = null;


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

    @Override
    public String getColumnName(int col) {
        return columnsCarShowrooms[col];
    }

    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    ModelCarshowroom(CarShowroomContainer container) {
            ArrayList<Object> dataArr = new ArrayList<Object>();

            for(CarShowroom c : container.salons.values()) {
               Object[] rowDataArr = {c.getSalonName(), c.getCurrentCarsAmount(), c.getMaxCars()
                        };
               dataArr.add(rowDataArr);

            }
            data = dataArr.toArray(new Object[0][]);
        }

    ModelCarshowroom(List<CarShowroom> salons) {
        ArrayList<Object> dataArr = new ArrayList<Object>();

        for(CarShowroom c : salons) {
            Object[] rowDataArr = {c.getSalonName(), c.getMaxCars()
            };
            dataArr.add(rowDataArr);

        }
        data = dataArr.toArray(new Object[0][]);
    }

}
