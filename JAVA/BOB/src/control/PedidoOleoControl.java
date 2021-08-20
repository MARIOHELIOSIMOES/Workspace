package control;

import java.util.ArrayList;
import model.Auxiliar;
import model.Pedido;
import model.VeiculoOleoCheck;
import model.GraficoItem;
import model.GraficoItemC;

public class PedidoOleoControl extends PedidoControl{

    ArrayList<Pedido> arraylist;
    Auxiliar aux;
    //PedidoItemControl pic;
    public PedidoOleoControl(){
        arraylist = new ArrayList<Pedido>();
        aux = new Auxiliar();
      //  pic = new PedidoItemControl();
    }
    public Pedido getUltimoPedidoByIdVeiculo(int idVeiculo){
        return getUltimoPedidoByIdVeiculoByTipo(idVeiculo, Pedido.OLEOFILTRO);
    }
    public VeiculoOleoCheck getUltimoOleoCheckByIdVeiculo(int idVeiculo){
        return new VeiculoOleoCheck();
    }
    public VeiculoOleoCheck getOleoCheckByIdPedido(int idPedido){
        return new VeiculoOleoCheck();
    }
    public ArrayList<GraficoItem> getArrayGrafico(int idVeiculo, int nRegistros){
        int limite = 0;
        
        ArrayList<GraficoItem> arrayGrafico = new ArrayList<>();
        try{
            arraylist = getArrayListPedidoByIdVeiculoByTipo(idVeiculo, Pedido.OLEOFILTRO);
            if(arraylist.size()<=0){
                GraficoItem gi = new GraficoItem();
                gi.setNome("Sem registros");
                gi.setValor(0);
                arrayGrafico.add(gi);
                return arrayGrafico;
            }
            
            limite = arraylist.size()- nRegistros - 1;
            limite = limite>=0? limite : 0;

            for(int i = arraylist.size()-1; i>=limite; i--){
                GraficoItem gi = new GraficoItem();
                gi.setNome((i+1)+"");
                gi.setValor(pic.getValorTotalByIdPedido(arraylist.get(i).getId()));
                arrayGrafico.add(gi);
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoOleoControl.getARrayGrafico");
        }
        return arrayGrafico;
    }
    public ArrayList<GraficoItemC> getArrayGraficoC(int idVeiculo, int nRegistros){
        int limite = 0;
        Pedido pedidoProximo = new Pedido();// Usado para calcular o pedido em aberto, set apenas o km 
        ArrayList<GraficoItemC> arrayGrafico = new ArrayList<GraficoItemC>();
       
        try{
            pedidoProximo.setKm(new VeiculoKMControl().getUltimoKmByIDVeiculo(idVeiculo));
            arraylist = getArrayListPedidoByIdVeiculoByTipo(idVeiculo, Pedido.OLEOFILTRO);
            if(arraylist.size()<=0){
                GraficoItemC grafC = new GraficoItemC();
                grafC.setCat1("Valor Pedido");
                grafC.setValor(0);
                grafC.setCat2("KM percorrido");
                grafC.setValor2(0);
                grafC.setCat3("Custo KM");
                grafC.setValor3(0);
                arrayGrafico.add(grafC);
                return arrayGrafico;
            }
            
            limite = arraylist.size()- nRegistros - 1;
            limite = limite>=0? limite : 0;
            pedidoProximo = arraylist.get(arraylist.size()-1);
            Pedido pedidoAtual;
            for(int i = arraylist.size()-2; i>=limite; i--){
                pedidoAtual = arraylist.get(i);
                arrayGrafico.add(getCalcularGraficoItemC(pedidoAtual, pedidoProximo, (i+1)+""));
                pedidoProximo = pedidoAtual;
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoOleoControl.getArrayGraficoC");
        }
        return arrayGrafico;
    }
    public ArrayList<GraficoItem> getArrayGraficoLinha(int idVeiculo, int nRegistros){
        int limite = 0;
        //Pedido pedidoProximo = new Pedido();// Usado para calcular o pedido em aberto, set apenas o km 
        ArrayList<GraficoItem> arrayGrafico = new ArrayList<GraficoItem>();
       
        try{
            //pedidoProximo.setKm(new VeiculoKMControl().getUltimoKmByIDVeiculo(idVeiculo));
            
            arraylist = getArrayListPedidoByIdVeiculoByTipo(idVeiculo, Pedido.OLEOFILTRO);
            if(arraylist.size()<=0){
                GraficoItem graficoItem = new GraficoItem();
                graficoItem.setNome("Custo Km");
                graficoItem.setValor(0);
               
                arrayGrafico.add(graficoItem);
                return arrayGrafico;
            }
            
            limite = arraylist.size()- nRegistros - 1;
            limite = limite>=0? limite : 0;
            
           // pedidoProximo = arraylist.get(arraylist.size()-1);
            Pedido pedidoAtual, pedidoProximo;
           // for(int i = arraylist.size()-2; i>=limite; i--){
            for(int i = limite;  i < arraylist.size()-1; i++){
                pedidoAtual = arraylist.get(i);
                pedidoProximo = arraylist.get(i+1);
                arrayGrafico.add(getCalcularGraficoItem(pedidoAtual, pedidoProximo, (i+1)+""));
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoOleoControl.getArrayGraficoC");
        }
        return arrayGrafico;
    }
    
    
    
    public float getMediaCustoGeral(int idVeiculo, int nRegistros){
        
        float valorTotal = 0, custo = 0;
        int j = 0, kmInicial=0, kmFinal=0, limite=0;
        
        try{
            arraylist = getArrayListPedidoByIdVeiculoByTipo(idVeiculo, Pedido.OLEOFILTRO);
            if(arraylist.size()<=0){
                return 0;
            }
            limite = arraylist.size()- nRegistros - 1;
            limite = limite>=0? limite : 0;

            for(int i = arraylist.size()-2; i>=limite; i--){
                valorTotal+=pic.getValorTotalByIdPedido(arraylist.get(i).getId());
                j++;
            }
            
            kmInicial = arraylist.get(limite).getKm();
            kmFinal = arraylist.get(arraylist.size()-1).getKm();
            kmFinal = kmFinal - kmInicial;
            custo = (kmFinal>0) ? (valorTotal/kmFinal) : (valorTotal/1);

        }catch(Exception e){
            return 0;
        }
        return custo;
    }
    public float getMediaCustoAtual(int idVeiculo){
        
        float  custo = 0;
        int j = 0, diffkm = 0;
        
        try{
            arraylist = getArrayListPedidoByIdVeiculoByTipo(idVeiculo, Pedido.OLEOFILTRO);
            if(arraylist.size()<=0){
                return 0;
            }
            custo+=pic.getValorTotalByIdPedido(arraylist.get(arraylist.size()-1).getId());
            diffkm = new VeiculoKMControl().getUltimoKmByIDVeiculo(idVeiculo) - arraylist.get(arraylist.size() - 1).getKm();
            diffkm = (diffkm <=0? 1: diffkm);
            custo = custo / diffkm;

        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoOleoControl.getMediaCustoAtual");
            return 0;
        }
        return custo;
    }
}
