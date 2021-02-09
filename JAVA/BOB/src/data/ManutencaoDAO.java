package data;

import java.util.ArrayList;
import model.Manutencao;

public class ManutencaoDAO extends  ConectionDAO{

    private static final String TB_TABELA = "tb_manutencao";
    private static final String ID = "id";
    private static final String ID_VEICULO = "id_veiculo";
    private static final String KM = "km";
    private static final String VALOR = "valor";
    private static final String DATAMILIS = "datamilis";
    private static final String OFICINA = "oficina";
    private static final String SERVICO = "servico";
    
    public ManutencaoDAO(){
        connection = getConexao();
    }
    public ManutencaoDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(Manutencao manutencao){
        AbrirConexao();
                    //INSERT INTO `tb_manutencao` (`id`, `id_veiculo`, `km`, `valor`, `datamilis`, `oficina`, `servico`) VALUES (NULL, '1', '100', '150', '0', 'oficina', 'servico');
        String sql = "INSERT INTO "+TB_TABELA+" ("+ID_VEICULO+", "+KM+", "+VALOR+", "+DATAMILIS+", "+OFICINA+", "+SERVICO+") VALUES (?, ?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, manutencao.getId_veiculo());
            stmt.setInt(2, manutencao.getKm());
            stmt.setFloat(3, manutencao.getValor());
            stmt.setLong(4, manutencao.getDatamilis());
            stmt.setString(5, manutencao.getOficina());
            stmt.setString(6, manutencao.getServico());
            
            
            stmt.execute();
            stmt.close();
            
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "ManutencaoDAO.Inserir");
             return false;
        }finally{
            FecharConexao();
            return true;
        }
    }

    public boolean Alterar(Manutencao manutencao){
        AbrirConexao();
        //UPDATE `tb_manutencao` SET `id`=[value-1],`id_veiculo`=[value-2],`km`=[value-3],`valor`=[value-4],`datamilis`=[value-5],`oficina`=[value-6],`servico`=[value-7] WHERE 1
        String sql = "UPDATE "+TB_TABELA+" SET "+ID_VEICULO+" = ?, "+KM+" = ?, "+VALOR+" = ?, "+DATAMILIS+" = ?, "+OFICINA+" = ?, "+SERVICO+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, manutencao.getId_veiculo());
            stmt.setInt(2, manutencao.getKm());
            stmt.setFloat(3, manutencao.getValor());
            stmt.setLong(4, manutencao.getDatamilis());
            stmt.setString(5, manutencao.getOficina());
            stmt.setString(6, manutencao.getServico());
            stmt.setInt(7, manutencao.getId());
            
            stmt.execute();
            

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ManutencaoDAO.Alterar");
            
        }finally{
            FecharConexao();
            return true;
        }
    }

    public void Excluir(Manutencao manutencao){
        AbrirConexao();
        String sql = "DELETE FROM "+TB_TABELA+" WHERE "+ID+" = " + manutencao.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ManutencaoDAO.Excluir");
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<Manutencao> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_TABELA; //Script sql para Consultar
        ArrayList<Manutencao> arraylist = new ArrayList<Manutencao>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Manutencao manutencao;
            while (resultSet.next()){
                manutencao = new Manutencao();
                manutencao.setId(resultSet.getInt(ID));
                manutencao.setId_veiculo(resultSet.getInt(ID_VEICULO));
                manutencao.setKm(resultSet.getInt(KM));
                manutencao.setValor(resultSet.getFloat(VALOR));
                manutencao.setDatamilis(resultSet.getLong(DATAMILIS));
                manutencao.setOficina(resultSet.getString(OFICINA));
                manutencao.setServico(resultSet.getString(SERVICO));
                arraylist.add(manutencao);
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ManutencaoDAO.PesquisarTodos");
            
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public Manutencao PesquisarById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_TABELA+" WHERE "+ID+" = "+id;
        Manutencao manutencao = new Manutencao();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                manutencao.setId(resultSet.getInt(ID));
                manutencao.setId_veiculo(resultSet.getInt(ID_VEICULO));
                manutencao.setKm(resultSet.getInt(KM));
                manutencao.setValor(resultSet.getFloat(VALOR));
                manutencao.setDatamilis(resultSet.getLong(DATAMILIS));
                manutencao.setOficina(resultSet.getString(OFICINA));
                manutencao.setServico(resultSet.getString(SERVICO));
                break;
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ManutencaoDAO.PesquisarById");
        }finally{
            FecharConexao();
            return manutencao;
        }
    }
    
    public ArrayList<Manutencao> PesquisarTodosByIDVeiculo(int id_veiculo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_TABELA+" WHERE "+ID_VEICULO+" = "+id_veiculo+ " ORDER BY " + DATAMILIS + " ASC"; //Script sql para Consultar
        ArrayList<Manutencao> arraylist = new ArrayList<Manutencao>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Manutencao manutencao;
            while (resultSet.next()){
                manutencao = new Manutencao();
                manutencao.setId(resultSet.getInt(ID));
                manutencao.setId_veiculo(resultSet.getInt(ID_VEICULO));
                manutencao.setKm(resultSet.getInt(KM));
                manutencao.setValor(resultSet.getFloat(VALOR));
                manutencao.setDatamilis(resultSet.getLong(DATAMILIS));
                manutencao.setOficina(resultSet.getString(OFICINA));
                manutencao.setServico(resultSet.getString(SERVICO));
                arraylist.add(manutencao);
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ManutencaoDAO.PesquisarTodosByIdVeiculo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<Manutencao> PesquisarTodosByIDVeiculoAndIntervaloDatamilis(int id_veiculo, long dataInicio, long dataFim){
                    //SELECT * FROM `tb_manutencao` WHERE datamilis > 0 AND datamilis < 10 AND id_veiculo = 1
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_TABELA+" WHERE "+ID_VEICULO+" = "+id_veiculo +
                                                         " AND "+ DATAMILIS + " >= "+ dataInicio +
                                                         " AND "+ DATAMILIS + " <= "+ dataFim +
                                                         " ORDER BY " + DATAMILIS + " ASC";//Script sql para Consultar
        ArrayList<Manutencao> arraylist = new ArrayList<Manutencao>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Manutencao manutencao;
            while (resultSet.next()){
                manutencao = new Manutencao();
                manutencao.setId(resultSet.getInt(ID));
                manutencao.setId_veiculo(resultSet.getInt(ID_VEICULO));
                manutencao.setKm(resultSet.getInt(KM));
                manutencao.setValor(resultSet.getFloat(VALOR));
                manutencao.setDatamilis(resultSet.getLong(DATAMILIS));
                manutencao.setOficina(resultSet.getString(OFICINA));
                manutencao.setServico(resultSet.getString(SERVICO));
                arraylist.add(manutencao);
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ManutencaoDAO.PesquisarTodosByIDVeiculoAndInverladoDatamilis");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    
}
