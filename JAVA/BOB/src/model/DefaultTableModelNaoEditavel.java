package model;

import javax.swing.table.DefaultTableModel;

public class DefaultTableModelNaoEditavel extends DefaultTableModel{

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
