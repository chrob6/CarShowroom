
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelVehicle extends AbstractTableModel {
    String[] columnsVehicle = { "Brand", "Model", "Condition", "Production Year", "Mileage", "Engine Size","Price", "Sum Rating","Avg Rating"};
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

    ModelVehicle(List<Vehicle> vehicle, List<Object []> ratings){


       ArrayList<Object> dataArr = new ArrayList<Object>();
        Object[] rowDataArr = new Object[0];

        for (Vehicle veh : vehicle) {
            boolean into = true;
            for (int j = 0; j < ratings.size(); j++) {
                Vehicle vehicleToCompere = (Vehicle) ratings.get(j)[2];
                if (vehicleToCompere.getId() == veh.getId()) {
                    rowDataArr = new Object[]{veh.getBrand(), veh.getModel(), "new", veh.getProduction_year(), veh.getMileage(), veh.getEnginesize(), veh.getPrice(), ratings.get(j)[0], ratings.get(j)[1]};
                    into = false;
                }
            }
            if(into)
            rowDataArr = new Object[]{veh.getBrand(), veh.getModel(), "new", veh.getProduction_year(), veh.getMileage(), veh.getEnginesize(), veh.getPrice(), 0, 0};



            dataArr.add(rowDataArr);
        }
        data = dataArr.toArray(new Object[0][]);
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }


}

