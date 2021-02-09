package data;

import java.util.ArrayList;
import model.VeiculoPneu;

public class VeiculoPneuDAO extends  ConectionDAO{

    private static final String TABELA = "tb_veiculo_pneu";
    private static final String POSICAO = "posicao";
    private static final String IDVEICULO = "idveiculo";
    private static final String IDPNEU = "idpneu";
    private static final String DATAMILIS = "datamilis";
    
    public VeiculoPneuDAO(){
        connection = getConexao();
    }
    public VeiculoPneuDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(VeiculoPneu vPneu){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_veiculo_pneu` (`idveiculo`, `idpneu`, `posicao`, `datamilis`) VALUES ('5', '1', '0', '0');
        String sql = "INSERT INTO "+TABELA+" ("+IDVEICULO+", "+IDPNEU+", "+POSICAO+", "+DATAMILIS+") VALUES (?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
    
            stmt.setInt(1, vPneu.getIdVeiculo());
            stmt.setInt(2, vPneu.getIdPneu());
            stmt.setInt(3, vPneu.getPosicao());
            stmt.setLong(4, vPneu.getDatamilis());
            
            stmt.execute();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "VeiculoPneu.Inserir");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public boolean Alterar(VeiculoPneu vPneu){
        AbrirConexao();
        boolean retorno = false;
        String sql = "UPDATE "+TABELA+" SET "+IDVEICULO+" = ?, "+IDPNEU+" = ?, "+POSICAO+" = ?, "+DATAMILIS+" = ? WHERE "+IDVEICULO+" = ? AND "+ POSICAO +" = ?"; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, vPneu.getIdVeiculo());
            stmt.setInt(2, vPneu.getIdPneu());
            stmt.setInt(3, vPneu.getPosicao());
            stmt.setLong(4, vPneu.getDatamilis());
            stmt.setInt(5, vPneu.getIdVeiculo());
            stmt.setInt(6, vPneu.getPosicao());
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneu.Alterar");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public void Excluir(VeiculoPneu vPneu){
        AbrirConexao();
        String sql = "DELETE FROM "+TABELA+" WHERE " + IDVEICULO+" = "+ vPneu.getIdVeiculo()+ ", "
                                                         +IDPNEU+" = "+vPneu.getIdPneu()+", "
                                                        +POSICAO+" = "+ vPneu.getPosicao(); //Script sql para Excluir
                
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneu.Excluir");
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<VeiculoPneu> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA; //Script sql para Consultar
        ArrayList<VeiculoPneu> arraylist = new ArrayList<VeiculoPneu>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            VeiculoPneu vPneu ;
            while (resultSet.next()){
                vPneu = new VeiculoPneu();
                vPneu.setPosicao(resultSet.getInt(POSICAO));
                vPneu.setIdVeiculo(resultSet.getInt(IDVEICULO));
                vPneu.setIdPneu(resultSet.getInt(IDPNEU));
                vPneu.setDatamilis(resultSet.getLong(DATAMILIS));
                arraylist.add(vPneu);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneu.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public VeiculoPneu PesquisarByIdVeiculo(int idveiculo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+IDVEICULO+" = "+idveiculo;
        VeiculoPneu vPneu = new VeiculoPneu();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                vPneu.setPosicao(resultSet.getInt(POSICAO));
                vPneu.setIdVeiculo(resultSet.getInt(IDVEICULO));
                vPneu.setIdPneu(resultSet.getInt(IDPNEU));
                vPneu.setDatamilis(resultSet.getLong(DATAMILIS));
            }
             
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneu.PesquisarByIdVeiculo");
        }finally{
            FecharConexao();
            return vPneu;
        }
    }
    public VeiculoPneu PesquisarByIDVeiculoAndByPosicao(int id_veiculo, int posicao){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+IDVEICULO+" = "+id_veiculo + " AND " 
                                                      +POSICAO +" = " +posicao; //Script sql para Consultar
        VeiculoPneu vPneu = new VeiculoPneu();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){
                vPneu = new VeiculoPneu();
                vPneu.setPosicao(resultSet.getInt(POSICAO));
                vPneu.setIdVeiculo(resultSet.getInt(IDVEICULO));
                vPneu.setIdPneu(resultSet.getInt(IDPNEU));
                vPneu.setDatamilis(resultSet.getLong(DATAMILIS));
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "VeiculoPneu.PesquisarByIdVeiculoAndByPosicao");
            vPneu = new VeiculoPneu();
        }finally{
            FecharConexao();
            return vPneu;
        }
    }
        
}
