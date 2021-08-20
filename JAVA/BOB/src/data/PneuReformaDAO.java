package data;

import java.util.ArrayList;
import model.PneuReforma;

public class PneuReformaDAO extends  ConectionDAO{

    private static final String TABELA = "tb_pneu_reforma";
    private static final String ID = "id";
    private static final String ID_PNEU = "idpneu";
    private static final String OFICINA = "oficina";
    private static final String VALOR = "valor";
    private static final String KM = "km";
    private static final String INFO = "info";
    private static final String DATAMILIS = "datamilis";
    
    public PneuReformaDAO(){
        connection = getConexao();
    }
    public PneuReformaDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(PneuReforma pneuReforma){
        AbrirConexao();
           //INSERT INTO `tb_pneu_reforma` (`id`, `idpneu`, `oficina`, `info`, `km`, `valor`, `datamilis`) VALUES (NULL, '1', 'oficina', 'informação', '10', '230', '0');
        String sql = "INSERT INTO "+TABELA+" ("+ID_PNEU+", "+OFICINA+", "+INFO+", "+KM+", "+VALOR+", "+DATAMILIS+") VALUES (?, ?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pneuReforma.getIdPneu());
            stmt.setString(2, pneuReforma.getOficina());
            stmt.setString(3, pneuReforma.getInfo());
            stmt.setInt(4, pneuReforma.getKm());
            stmt.setFloat(5, pneuReforma.getValor());
            stmt.setLong(6, pneuReforma.getDatamilis());
            
            stmt.execute();
            stmt.close();
            
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "PneuReformaDAO.Inserir");
             return false;
        }finally{
            FecharConexao();
            return true;
        }
    }

    public boolean Alterar(PneuReforma pneuReforma){
        AbrirConexao();
        String sql = "UPDATE "+TABELA+" SET "+ID_PNEU+" = ?, "+OFICINA+" = ?, "+INFO+" = ?, "+KM+" = ?, "+VALOR+" = ?, "+DATAMILIS+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
             stmt.setInt(1, pneuReforma.getIdPneu());
            stmt.setString(2, pneuReforma.getOficina());
            stmt.setString(3, pneuReforma.getInfo());
            stmt.setInt(4, pneuReforma.getKm());
            stmt.setFloat(5, pneuReforma.getValor());
            stmt.setLong(6, pneuReforma.getDatamilis());
            stmt.setInt(7, pneuReforma.getId());
            stmt.execute();
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuReformaDAO.Alterar");
            
        }finally{
            FecharConexao();
            return true;
        }
    }

    public void Excluir(PneuReforma item){
        AbrirConexao();
        String sql = "DELETE FROM "+TABELA+" WHERE "+ID+" = " + item.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuReformaDAO.Excluir");
        }finally{
            FecharConexao();
        }
    }
    public void Excluir(int _id){
        AbrirConexao();
        String sql = "DELETE FROM "+TABELA+" WHERE "+ID+" = " + _id; //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuReformaDAO.Excluir");
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<PneuReforma> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA; //Script sql para Consultar
        ArrayList<PneuReforma> arraylist = new ArrayList<PneuReforma>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            PneuReforma pneuReforma;
            while (resultSet.next()){
                pneuReforma = new PneuReforma();
                pneuReforma.setId(resultSet.getInt(ID));
                pneuReforma.setIdPneu(resultSet.getInt(ID_PNEU));
                pneuReforma.setOficina(resultSet.getString(OFICINA));
                pneuReforma.setInfo(resultSet.getString(INFO));
                pneuReforma.setKm(resultSet.getInt(KM));
                pneuReforma.setValor(resultSet.getFloat(VALOR));
                pneuReforma.setDatamilis(resultSet.getLong(DATAMILIS));
               
                arraylist.add(pneuReforma);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuReformaDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public PneuReforma PesquisarById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+ID+" = "+id;
        PneuReforma pneuReforma = new PneuReforma();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                pneuReforma.setId(resultSet.getInt(ID));
                pneuReforma.setIdPneu(resultSet.getInt(ID_PNEU));
                pneuReforma.setOficina(resultSet.getString(OFICINA));
                pneuReforma.setInfo(resultSet.getString(INFO));
                pneuReforma.setKm(resultSet.getInt(KM));
                pneuReforma.setValor(resultSet.getFloat(VALOR));
                pneuReforma.setDatamilis(resultSet.getLong(DATAMILIS));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuReformaDAO.PesquisarById");
        }finally{
            FecharConexao();
            return pneuReforma;
        }
    }
    
    public ArrayList<PneuReforma> PesquisarByIDPneu(int idpneu){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+ID_PNEU+" = "+idpneu; //Script sql para Consultar
        ArrayList<PneuReforma> arraylist = new ArrayList<PneuReforma>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            PneuReforma pneuReforma;
            while (resultSet.next()){
                pneuReforma = new PneuReforma();
                pneuReforma.setId(resultSet.getInt(ID));
                pneuReforma.setIdPneu(resultSet.getInt(ID_PNEU));
                pneuReforma.setOficina(resultSet.getString(OFICINA));
                pneuReforma.setInfo(resultSet.getString(INFO));
                pneuReforma.setKm(resultSet.getInt(KM));
                pneuReforma.setValor(resultSet.getFloat(VALOR));
                pneuReforma.setDatamilis(resultSet.getLong(DATAMILIS));
                arraylist.add(pneuReforma);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuReformaDAO.PesquisarByIDs");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
       
}
