package control;

import data.PedidoDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.PedidoItem;

public class PedidoItemControl {
    
    ArrayList<PedidoItem> arraylist;
    PedidoDAO pedidoDao;
    Auxiliar aux;
    public PedidoItemControl(){
        arraylist = new ArrayList<PedidoItem>();
        pedidoDao = new PedidoDAO();
        aux = new Auxiliar();
    }
    
    public float getValorTotalByIdPedido(int idPedido){
        
        float valorTotal =0;
        try{
            arraylist = pedidoDao.getArrayListPedidoItemByIdPedido(idPedido);
            if(arraylist.size()<=0){
                return 0;
            }
            for(int i = arraylist.size()-1; i>=0; i--){
                valorTotal+=arraylist.get(i).getValorTotal();
            }
        
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoItemControl.getValorTotalByIdPedido");
        }finally{
            return valorTotal;
        }
    }

}
