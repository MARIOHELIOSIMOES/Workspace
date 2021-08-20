package data;

import java.util.ArrayList;
import model.VeiculoConfiguracao;
import java.sql.ResultSet;
import java.sql.Statement;
public class VeiculoConfiguracaoDAO extends  ConectionDAO{

    private static final String NOMECLASSE = "VeiculoConfiguracaoDAO", TB_VEICULO_CONFIGURACAO = "tb_veiculo_configuracao", TB_VEICULO_CONFIGURACAO_TRACAO = "tb_veiculo_configuracao_tracao";
    private static final String ID_VEICULO = "idveiculo", CONFIGURACAO = "configuracao", NRODAS = "nrodas", NEIXOS = "neixos", DESCRICAO = "descricao";
    private static final String POSICAO = "posicao";
    public VeiculoConfiguracaoDAO(){
       connection = getConexao();
    }
    public VeiculoConfiguracaoDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(VeiculoConfiguracao vc, int idVeiculo){
        AbrirConexao();
        boolean retorno = false;
                
                //        DELETE FROM `tb_veiculo_configuracao` WHERE idveiculo = 5;
        String sql="INSERT INTO "+TB_VEICULO_CONFIGURACAO+" ("+ID_VEICULO+", "+CONFIGURACAO+", "+NRODAS+", "+NEIXOS+", "+DESCRICAO+") VALUES (?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            Excluir(idVeiculo);
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idVeiculo);
            stmt.setInt(2, vc.getConfiguracao());
            stmt.setInt(3, vc.getnRodas());
            stmt.setInt(4, vc.getnEixos());
            stmt.setString(5, vc.getDescricao());
            
            stmt.execute();
            retorno = InserirPosicoes(vc.getPosicoesTracao(), idVeiculo);
            
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), NOMECLASSE+".Inserir");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    private boolean InserirPosicoes(int[] posicoes, int idveiculo){
        boolean retorno = false;
        try {
            ExcluirPosicaoByIdVeiculo(idveiculo);
            //INSERT INTO `tb_veiculo_configuracao_tracao` (`idveiculo`, `posicao`) VALUES ('2', '0');
            String sql = "INSERT INTO "+TB_VEICULO_CONFIGURACAO_TRACAO+" ("+ID_VEICULO+", "+POSICAO+") VALUES (?, ?)"; //Script sql para inserção
            if(posicoes.length<=0){
                return true;
            }
            if(posicoes.length>1){
                for(int p = posicoes.length-1; p > 0 ; p--){
                    sql += ", (? , ?)";
                }
            }
            stmt = connection.prepareStatement(sql);
            int i = 1;
            for(int p: posicoes){
                stmt.setInt(i, idveiculo);
                i++;
                stmt.setInt(i, p);
                i++;
            }
            stmt.execute();
            return true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), NOMECLASSE+".InserirPosicoes");
             return false;
        }
    }
    private void ExcluirPosicaoByIdVeiculo(int idVeiculo){
        //boolean retorno = false;
        String sql = "DELETE FROM "+TB_VEICULO_CONFIGURACAO_TRACAO+" WHERE "+ID_VEICULO+" = " + idVeiculo; //Script sql para Excluir
        try {
            Statement statementEi = connection.createStatement();
            statementEi.execute(sql);
            //return true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), NOMECLASSE +".ExcluirPosicaoByIdVeiculo");
            //return false;
        }
    }
    private void Excluir(int idveiculo){
        String sql = "DELETE FROM "+TB_VEICULO_CONFIGURACAO+" WHERE "+ID_VEICULO+" = " + idveiculo; //Script sql para Excluir
        try {
            Statement statementE = connection.createStatement();
            ExcluirPosicaoByIdVeiculo(idveiculo);
            //statement = connection.createStatement();
            statementE.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), NOMECLASSE+".Excluir");
        }
    }
    public VeiculoConfiguracao PesquisarByIDVeiculo(int idVeiculo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_VEICULO_CONFIGURACAO+" WHERE "+ID_VEICULO+" = "+idVeiculo; //Script sql para Consultar
        VeiculoConfiguracao vc  = new VeiculoConfiguracao();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){
                vc = new VeiculoConfiguracao();
                vc.setConfiguracao(resultSet.getInt(CONFIGURACAO));
                vc.setnRodas(resultSet.getInt(NRODAS));
                vc.setnEixos(resultSet.getInt(NEIXOS));
                vc.setDescricao(resultSet.getString(DESCRICAO));
                vc.setPosicoesTracao(getRodasTracaoByIdVeiculo(idVeiculo));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), NOMECLASSE+".PesquisarByIdVeiculo");
            vc = new VeiculoConfiguracao();
        }finally{
            FecharConexao();
            return vc;
        }
        
    }
    private int[] getRodasTracaoByIdVeiculo(int idVeiculo) {
        String sql = "SELECT * FROM "+TB_VEICULO_CONFIGURACAO_TRACAO+" WHERE "+ID_VEICULO+" = "+idVeiculo;
        int [] rodasTracao;
        ArrayList<Integer> array = new ArrayList<>();
        try {
            Statement statementP;
            ResultSet resultSetP;
    
            statementP = connection.createStatement();
            resultSetP = statementP.executeQuery(sql);
            
            while (resultSetP.next()){
                array.add(resultSetP.getInt(POSICAO));
            }
            rodasTracao = new int[array.size()];
            int i = 0;
            for(int p: array){
                rodasTracao[i]=p;
                i++;
            }
            return rodasTracao;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), NOMECLASSE+"getRodasTracaoByIdVeiculo");
            rodasTracao = new int[0];
            return rodasTracao;
        }
    }
        
}
