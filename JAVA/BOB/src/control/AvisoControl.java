package control;

import data.AvisoDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.Aviso;
import model.GraficoItem;
import model.GraficoItemC;
import model.Item;
import model.Veiculo;
import model.VeiculoPreventiva;

public class AvisoControl {
    
    ArrayList<Aviso> arrayList;
    Auxiliar aux;
    AvisoDAO ad;
    VeiculoPreventivaControl vpc;
    
    public AvisoControl(){
        arrayList = new ArrayList<Aviso>();
        aux = new Auxiliar();
        ad = new AvisoDAO();
        
    }
    public ArrayList<Aviso> getArrayListAtivosInativo(boolean status){
        try{
            arrayList = ad.getArrayAvisosAtivo(status);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoControl.getArrayListAtivosInativo");
        }
        return arrayList;
    }
    public ArrayList<Aviso> getArrayListAtivosByIdVeiculo(int idveiculo){
        try{
            arrayList = ad.getArrayAvisoByIdVeiculoAndAtivo(idveiculo, true);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoControl.getArrayListAtivosByIdVeiculo");
        }
        return arrayList;
    }
    public ArrayList<Aviso> getArrayListInativosByIdVeiculo(int idveiculo){
        try{
            arrayList = ad.getArrayAvisoByIdVeiculoAndAtivo(idveiculo,false);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoControl.getArrayListInativosByIdVeiculo");
        }
        return arrayList;
    }
    public ArrayList<Aviso> getArrayListTodosByIdVeiculo(int idveiculo){
        try{
            arrayList = ad.getArrayAvisoByIdVeiculo(idveiculo);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoControl.getArrayListTodosByIdVeiculo");
        }
        return arrayList;
    }

    public boolean excluirInativar(Aviso aviso) {
       try{
           boolean retorno = false;
           if(aviso.isAtivo()){
               aviso.setAtivo(false);
               retorno = ad.Alterar(aviso);
           }else{
               retorno = ad.Excluir(aviso);
           }
           
           if(retorno){
                aux.showMessageInformacao("Salvo com sucesso!", "Avisos");
                return true;
            }else{
                aux.showMessageWarning("Falha ao salvar!", "Avisos");
                return false;
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoControl");
            return false;
        }
    }

    public void inserir(Aviso aviso) {
        try{
            if(ad.Inserir(aviso)){
                aux.showMessageInformacao("Salvo com sucesso!", "Avisos");
            }else{
                aux.showMessageWarning("Falha ao salvar!", "Avisos");
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoControl");
        }
    }

    public int getNAtivosByIdVeiculo(int idveiculo) {
        int nAtivos = 0;
        try{
            nAtivos = getArrayListAtivosByIdVeiculo(idveiculo).size();
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoControl.getNAtivosByIdVeiculo");
            nAtivos = 0;
        }
        return nAtivos;
    }

    public void atualizarAvisosByIdVeiculo(int idveiculo, int kmAtualizado) {
        vpc = new VeiculoPreventivaControl();
        ArrayList<VeiculoPreventiva> arrayPreventiva = vpc.getArrayListPreventivasPendentesByIdVeiculoByTipo(idveiculo, Item.TIPOS_INT);
        for(VeiculoPreventiva vp : arrayPreventiva){
            if(kmAtualizado>=vp.getKmProx()){
                atualizarAvisoByIdVeiculoAndTipoItem(idveiculo, vp.getTipo());
            }
        }
    }
    public void excluirByIdVeiculoAndByTitulo(int idveiculo, int[] tipos){
        try{
            String titulos = "";
            if(tipos.length>0){
                titulos+="'"+Item.TIPO_STRING[tipos[0]]+"'";
            }else{
                return;
            }
            for(int i: tipos){
                titulos+=", '" + Item.TIPO_STRING[i]+"'";
            }
            ad.ExcluirByIdVeiculoAndTitulo(idveiculo, titulos);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoControl.excluirByIdVeiculoAndByTitulo");
        }
   }
    private void atualizarAvisoByIdVeiculoAndTipoItem(int idveiculo, int tipo) {
        try{
            Aviso aviso = new Aviso();
            aviso.setIdveiculo(idveiculo);
            aviso.setTitulo(Item.TIPO_STRING[tipo]);
            aviso.setMensagem("Km vencido em "+aux.getDataStringAtual());
            aviso.setAtivo(true);
            aviso.setDatamilis(aux.getDataMilisAtual());
            ad.InserirIfNotExist(aviso);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoControl.atualizarAvisoByIdVeiculoAndTipoItem");
        }
    }

    public int getNAtivos() {
        int nAtivos = 0;
        try{
            nAtivos = ad.getQtdeAtivos(true);
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoControl.getNAtivosByIdVeiculo");
            nAtivos = 0;
        }
        return nAtivos;
    }

    public ArrayList<GraficoItem> getArrayGraficoCategoria(){
        int limite = 0;
        ArrayList<GraficoItem> arrayGrafico = new ArrayList<GraficoItem>();
        ArrayList<Veiculo> arrayVeiculo = new ArrayList<Veiculo>();
        try{
            arrayVeiculo = new VeiculoControl().getArrayListTodosVeiculos();
            arrayList = getArrayListAtivosInativo(true);
            if(arrayList.size()<=0){
                GraficoItem grafC = new GraficoItem("Placa", 0);
                arrayGrafico.add(grafC);
                return arrayGrafico;
            }
            
            for(Veiculo v: arrayVeiculo){
                arrayGrafico.add(new GraficoItem(v.getPlaca(), getNAtivosByIdVeiculo(v.getId())));
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "PedidoOleoControl.getArrayGraficoC");
        }
        return arrayGrafico;
    }
    
    
}
