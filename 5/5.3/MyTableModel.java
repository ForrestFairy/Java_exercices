import javax.swing.table.*;

public class MyTableModel extends DefaultTableModel {

    public MyTableModel(Object[][] data, Object[] columns) {
        super(data, columns);
    }

    public boolean isCellEditable(int row, int col) {
        if (col == 3) return true;
        return false;
    }

    public Object getValueAt(int row, int col) {
        if (col == 0) return new Integer(row + 1);
        return super.getValueAt(row, col);
    }
}