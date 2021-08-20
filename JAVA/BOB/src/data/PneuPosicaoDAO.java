package data;

import java.util.ArrayList;
import model.PneuPosicao;

public class PneuPosicaoDAO extends  ConectionDAO{

    private static final String TABELA = "tb_pneu_posicao";
    private static final String POSICAO = "posicao";
    private static final String IDVEICULO = "idveiculo";
    private static final String IDPNEU = "idpneu";
    private static final String DATAMILIS = "datamilis";
    private static final String KM = "km";
    
    public PneuPosicaoDAO(){
        connection = getConexao();
    }
    public PneuPosicaoDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(PneuPosicao vPneu){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_veiculo_pneu` (`idveiculo`, `idpneu`, `posicao`, `datamilis`) VALUES ('5', '1', '0', '0');
        String sql = "INSERT INTO "+TABELA+" ("+IDVEICULO+", "+IDPNEU+", "+POSICAO+", "+DATAMILIS+", "+KM+") VALUES (?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
    
            stmt.setInt(1, vPneu.getIdVeiculo());
            stmt.setInt(2, vPneu.getIdPneu());
            stmt.setInt(3, vPneu.getPosicao());
            stmt.setLong(4, vPneu.getDatamilis());
            stmt.setInt(5, vPneu.getKm());
            
            stmt.execute();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "PneuPosicaoDAO.Inserir");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public boolean Alterar(PneuPosicao vPneu){
        AbrirConexao();
        boolean retorno = false;
        String sql = "UPDATE "+TABELA+" SET "+IDVEICULO+" = ?, "+IDPNEU+" = ?, "+POSICAO+" = ?, "+DATAMILIS+" = ?, "+KM+" = ? WHERE "+IDVEICULO+" = ? AND "+ POSICAO +" = ?"; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setInt(1, vPneu.getIdVeiculo());
            stmt.setInt(2, vPneu.getIdPneu());
            stmt.setInt(3, vPneu.getPosicao());
            stmt.setLong(4, vPneu.getDatamilis());
            stmt.setInt(5, vPneu.getKm());
            stmt.setInt(6, vPneu.getIdVeiculo());
            stmt.setInt(7, vPneu.getPosicao());
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuPosicaoDAO.Alterar");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public boolean Excluir(PneuPosicao vPneu){
        boolean retorno = false;
        AbrirConexao();
        String sql = "DELETE FROM "+TABELA+" WHERE " + IDVEICULO+" = "+ vPneu.getIdVeiculo()+ " AND "
                                                        +POSICAO+" = "+ vPneu.getPosicao() + " OR "
                                                        +IDPNEU+" = "+vPneu.getIdPneu(); //Script sql para Excluir
                
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuPosicaoDAO.Excluir");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public ArrayList<PneuPosicao> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA; //Script sql para Consultar
        ArrayList<PneuPosicao> arraylist = new ArrayList<PneuPosicao>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            PneuPosicao vPneu ;
            while (resultSet.next()){
                vPneu = new PneuPosicao();
                vPneu.setPosicao(resultSet.getInt(POSICAO));
                vPneu.setIdVeiculo(resultSet.getInt(IDVEICULO));
                vPneu.setIdPneu(resultSet.getInt(IDPNEU));
                vPneu.setDatamilis(resultSet.getLong(DATAMILIS));
                vPneu.setKm(resultSet.getInt(KM));
                arraylist.add(vPneu);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuPosicaoDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<PneuPosicao> PesquisarByIdVeiculo(int idveiculo){
        AbrirConexao();
        ArrayList<PneuPosicao> arraylist;
        String sql = "SELECT * FROM "+TABELA+" WHERE "+IDVEICULO+" = "+idveiculo;
        arraylist = new ArrayList<PneuPosicao>();
        PneuPosicao vPneu;
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                vPneu = new PneuPosicao();
                vPneu.setPosicao(resultSet.getInt(POSICAO));
                vPneu.setIdVeiculo(resultSet.getInt(IDVEICULO));
                vPneu.setIdPneu(resultSet.getInt(IDPNEU));
                vPneu.setDatamilis(resultSet.getLong(DATAMILIS));
                vPneu.setKm(resultSet.getInt(KM));
                
                arraylist.add(vPneu);
            }
             
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuPosicaoDAO.PesquisarByIdVeiculo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public PneuPosicao PesquisarByIDVeiculoAndByPosicao(int id_veiculo, int posicao){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+IDVEICULO+" = "+id_veiculo + " AND " 
                                                      +POSICAO +" = " +posicao; //Script sql para Consultar
        PneuPosicao vPneu = new PneuPosicao();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){
                vPneu = new PneuPosicao();
                vPneu.setPosicao(resultSet.getInt(POSICAO));
                vPneu.setIdVeiculo(resultSet.getInt(IDVEICULO));
                vPneu.setIdPneu(resultSet.getInt(IDPNEU));
                vPneu.setDatamilis(resultSet.getLong(DATAMILIS));
                vPneu.setKm(resultSet.getInt(KM));
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuPosicaoDAO.PesquisarByIdVeiculoAndByPosicao");
            vPneu = new PneuPosicao();
        }finally{
            FecharConexao();
            return vPneu;
        }
    }
    public PneuPosicao PesquisarByIDPneu(int idPneu){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+IDPNEU+" = "+idPneu ; //Script sql para Consultar
        PneuPosicao vPneu = new PneuPosicao();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){
                vPneu = new PneuPosicao();
                vPneu.setPosicao(resultSet.getInt(POSICAO));
                vPneu.setIdVeiculo(resultSet.getInt(IDVEICULO));
                vPneu.setIdPneu(resultSet.getInt(IDPNEU));
                vPneu.setDatamilis(resultSet.getLong(DATAMILIS));
                vPneu.setKm(resultSet.getInt(KM));
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuPosicaoDAO.PesquisarByIdVeiculoAndByPosicao");
            vPneu = new PneuPosicao();
        }finally{
            FecharConexao();
            return vPneu;
        }
    }
    
}
