
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelCarshowroom extends AbstractTableModel {

    String[] columnsCarShowrooms = { "Salon name","Current cars", "Max Cars", "Sum Rating","Avg Rating"};
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



    ModelCarshowroom(List<Carshowroom> salons, List<Object []> rating, List<Object []> vehicles) {
        ArrayList<Object> dataArr = new ArrayList<Object>();

        Integer i = 0;
        for(Carshowroom c : salons) {
            Object[] rowDataArr;
           try {
               rowDataArr = new Object[]{c.getSalonname(), vehicles.get(i)[0], c.getMaxcars(), rating.get(i)[0], rating.get(i)[1]};
           }
           catch(IndexOutOfBoundsException e){
               rowDataArr = new Object[]{c.getSalonname(), vehicles.get(i)[0],  c.getMaxcars(), 0, 0};
           }

            dataArr.add(rowDataArr);
            i++;
        }
        data = dataArr.toArray(new Object[0][]);
    }

}
