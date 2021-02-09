package control;

import Exception.ValorInvalidoException;
import data.PedidoDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.GraficoItemC;
import model.Pedido;
import model.PedidoItem;
public class PedidoControl {

    Auxiliar aux;
    PedidoDAO pDao;
    PedidoItemControl pic;
    public PedidoControl(){
        inicializar();
    }
    private void inicializar(){
        aux = new Auxiliar();
        pDao = new PedidoDAO();
        pic = new PedidoItemControl();
    }
    
    public boolean Inserir(Pedido pedido){
        try{
            if(pedido.getId()==0){
                if(pDao.Inserir(pedido)){
                    aux.showMessageInformacao("Salvo com sucesso!", "Pedido");
                }else{
                    return false;
                }
            }else{
                if(pDao.Alterar(pedido)){
                    aux.showMessageInformacao("Alterado com sucesso!", "Pedido");
                }else{
                    return false;
                }
                    
            }
        }catch(Exception e){
            return false;
        }
        return true;
    }
    public boolean Inserir(String id, int idVeiculo, int tipoPedido, String km, String dataMilis, String info, ArrayList<PedidoItem> arraylist){
        try{
            Pedido pedido = new Pedido();
            pedido.setId(Integer.parseInt(id));
            pedido.setId_veiculo(idVeiculo);
            pedido.setTipo(tipoPedido);
            pedido.setKm(Integer.parseInt(km));
            pedido.setDatamilis(new Auxiliar().dataStringLong(dataMilis));
            pedido.setInfo(info);
            if(arraylist.size()<=0){
                throw new ValorInvalidoException("Lista de Itens vazia","");
            }else{
                pedido.setArraylist(arraylist);
            }
            if(pedido.getId()==0){
                if(pDao.Inserir(pedido)){
                    aux.showMessageInformacao("Salvo com sucesso!", "Pedido");
                }else{
                    return false;
                }
            }else{
                if(pDao.Alterar(pedido)){
                    aux.showMessageInformacao("Alterado com sucesso!", "Pedido");
                }else{
                    return false;
                }
            }
        }catch (Exception e){
            aux.showMessageWarning("Verifique os campos informados!", "Valor inválido");
        }
        return true;
    }
    
    //NOTA: TALVEZ PRECISE ORDEM POR ALGUM CAMPO..
    public Pedido getUltimoPedidoByIdVeiculoByTipo(int idVeiculo, int tipoPedido){
        ArrayList<Pedido> arraylist = new ArrayList<Pedido>();
        Pedido pedido = new Pedido();
        try{
            arraylist = pDao.PesquisarTodosPedidoByIDVeiculoAndByTipo(idVeiculo, tipoPedido);
            pedido = (arraylist.size()>0)? arraylist.get(arraylist.size()-1): new Pedido();
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoControl.getUltimoPedidoByIdVeiculoByTipo");
        }finally{
            return pedido;
        }
    }
    public ArrayList<Pedido> getArrayListPedidoByIdVeiculoByTipo(int idVeiculo, int tipoPedido){
        ArrayList<Pedido> arraylist = new ArrayList<Pedido>();
        
        try{
            arraylist = pDao.PesquisarTodosPedidoByIDVeiculoAndByTipo(idVeiculo, tipoPedido);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoControl.getArraListPedidoByIdVeiculoByTipo");
        }finally{
            return arraylist;
        }
    }
    public ArrayList<Pedido> getArrayListPedidoByIdVeiculo(int idVeiculo){
        ArrayList<Pedido> arraylist = new ArrayList<Pedido>();
        try{
            arraylist = pDao.PesquisarTodosPedidoByIDVeiculo(idVeiculo);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoControl.getArraListPedidoByIdVeiculo");
        }
        finally{
            return arraylist;
        }
    }
    public Pedido getUltimoPedidoByIdVeiculo(int idVeiculo){
        ArrayList<Pedido> arraylist = getArrayListPedidoByIdVeiculo(idVeiculo);
        try{
            if(arraylist.size()>0){
                return arraylist.get(arraylist.size()-1);
            }else{
                return new Pedido();
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoControl.getUltimoPedidoByIdVeiculo");
            return new Pedido();
        }
    }
    protected GraficoItemC getCalcularGraficoItemC(Pedido atualPedido, Pedido proxPedido, String id){
        GraficoItemC grafC = new GraficoItemC();
        try{
            float custo = pic.getValorTotalByIdPedido(atualPedido.getId());//Custo pedido Atual
            int diffKm = proxPedido.getKm() - atualPedido.getKm();// diferença dos KM atual e o próximo
            diffKm = (diffKm<=0)? 1 : diffKm;
            float custoKM = custo / diffKm;
            grafC.setNome(id);
            grafC.setCat1("Valor do pedido");
            grafC.setValor(custo);
            grafC.setCat2("Km percorrido");
            grafC.setValor2(diffKm);
            grafC.setCat3("Custo por Km");
            grafC.setValor3(custoKM);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoControl.getCalcularGraficoItemC");
        }finally{
            return grafC;
        }
    }
    public boolean Excluir(Pedido pedido){
        try{
            return pDao.Excluir(pedido);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoControl.Excluir");
            return false;
        }
    }
    
}
