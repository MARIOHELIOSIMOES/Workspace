package data;

import java.util.ArrayList;
import model.Aviso;

public class AvisoDAO extends  ConectionDAO{

    private static final String TABELA = "tb_aviso";
    private static final String ID = "id";
    private static final String ID_VEICULO = "idveiculo";
    private static final String TITULO = "titulo";
    private static final String MENSAGEM = "mensagem";
    private static final String ATIVO = "ativo";
    private static final String DATAMILIS = "datamilis";
    
    public AvisoDAO(){
        connection = getConexao();
    }
    public AvisoDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(Aviso aviso){
        AbrirConexao();
                    //INSERT INTO `tb_aviso` (`id`, `idveiculo`, `titulo`, `mensagem`, `datamilis`, `ativo`) VALUES (NULL, '5', 'Manutenção no Motor', 'Vazamento de óleo no cabeçote do motor', '0', '1');

        String sql = "INSERT INTO "+TABELA+" ("+ID_VEICULO+", "+TITULO+", "+MENSAGEM+", "+DATAMILIS+", "+ATIVO+") VALUES (?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aviso.getIdveiculo());
            stmt.setString(2, aviso.getTitulo());
            stmt.setString(3, aviso.getMensagem());
            stmt.setLong(4, aviso.getDatamilis());
            stmt.setBoolean(5, aviso.isAtivo());
            
            stmt.execute();
            stmt.close();
            
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "AvisoDAO.Inserir");
             return false;
        }finally{
            FecharConexao();
            return true;
        }
    }

    public boolean Alterar(Aviso aviso){
        AbrirConexao();
        String sql = "UPDATE "+TABELA+" SET "+ID_VEICULO+" = ?, "+TITULO+" = ?, "+MENSAGEM+" = ?, "+DATAMILIS+" = ?, "+ATIVO+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aviso.getIdveiculo());
            stmt.setString(2, aviso.getTitulo());
            stmt.setString(3, aviso.getMensagem());
            stmt.setLong(4, aviso.getDatamilis());
            stmt.setBoolean(5, aviso.isAtivo());
            stmt.setInt(6, aviso.getId());
            stmt.execute();
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoDAO.Alterar");
            
        }finally{
            FecharConexao();
            return true;
        }
    }

    public boolean Excluir(Aviso aviso){
        AbrirConexao();
        boolean retorno = false;
        String sql = "DELETE FROM "+TABELA+" WHERE "+ID+" = " + aviso.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoDAO.Excluir");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean ExcluirByIdVeiculoAndTitulo(int idveiculo, String titulo){
        AbrirConexao();
        boolean retorno = false;
        //DELETE FROM tb_aviso WHERE idveiculo = 5  AND titulo IN ('Óleo Cambio', 'Óleo Diferencial')
        String sql = "DELETE FROM "+TABELA+" WHERE "+ID_VEICULO+" = " + idveiculo + " AND " + TITULO +" IN ( "+ titulo+" )" ; //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoDAO.ExcluirByIdVeiculoAndTitulo");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public Aviso PesquisarById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA+" WHERE "+ID+" = "+id;
        Aviso aviso = new Aviso();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            
            while (resultSet.next()){
                aviso = new Aviso();
                aviso.setId(resultSet.getInt(ID));
                aviso.setAtivo(resultSet.getBoolean(ATIVO));
                aviso.setIdveiculo(resultSet.getInt(ID_VEICULO));
                aviso.setTitulo(resultSet.getString(TITULO));
                aviso.setMensagem(resultSet.getString(MENSAGEM));
                aviso.setDatamilis(resultSet.getLong(DATAMILIS));
           }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoDAO.PesquisarById");
            aviso = new Aviso();
        }finally{
            FecharConexao();
            return aviso;
        }
    }

    public ArrayList<Aviso> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA; //Script sql para Consultar
        ArrayList<Aviso> arraylist = new ArrayList<Aviso>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Aviso aviso;
            while (resultSet.next()){
                aviso = new Aviso();
                aviso.setId(resultSet.getInt(ID));
                aviso.setAtivo(resultSet.getBoolean(ATIVO));
                aviso.setIdveiculo(resultSet.getInt(ID_VEICULO));
                aviso.setTitulo(resultSet.getString(TITULO));
                aviso.setMensagem(resultSet.getString(MENSAGEM));
                aviso.setDatamilis(resultSet.getLong(DATAMILIS));
                arraylist.add(aviso);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    
    public ArrayList<Aviso> getArrayAvisoByIdVeiculo(int idveiculo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA + " WHERE " + ID_VEICULO + " = " + idveiculo; //Script sql para Consultar
        ArrayList<Aviso> arraylist = new ArrayList<Aviso>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Aviso aviso;
            while (resultSet.next()){
                aviso = new Aviso();
                aviso.setId(resultSet.getInt(ID));
                aviso.setAtivo(resultSet.getBoolean(ATIVO));
                aviso.setIdveiculo(resultSet.getInt(ID_VEICULO));
                aviso.setTitulo(resultSet.getString(TITULO));
                aviso.setMensagem(resultSet.getString(MENSAGEM));
                aviso.setDatamilis(resultSet.getLong(DATAMILIS));
                arraylist.add(aviso);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<Aviso> getArrayAvisoByIdVeiculoAndAtivo(int idveiculo, boolean ativo){
        AbrirConexao();
        String sql = "SELECT * FROM "+TABELA + " WHERE " + ID_VEICULO + " = " + idveiculo
                                               + " AND " + ATIVO + " = " + ativo  ; //Script sql para Consultar
        ArrayList<Aviso> arraylist = new ArrayList<Aviso>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Aviso aviso;
            while (resultSet.next()){
                aviso = new Aviso();
                aviso.setId(resultSet.getInt(ID));
                aviso.setAtivo(resultSet.getBoolean(ATIVO));
                aviso.setIdveiculo(resultSet.getInt(ID_VEICULO));
                aviso.setTitulo(resultSet.getString(TITULO));
                aviso.setMensagem(resultSet.getString(MENSAGEM));
                aviso.setDatamilis(resultSet.getLong(DATAMILIS));
                arraylist.add(aviso);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }

    
    //verificar como implementar o resultset para pegar o número direto não buscar pelo ID 
    public int getQtdeAtivosByIdVeiculo(int idveiculo, boolean ativo) {
       //SELECT COUNT(*) FROM tb_aviso WHERE idveiculo = 5 AND ativo = false
        ArrayList<Aviso> arraylist;
        int qtde = 0;
        try {
           arraylist = getArrayAvisoByIdVeiculoAndAtivo(idveiculo, ativo);
           qtde = arraylist.size();
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoDAO.PesquisarTodos");
            qtde = 0;
        }finally{
            return qtde;
        }
    }

    public void InserirIfNotExist(Aviso aviso) {
        AbrirConexao();
                   /*
                    INSERT INTO tb_aviso (idveiculo, titulo, mensagem,  datamilis, ativo)
                    SELECT * FROM (SELECT '1', 'OLEO MOTORes', 'TESTE', '1613761775576', true) AS tmp
                    WHERE NOT EXISTS (
                        SELECT titulo FROM tb_aviso WHERE titulo = 'OLEO MOTORes'
                    ) LIMIT 1;
        */

        String sql = "INSERT INTO "+TABELA+" ("+ID_VEICULO+", "+TITULO+", "+MENSAGEM+", "+DATAMILIS+", "+ATIVO+")"+
                      " SELECT * FROM (SELECT ?, ?, ?, ?, ?) AS tmp"+
                      " WHERE NOT EXISTS ("+
                      "SELECT "+TITULO+" FROM "+TABELA+" WHERE "+TITULO+" = ? " + " AND "+ID_VEICULO+" = ?) LIMIT 1";
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aviso.getIdveiculo());
            stmt.setString(2, aviso.getTitulo());
            stmt.setString(3, aviso.getMensagem());
            stmt.setLong(4, aviso.getDatamilis());
            stmt.setBoolean(5, aviso.isAtivo());
            stmt.setString(6, aviso.getTitulo());
            stmt.setInt(7, aviso.getIdveiculo());
            
            stmt.execute();
            stmt.close();
            
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "AvisoDAO.InserirIfNotExists");
            
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<Aviso> getArrayAvisosAtivo(boolean ativo) {
       AbrirConexao();
        String sql = "SELECT * FROM "+TABELA + " WHERE " + ATIVO + " = " + ativo  ; //Script sql para Consultar
        ArrayList<Aviso> arraylist = new ArrayList<Aviso>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Aviso aviso;
            while (resultSet.next()){
                aviso = new Aviso();
                aviso.setId(resultSet.getInt(ID));
                aviso.setAtivo(resultSet.getBoolean(ATIVO));
                aviso.setIdveiculo(resultSet.getInt(ID_VEICULO));
                aviso.setTitulo(resultSet.getString(TITULO));
                aviso.setMensagem(resultSet.getString(MENSAGEM));
                aviso.setDatamilis(resultSet.getLong(DATAMILIS));
                arraylist.add(aviso);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoDAO.getArrayAvisosAtivo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public int getQtdeAtivos(boolean ativo) {
       //SELECT COUNT(*) FROM tb_aviso WHERE idveiculo = 5 AND ativo = false
        ArrayList<Aviso> arraylist;
        int qtde = 0;
        try {
           arraylist = getArrayAvisosAtivo(ativo);
           qtde = arraylist.size();
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "AvisoDAO.getQtdeAtivos");
            qtde = 0;
        }finally{
            return qtde;
        }
    }
    

}
