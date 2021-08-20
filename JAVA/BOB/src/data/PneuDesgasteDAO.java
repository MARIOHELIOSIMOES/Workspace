package data;

import java.util.ArrayList;
import model.PneuDesgaste;

public class PneuDesgasteDAO extends  ConectionDAO{

    private static final String TABELA = "tb_pneu_desgaste";
    private static final String KMSAIDA = "kmsaida";
    private static final String IDVEICULO = "idveiculo";
    private static final String IDPNEU = "idpneu";
    private static final String DATAMILIS = "datamilis";
    private static final String KMENTRADA = "kmentrada";
    private static final String VALOR = "valor";
    private static final String ID = "id";
    private static final String POSICAO = "posicao";
    
    
    public PneuDesgasteDAO(){
        connection = getConexao();
    }
    public PneuDesgasteDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(PneuDesgaste pDesgaste){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_pneu_desgaste` (`id`, `idpneu`, `idveiculo`, `kmentrada`, `kmsaida`, `valor`, `datamilis`) VALUES (NULL, '1', '5', '10', '100', '100', '10');
        String sql = "INSERT INTO "+TABELA+" ("+IDPNEU+", "+IDVEICULO+", "+POSICAO+", "+KMENTRADA+", "+KMSAIDA+", "+VALOR+", "+DATAMILIS+") VALUES (?, ?, ?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
    
            stmt.setInt(1, pDesgaste.getIdPneu());
            stmt.setInt(2, pDesgaste.getIdVeiculo());
            stmt.setInt(3, pDesgaste.getPosicao());
            stmt.setInt(4, pDesgaste.getKmEntrada());
            stmt.setInt(5, pDesgaste.getKmSaida());
            stmt.setFloat(6, pDesgaste.getValor());
            stmt.setLong(7, pDesgaste.getDatamilis());
            
            stmt.execute();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "PneuDesgasteDAO.Inserir");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public boolean Alterar(PneuDesgaste pDesgaste){
        AbrirConexao();
        boolean retorno = false;
        String sql = "UPDATE "+TABELA+" SET "+IDPNEU+" = ?, "+IDVEICULO+" = ?, "+POSICAO+" = ?, "+KMENTRADA+" = ?, "+KMSAIDA+" = ?, "+VALOR+" = ?, "+DATAMILIS+" = ? WHERE "+ID+" = ?"; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
             stmt.setInt(1, pDesgaste.getIdPneu());
            stmt.setInt(2, pDesgaste.getIdVeiculo());
            stmt.setInt(3, pDesgaste.getPosicao());
            stmt.setInt(4, pDesgaste.getKmEntrada());
            stmt.setInt(5, pDesgaste.getKmSaida());
            stmt.setFloat(6, pDesgaste.getValor());
            stmt.setLong(7, pDesgaste.getDatamilis());
            stmt.setInt(8, pDesgaste.getId());
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDesgasteDAO.Alterar");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public void Excluir(PneuDesgaste pneuDesgaste){
        AbrirConexao();
        String sql = "DELETE FROM "+TABELA+" WHERE " + ID+" = "+ pneuDesgaste.getId(); //Script sql para Excluir
                
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDesgasteDAO.Excluir");
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<PneuDesgaste> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA; //Script sql para Consultar
        ArrayList<PneuDesgaste> arraylist = new ArrayList<PneuDesgaste>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            PneuDesgaste pneuDesgaste ;
            while (resultSet.next()){
                pneuDesgaste = new PneuDesgaste();
                pneuDesgaste.setId(resultSet.getInt(KMSAIDA));
                pneuDesgaste.setIdPneu(resultSet.getInt(IDPNEU));
                pneuDesgaste.setIdVeiculo(resultSet.getInt(IDVEICULO));
                pneuDesgaste.setPosicao(resultSet.getInt(POSICAO));
                pneuDesgaste.setKmEntrada(resultSet.getInt(KMENTRADA));
                pneuDesgaste.setKmSaida(resultSet.getInt(KMSAIDA));
                pneuDesgaste.setValor(resultSet.getFloat(VALOR));
                pneuDesgaste.setDatamilis(resultSet.getLong(DATAMILIS));
                
                arraylist.add(pneuDesgaste);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDesgasteDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<PneuDesgaste> PesquisarByIdPneu(int idpneu){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+IDPNEU+" = "+idpneu;
        ArrayList<PneuDesgaste> arraylist = new ArrayList<PneuDesgaste>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            PneuDesgaste pneuDesgaste ;
            while (resultSet.next()){
                pneuDesgaste = new PneuDesgaste();
                pneuDesgaste.setId(resultSet.getInt(ID));
                pneuDesgaste.setIdPneu(resultSet.getInt(IDPNEU));
                pneuDesgaste.setIdVeiculo(resultSet.getInt(IDVEICULO));
                pneuDesgaste.setPosicao(resultSet.getInt(POSICAO));
                pneuDesgaste.setKmEntrada(resultSet.getInt(KMENTRADA));
                pneuDesgaste.setKmSaida(resultSet.getInt(KMSAIDA));
                pneuDesgaste.setValor(resultSet.getFloat(VALOR));
                pneuDesgaste.setDatamilis(resultSet.getLong(DATAMILIS));
                
                arraylist.add(pneuDesgaste);
            }
             
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDesgasteDAO.PesquisarByIdVeiculo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }

    public ArrayList<PneuDesgaste> pesquisarByIdVeiculoAndPosicao(int idVeiculo, int posicao) {
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+IDVEICULO+" = ? AND "+POSICAO +" = ?";
        ArrayList<PneuDesgaste> arraylist = new ArrayList<PneuDesgaste>();
        
        try {
            stmt = connection.prepareStatement(sql);
    
            stmt.setInt(1, idVeiculo);
            stmt.setInt(2, posicao);
            
            resultSet = stmt.executeQuery();
            
            PneuDesgaste pneuDesgaste ;
            while (resultSet.next()){
                pneuDesgaste = new PneuDesgaste();
                pneuDesgaste.setId(resultSet.getInt(ID));
                pneuDesgaste.setIdPneu(resultSet.getInt(IDPNEU));
                pneuDesgaste.setIdVeiculo(resultSet.getInt(IDVEICULO));
                pneuDesgaste.setPosicao(resultSet.getInt(POSICAO));
                pneuDesgaste.setKmEntrada(resultSet.getInt(KMENTRADA));
                pneuDesgaste.setKmSaida(resultSet.getInt(KMSAIDA));
                pneuDesgaste.setValor(resultSet.getFloat(VALOR));
                pneuDesgaste.setDatamilis(resultSet.getLong(DATAMILIS));
                
                arraylist.add(pneuDesgaste);
            }
             
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDesgasteDAO.pesquisarByIdVeiculoAndPosicao");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    
}
