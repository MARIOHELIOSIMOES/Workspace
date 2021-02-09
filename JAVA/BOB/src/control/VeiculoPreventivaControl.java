package control;

import data.VeiculoPreventivaDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.Item;
import model.Pedido;
import model.PedidoItem;
import model.VeiculoKM;
import model.VeiculoPreventiva;

public class VeiculoPreventivaControl {
    VeiculoPreventivaDAO vpd;
    Auxiliar aux;
    ItemControl itemCtrol;
    public VeiculoPreventivaControl(){
        vpd = new VeiculoPreventivaDAO();
        aux = new Auxiliar();
        itemCtrol = new ItemControl();
    }
    
    public VeiculoPreventiva getVeiculoPrenventivaByIdVeiculoAndByIdTipo(int idVeiculo, int tipo){
        ArrayList<VeiculoPreventiva> arraylist = getArrayListPreventivasPendentesByIdVeiculoByTipo(idVeiculo, new int[]{tipo});
        
        if(arraylist.size()>0){
            return arraylist.get(0);
        }
        VeiculoPreventiva vp = new VeiculoPreventiva();
        vp.setId_veiculo(idVeiculo);
        vp.setKm(0);
        vp.setKmProx(1);
        vp.setDatamilis(aux.getDataMilisAtual());
        vp.setTipo(tipo);
        return vp;
    }
    private void inserirVPBase(int idVeiculo){
        ArrayList<VeiculoPreventiva> arrayListVP = new ArrayList<>();
        VeiculoPreventiva vp;
        try{
            for(int i = 0; i< Item.TIPOS_INT.length; i++){
                vp = new VeiculoPreventiva();
                vp.setId_veiculo(idVeiculo);
                vp.setDatamilis(aux.getDataMilisAtual());
                vp.setKm(1);
                vp.setKmProx(1);
                vp.setTipo(Item.TIPOS_INT[i]);
                arrayListVP.add(vp);
            }
            vpd.InserirArray(arrayListVP);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPreventivaControl.inserirVPBase");
        }
    }
    public ArrayList<VeiculoPreventiva> getArrayListPreventivaByIdVeiculoPendete(int idVeiculo){
        ArrayList<VeiculoPreventiva> arraylist;
            
        try{
            //inserirVPBase(idVeiculo);
            arraylist = vpd.getArrayListPreventivaByIdVeiculoByIDPedido(idVeiculo, 0, true);
            
        }catch (Exception e){
            arraylist = new ArrayList<VeiculoPreventiva>();
        }
        return arraylist;
    }
    public ArrayList<VeiculoPreventiva> getArrayListPreventivasFeitasByIdVeiculo(int idVeiculo){
        ArrayList<VeiculoPreventiva> arraylist;
            
        try{
            arraylist = vpd.getArrayListPreventivaByIdVeiculoByIDPedido(idVeiculo, 0, false);
        }catch (Exception e){
            arraylist = new ArrayList<VeiculoPreventiva>();
        }
        return arraylist;
    }
    private ArrayList<VeiculoPreventiva> getArrayListPreventivasByIdVeiculoByTipo(int idVeiculo, int[] tipos, boolean pendente){
        ArrayList<VeiculoPreventiva> arraylist, arraytipo;
            arraytipo = new ArrayList<VeiculoPreventiva>();
            try{
                arraylist = vpd.getArrayListPreventivaByIdVeiculoByIDPedido(idVeiculo, 0, pendente);

                for(VeiculoPreventiva vp : arraylist){
                    for(int tipo: tipos){
                        if(tipo==vp.getTipo()){
                            arraytipo.add(vp);
                        }
                    }
                }
            }catch (Exception e){
                arraytipo = new ArrayList<VeiculoPreventiva>();
            }
            return arraytipo;
    }
    public ArrayList<VeiculoPreventiva> getArrayListPreventivasFeitasByIdVeiculoByTipo(int idVeiculo, int[] tipos){
        return getArrayListPreventivasByIdVeiculoByTipo(idVeiculo, tipos, false);
    }
    public ArrayList<VeiculoPreventiva> getArrayListPreventivasPendentesByIdVeiculoByTipo(int idVeiculo, int[] tipos){
        return getArrayListPreventivasByIdVeiculoByTipo(idVeiculo, tipos, true);
    }
    
    //Recebe um Pedido e atualiza as datas de manutenção preventiva pelos itens do pedido
    public boolean atualizarPreventivaByPedido(Pedido pedido, ArrayList<VeiculoPreventiva> vpfuturas){
        try{
            int[] ids = new int[pedido.getArraylist().size()];
            int i =0;
            for(PedidoItem pi: pedido.getArraylist()){
                ids[i]=pi.getIdItem();
                i++;
            }
            ArrayList<Item> arraylistItems = itemCtrol.getArrayListIDS(ids);
            
            if(arraylistItems.size()>0){
                String tiposString = " ";
                for(Item item: arraylistItems){
                    tiposString +=item.getTipo()+", ";
                }
                tiposString+=arraylistItems.get(0).getTipo();
                if(vpd.AlterarPedido(pedido.getId_veiculo(), pedido.getId(), tiposString)){
                    vpd.InserirArray(vpfuturas);
                }
            }
        }catch(Exception e){
            
        }
        return true;
    }
    
    
    
    
}
