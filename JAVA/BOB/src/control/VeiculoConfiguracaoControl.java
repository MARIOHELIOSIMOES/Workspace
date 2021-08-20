package control;

import data.VeiculoConfiguracaoDAO;
import model.Auxiliar;
import model.VeiculoConfiguracao;

public class VeiculoConfiguracaoControl {

    Auxiliar aux;
    VeiculoConfiguracaoDAO vcd;
    
    public VeiculoConfiguracaoControl(){
        inicializar();
    }
    public boolean Inserir(VeiculoConfiguracao vc, int idveiculo){
        if(vcd.Inserir(vc, idveiculo)){
            aux.RegistrarLog("Veiculo Configuração inserida com sucesso", "VeiculoConfiguraçãoControl");
            return true;
        }else{
            aux.RegistrarLog("Veiculo Configuração falhou", "VeiculoConfiguraçãoControl");
            return false;
        }
    }
    public VeiculoConfiguracao getConfiguracaoByIdVeiculo(int idveiculo){
        return vcd.PesquisarByIDVeiculo(idveiculo);
    }
    
    private void inicializar() {
        aux = new Auxiliar();
        vcd = new VeiculoConfiguracaoDAO();
    }

    public int[] getPosicoesTracaoByIdVeiculo(int idveiculo) {
        try{
            return getConfiguracaoByIdVeiculo(idveiculo).getPosicoesTracao();
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoConfiguraçaoControl.getPosicoesTracaoByIdVeiculo");
            return new int[]{};
        }
    }
}
