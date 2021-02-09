package Control;

import Dao.PedidoDAO;
import Model.Pedido;

import javax.swing.*;

public class PedidoControl {
    private PedidoDAO pedidoDAO;

    public PedidoControl(){
        pedidoDAO = new PedidoDAO();
    }
    public void Inserir(Pedido pedido){
        try{
            pedidoDAO.Inserir(pedido);
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!", "Pedido",JOptionPane.INFORMATION_MESSAGE);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao gerar o pedido: "+e.getMessage());
        }
    }
}
