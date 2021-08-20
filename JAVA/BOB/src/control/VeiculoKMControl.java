
package control;

import data.VeiculoKMDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Auxiliar;
import model.VeiculoKM;

public class VeiculoKMControl {

    ArrayList<VeiculoKM> arraylist;
    VeiculoKMDAO vkmDAO;
    Auxiliar aux;
    PneuControl pc;
    AvisoControl ac;
    
    public VeiculoKMControl(){
        arraylist = new ArrayList<VeiculoKM>();
        vkmDAO = new VeiculoKMDAO();
        aux = new Auxiliar();
    }
    
    // deverá fazer uma consulta na base de dados e retornar a lista com todos os registros
    public ArrayList<VeiculoKM> getArrayListVeiculoKM(int idVeiculo){
        try{
            arraylist = vkmDAO.PesquisarTodosByIDVeiculo(idVeiculo);
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoKMControl.getArrayListVeiculoKM");
        }finally{
            return arraylist;
        }
    }
    public int getMediaKmPorDia(int idVeiculo){
        arraylist = getArrayListVeiculoKM(idVeiculo);
        int kmInicial = 0, kmFinal = 0, dias =0, diffkm=0, kmDia = 1;
        long dataInicial = 0, dataFinal = 0;
        if(arraylist.size()>=10){
            kmFinal = arraylist.get(arraylist.size() -1 ).getValor();
            dataFinal = arraylist.get(arraylist.size() -1 ).getDataMilis();
            kmInicial = arraylist.get(arraylist.size() -10 ).getValor();
            dataInicial = arraylist.get(arraylist.size() -10 ).getDataMilis();
        }else{
            kmFinal = arraylist.get(arraylist.size() -1 ).getValor();
            dataFinal = arraylist.get(arraylist.size() -1 ).getDataMilis();
            kmInicial = arraylist.get(0).getValor();
            dataInicial = arraylist.get(0).getDataMilis();
        }
        dias = aux.diffDiasPassados(dataFinal, dataInicial);
        dias = (dias>0) ? dias : 1;
        diffkm = kmFinal - kmInicial;
        diffkm = (diffkm<=0)? 1: diffkm;
        
        kmDia = (diffkm / dias); 
        kmDia = (kmDia<=0? 1: kmDia);
        return kmDia;
    }
    
    // deverá validar os parâmetros recebidos e salvar na base de dados
    
    private void atualizarGeral(int idveiculo){
        try{
            pc = new PneuControl();
            pc.atualizarKMsByIdVeiculo(idveiculo);
            ac = new AvisoControl();
            ac.atualizarAvisosByIdVeiculo(idveiculo, getUltimoKmByIDVeiculo(idveiculo));
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoKMControl.atualziarGeral");
        }
         
    }
    
    public boolean addVeiculoKM(int idVeiculo, int idUsuario, String data, String KM){
        try{
            VeiculoKM vkmUlt, vkm;
            vkm = new VeiculoKM();
            vkm.setIdVeiculo(idVeiculo);
            vkm.setIdUsuario(idUsuario);
            vkm.setValor(Integer.parseInt(KM));
            vkm.setDataMilis(aux.dataStringLong(data));
            
            vkmUlt = getUltimoVeiculoKMByIdVeiculo(idVeiculo);
            if(vkm.getValor()<vkmUlt.getValor()){
                aux.showMessageWarning("O valor da nova quilometragem deve ser maior que a anterior!", "Valor Inválido");
                return false;
            }
            if(JOptionPane.showConfirmDialog(null, "Confirma o valor da nova Quilometragem: \nNovo KM: "+vkm.getValor(), "Confirmação de valor", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                boolean retorno = vkmDAO.Inserir(vkm);
               
                if(retorno){
                    atualizarGeral(idVeiculo);
                   
                }
                return retorno;
            }
        }catch (NumberFormatException nfe){
           aux.showMessageWarning("Verifique o valor informado!", "Valor inválido");
           aux.RegistrarLog(nfe.getMessage(), "VeiculoKMControl.addVeiculoKM");
        }
        catch (Exception e){
            aux.showMessageWarning("Verifique os campos informados!", "Valor inválido");
            aux.RegistrarLog(e.getMessage(), "VeiculoKMControl.addVeiculoKM");
        }
        return false;
    }
    
    //Precisa implementar
    public VeiculoKM getUltimoVeiculoKMByIdVeiculo(int idVeiculo){
        return vkmDAO.getUltimoVeiculoKMByIDVeiculo(idVeiculo);
    }
    //Precisa implementar
   /* public VeiculoKM getVeiculoKMById(int id){
        return new VeiculoKM();
    }*/
    // retorna o último KM registrado do Veículo
    public int getUltimoKmByIDVeiculo(int idVeiculo){
        //precisa implementar
        return vkmDAO.getUltimoKMByIDVeiculo(idVeiculo);
    }
    public int getDiffUltimoKmByID(int idVeiculo, int km){
        int diff = getUltimoKmByIDVeiculo(idVeiculo)-km;
        return (diff==0?1:diff);
    }

    public int getKmUltPercorridoByIdVeiculo(int idveiculo) {
        int kmPercorrido = 0;
        try{
            arraylist = getArrayListVeiculoKM(idveiculo);
            if(arraylist.size()>1){
                kmPercorrido = (arraylist.get(arraylist.size() - 1).getValor() ) -(arraylist.get(arraylist.size() - 2).getValor()) ;
            }
        }catch(Exception e){
            kmPercorrido = 0;
        }
        return kmPercorrido;
    }

    public void excluirById(VeiculoKM veiculoKM) {
        try{
            vkmDAO.Excluir(veiculoKM);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoKMControl.excluirById");
        }
        
    }
    
    
}
