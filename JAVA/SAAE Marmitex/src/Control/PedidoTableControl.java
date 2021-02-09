package Control;

import Model.Pedido;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class PedidoTableControl extends AbstractTableModel {

    public static final int COl_ID = 0;
    public static final int COl_ID_USUARIO = 1;
    public static final int COl_ID_RESTAURANTE = 2;
    public static final int COl_DATA = 3;
    public static final int COl_DESCRICAO = 4;

    public ArrayList<Pedido> pedidoArrayList;

    public PedidoTableControl(ArrayList<Pedido> l){
        pedidoArrayList = new ArrayList<>(l);
    }

    @Override
    public int getRowCount() {
        return pedidoArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Pedido pedido = pedidoArrayList.get(rowIndex);
        switch (columnIndex){
            case COl_ID:
                return pedido.getId();
            case COl_ID_USUARIO:
                return pedido.getIdUsuario();
            case COl_ID_RESTAURANTE:
                return pedido.getIdRestaurante();
            case COl_DATA:
                return pedido.getDia();
            case COl_DESCRICAO:
                return pedido.getDescricao();
            default:
        }

        return null;
    }
    @Override
    public String getColumnName(int colunas){
        switch (colunas){
            case COl_ID:
                return "ID";
            case COl_ID_USUARIO:
                return "ID_USUARIO";
            case COl_ID_RESTAURANTE:
                return "ID_RESTAURANTE";
            case COl_DATA:
                return "DATA";
            case COl_DESCRICAO:
                return "DESCRICAO";
            default:
                return "";
        }
    }
}
