
package control;

import data.ManutencaoDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Auxiliar;
import model.Manutencao;
import model.Usuario;

public class ManutencaoControl {

    ArrayList<Manutencao> arraylist;
    Auxiliar aux;
    ManutencaoDAO mdao;
    VeiculoKMControl vkm;
    
    
    
    public ManutencaoControl(){
       inicializar();
       
    }
    private void inicializar(){
        arraylist = new ArrayList<Manutencao>();
        aux = new Auxiliar();
        mdao = new ManutencaoDAO();
        vkm = new VeiculoKMControl();
       
    }
    public ArrayList<Manutencao> getArrayListManutencaoTodosByIdVeiculo(int id_veiculo){
        try{
            arraylist = mdao.PesquisarTodosByIDVeiculo(id_veiculo);
        }catch(Exception e){
            
        }finally{
            return arraylist;
        }
    }
    public ArrayList<Manutencao> getArrayListManutencaoByIdVeiculoAndIntervaloDatas(int id_veiculo, long dataInicio, long dataFim){
        try{
            
            dataInicio = aux.dataMilisMin(dataInicio);
            dataFim = aux.dataMilisMax(dataFim);
            
            arraylist = mdao.PesquisarTodosByIDVeiculoAndIntervaloDatamilis(id_veiculo, dataInicio, dataFim);
        }catch(Exception e){
            aux.showMessageInformacao(e.getMessage(), "GetArrayListManutençaoByIdVeiculoandIntervaloDatas");
        }finally{
            return arraylist; 
        }
    }
    public boolean Inserir(Manutencao manutencao){
        if(validarCampos(manutencao)){
            if(manutencao.getId()==0){
                MostrarMensagem("Inserção ", mdao.Inserir(manutencao));
            }else{
                MostrarMensagem("Alteração ", mdao.Alterar(manutencao));
            }
        
        }
        return false;
    }
    public boolean Inserir(String id, int id_veiculo,int idUsuario, String km, String valor, String datamilis, String oficina, String servico){
        if(validarCampos(id, id_veiculo, km, valor, datamilis, oficina, servico)){
            
            Manutencao manutencao = montarObjetoManutencao(id, id_veiculo, km, valor, datamilis, oficina, servico);
            
            if(manutencao.getKm() > vkm.getUltimoKmByIDVeiculo(id_veiculo)){
                if(JOptionPane.showConfirmDialog(null, "O valor do KM atual do veiculo é menor que o valor no momento da manutenção.\n Deseja atualizar o KM atual do veiculo para '"+manutencao.getKm()+"' ?", "Validação de Campos", JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
                    if(!vkm.addVeiculoKM(id_veiculo, idUsuario, datamilis, km)){
                        return false;
                    }
                }else{
                    return false;
                }
            }
            
            if(manutencao.getId()==0){
                MostrarMensagem("Inserção ", mdao.Inserir(manutencao));
            }else{
                MostrarMensagem("Alteração ", mdao.Alterar(manutencao));
            }
            return true;
        }
        return false;
    }
    private Manutencao montarObjetoManutencao(String id, int id_veiculo, String km, String valor, String datamilis, String oficina, String servico){
        Manutencao manutencao = new Manutencao();
        try{
            manutencao.setId(Integer.parseInt(id));
            manutencao.setId_veiculo(id_veiculo);
            manutencao.setKm(Integer.parseInt(km));
            manutencao.setValor(Float.parseFloat(valor));
            manutencao.setDatamilis(aux.dataStringLong(datamilis));
            manutencao.setOficina(oficina);
            manutencao.setServico(servico);
            
        }catch(Exception e){
            aux.showMessageInformacao(e.getMessage(), "montarObjetoManutencao");
            manutencao = new Manutencao();
        }finally{
            return manutencao;
        }
    }
    private boolean validarCampos(String id, int id_veiculo, String km, String valor, String datamilis, String oficina, String servico){
        Manutencao manutencao = new Manutencao();
        try{
            if(km.trim().equals("")||valor.trim().equals("")||datamilis.trim().equals("")||oficina.trim().equals("")||servico.trim().equals("")){
                aux.showMessageWarning("Existem valores inválidos. Verifique os campos", "Validação de valores");
                return false;
            }
            return true;
        }catch(Exception e){
            return false;
        }
    }
    
    private boolean validarCampos(Manutencao manutencao){
        return true;
    }
    private void MostrarMensagem(String mensagem, boolean status){
        String situacao = status ? " com sucesso!": " falhou";
        aux.showMessageInformacao(mensagem + situacao, "Manutenção");
    }
    public float custoKmParcial(int idVeiculo){
        float custo=0;
        arraylist = getArrayListManutencaoTodosByIdVeiculo(idVeiculo);
        if(arraylist.size()>0){
            int i = arraylist.size()-1;
            int kmAtual = new VeiculoKMControl().getUltimoKmByIDVeiculo(idVeiculo);
            int diffkm = kmAtual - arraylist.get(i).getKm();
            diffkm = diffkm<=0? 1: diffkm;
            float valor = arraylist.get(i).getValor();
            valor = (valor<=0)? 1: valor;
            custo = valor / diffkm;
        }
        return custo;
    }
    
    /*
    try{
            
        }catch(Exception e){
            aux.showMessageInformacao(e.getMessage(), "Titulo");
        }finally{
            
        }
    */
}