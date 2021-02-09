package Control;

import Dao.PedidoDAO;
import Dao.RestauranteDAO;
import Dao.UsuarioDAO;
import Model.*;

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
            new Exception("Erro ao gerar o pedido!");
        }
    }
    public Object[] PesquisarDesc(String descricao){
        Object[] retorno = new Object[3];
        try{
            retorno[0]= 1;
            retorno[1]= pedidoDAO.PesquisarTodosByDesc(descricao);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao realizar a pesquisa: "+e.getMessage(),"Pesquisar", JOptionPane.ERROR_MESSAGE);
            retorno[0]= -1;
        }
        return retorno;
    }
    public Object[] Pesquisar(int[] parametros, long dataInicio, long dataFinal, int[] idRestaurante, String descricao){
        Object[] retorno = new Object[3];
        boolean and=false;
        String sql="";
        
        if(parametros[0]==1){
            sql = sql + " timemili >= "+dataInicio;
            and=true;
        }
        if(parametros[1]==1){
            if(and){
                sql = sql+ " AND ";
            }
            sql = sql + " timemili <= "+dataFinal;
            and=true;
        }
        if(parametros[3]==1){
            if(and){
                sql = sql+ " AND ";
            }
            sql = sql + " id_restaurante in ( ";
            for(int i=0; i<idRestaurante.length; i++){
                sql = sql + (idRestaurante[i])+", ";
            }
            sql = sql + idRestaurante[0]+" )" ;
            and=true;
        }
      //  if(parametros[4]==1){
            if(and){
                sql = sql+ " AND ";
            }
            sql = sql + " descricao LIKE'%"+descricao+"%'";
        //}
        
        try{
            retorno[0]= 1;
            retorno[1]= pedidoDAO.PesquisarTodosBySQLstring(sql);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro ao realizar a pesquisa: "+e.getMessage(),"Pesquisar", JOptionPane.ERROR_MESSAGE);
            retorno[0]= -1;
        }
        return retorno;
    }
    
    
    public void ImprimirUltimoPedido(){
        try{
            Pedido pedido = pedidoDAO.UltimoPedido();
            ImprimirPedido(pedido);
           
        }catch(Exception e){
            
        }
    }
    public void ImprimirPedido(Pedido pedido){
            Usuario usuario = new UsuarioDAO().PesquisarById(pedido.getIdUsuario());
            Restaurante restaurante = new RestauranteDAO().PesquisarById(pedido.getIdRestaurante());
            new pdfControl().CriarDocumentoPDF(pedido, restaurante, usuario);
    }
    
}
