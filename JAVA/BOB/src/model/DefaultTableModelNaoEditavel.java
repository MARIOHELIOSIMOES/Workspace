package model;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class DefaultTableModelNaoEditavel extends DefaultTableModel{

      
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
    
}
