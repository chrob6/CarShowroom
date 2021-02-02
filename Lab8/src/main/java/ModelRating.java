
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ModelRating extends AbstractTableModel {
    String[] columnsRatings = { "Grade", "Description", "Date"};
    public Object[][] data = null;

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnsRatings.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    ModelRating(List<Rating> ratings){

        ArrayList<Object> dataArr = new ArrayList<Object>();

        for (Rating r : ratings) {
            Object[] rowDataArr = {r.getGrade(), r.getDescription(), r.getDate()};
            dataArr.add(rowDataArr);
        }
        data = dataArr.toArray(new Object[0][]);
    }

    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }


}

