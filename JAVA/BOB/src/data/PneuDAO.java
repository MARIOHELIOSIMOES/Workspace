package data;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import java.util.ArrayList;
import model.Pneu;

public class PneuDAO extends  ConectionDAO{

    private static final String TB_PNEU = "tb_pneu";
    private static final String ID = "id";
    private static final String ID_ITEM = "iditem";
    private static final String FOGO = "fogo";
    private static final String KM = "km";
    private static final String KMTRACAO = "kmtracao";
    private static final String VALOR = "valor";
    private static final String STATUS = "status";
    private static final String INFO = "info";
    
    
    
    
    public PneuDAO(){
        connection = getConexao();
    }
    public PneuDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(Pneu pneu){
        AbrirConexao();
        boolean retorno = false;
                    //INSERT INTO `tb_pneu` (`id`, `iditem`, `fogo`, `km`, `kmtracao`) VALUES (NULL, '10', '15', '0', '0');
        String sql = "INSERT INTO "+TB_PNEU+" ("+ID_ITEM+", "+FOGO+", "+KM+", "+KMTRACAO+", "+VALOR+", "+STATUS+", "+INFO+") VALUES (?, ?, ?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pneu.getIdItem());
            stmt.setInt(2, pneu.getFogo());
            stmt.setInt(3, pneu.getKm());
            stmt.setInt(4, pneu.getKmTracao());
            stmt.setFloat(5, pneu.getValor());
            stmt.setInt(6, pneu.getStatus());
            stmt.setString(7, pneu.getInfo());
            
            stmt.execute();
            retorno = true;
        }/*catch(MySQLIntegrityConstraintViolationException e){
            aux.showMessageWarning("Já existe um pneu registrado com o fogo "+ pneu.getFogo(), "Chave Duplicada");
             retorno = false;
        }*/catch(com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException e){
            aux.showMessageWarning("Já existe um pneu registrado com o fogo "+ pneu.getFogo(), "Chave Duplicada");
             retorno = false;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "PneuDAO.Inserir");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }

    public boolean AtualizarKMsByIdPneu(int idpneu, int km, boolean tracao){
        Pneu p = PesquisarById(idpneu);
        try {
            AbrirConexao();
            String sql = "UPDATE "+TB_PNEU+" SET "+((tracao)?KMTRACAO: KM)+" = ?  WHERE "+ID+" = ? "; //Script sql para alterar
            
            int novokm = km + (tracao? p.getKmTracao(): p.getKm());
            
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, novokm);
            stmt.setInt(2, idpneu);
            stmt.execute();
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.AtualizarKMsByIdPneu");
        }finally{
            FecharConexao();
            return true;
        }
    }
    
    public boolean Alterar(Pneu pneu){
        AbrirConexao();
        String sql = "UPDATE "+TB_PNEU+" SET "+ID_ITEM+" = ?, "+FOGO+" = ?, "+KM+" = ?, "+KMTRACAO+" = ?, "+VALOR+" = ?, "+STATUS+" = ?, "+INFO+" = ?  WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pneu.getIdItem());
            stmt.setInt(2, pneu.getFogo());
            stmt.setInt(3, pneu.getKm());
            stmt.setInt(4, pneu.getKmTracao());
            stmt.setFloat(5, pneu.getValor());
            stmt.setInt(6, pneu.getStatus());
            stmt.setString(7, pneu.getInfo());
            stmt.setInt(8, pneu.getId());
            stmt.execute();
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.Alterar");
        }finally{
            FecharConexao();
            return true;
        }
    }

    public void Excluir(Pneu pneu){
        AbrirConexao();
        String sql = "DELETE FROM "+TB_PNEU+" WHERE "+ID+" = " + pneu.getId(); //Script sql para Excluir
       
        
        /*EXCLUIR TODOS OS ITENS DE TABELA RELACIONADOS COM O ID DO PNEU*/
        aux.RegistrarLog("Verificar Método Excluir", "PneuDAO.Excluir");
        
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.Excluir");
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<Pneu> PesquisarTodos(){// Dados de marca, modelo devem ser pesquisadas na tabela de item
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PNEU; //Script sql para Consultar
        ArrayList<Pneu> arraylist = new ArrayList<Pneu>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Pneu pneu;
            while (resultSet.next()){
                pneu = new Pneu();
                pneu.setId(resultSet.getInt(ID));
                pneu.setIdItem(resultSet.getInt(ID_ITEM));
                pneu.setFogo(resultSet.getInt(FOGO));
                pneu.setKm(resultSet.getInt(KM));
                pneu.setKmTracao(resultSet.getInt(KMTRACAO));
                pneu.setValor(resultSet.getFloat(VALOR));
                pneu.setStatus(resultSet.getInt(STATUS));
                pneu.setInfo(resultSet.getString(INFO));
                
                arraylist.add(pneu);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public Pneu PesquisarById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PNEU+" WHERE "+ID+" = "+id;
        Pneu pneu = new Pneu();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            //statement.closeOnCompletion();
            while(resultSet.next()){
                pneu.setId(resultSet.getInt(ID));
                pneu.setIdItem(resultSet.getInt(ID_ITEM));
                pneu.setFogo(resultSet.getInt(FOGO));
                pneu.setKm(resultSet.getInt(KM));
                pneu.setKmTracao(resultSet.getInt(KMTRACAO));
                pneu.setValor(resultSet.getFloat(VALOR));
                pneu.setStatus(resultSet.getInt(STATUS));
                pneu.setInfo(resultSet.getString(INFO));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.PesquisarById");
        }finally{
            FecharConexao();
            return pneu;
        }
    }
    
    public ArrayList<Pneu> PesquisarTodosByIdItem(int iditem){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_PNEU+" WHERE "+ID_ITEM+" = "+iditem; //Script sql para Consultar
        ArrayList<Pneu> arraylist = new ArrayList<Pneu>();
        
        try {
           statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Pneu pneu;
            while (resultSet.next()){
                pneu = new Pneu();
                pneu.setId(resultSet.getInt(ID));
                pneu.setIdItem(resultSet.getInt(ID_ITEM));
                pneu.setFogo(resultSet.getInt(FOGO));
                pneu.setKm(resultSet.getInt(KM));
                pneu.setKmTracao(resultSet.getInt(KMTRACAO));
                pneu.setValor(resultSet.getFloat(VALOR));
                pneu.setStatus(resultSet.getInt(STATUS));
                pneu.setInfo(resultSet.getString(INFO));
                
                arraylist.add(pneu);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.PesquisarTodosByTipo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
     public ArrayList<Pneu> PesquisarTodosByStatus(int status){
        AbrirConexao();
            String sql = "SELECT * FROM "+TB_PNEU+" WHERE "+STATUS+" = "+status; //Script sql para Consultar
        ArrayList<Pneu> arraylist = new ArrayList<Pneu>();
        
        try {
           statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Pneu pneu;
            while (resultSet.next()){
                pneu = new Pneu();
                pneu.setId(resultSet.getInt(ID));
                pneu.setIdItem(resultSet.getInt(ID_ITEM));
                pneu.setFogo(resultSet.getInt(FOGO));
                pneu.setKm(resultSet.getInt(KM));
                pneu.setKmTracao(resultSet.getInt(KMTRACAO));
                pneu.setValor(resultSet.getFloat(VALOR));
                pneu.setStatus(resultSet.getInt(STATUS));
                pneu.setInfo(resultSet.getString(INFO));
                
                arraylist.add(pneu);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.PesquisarTodosByTipo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
   
    
    public ArrayList<Pneu> PesquisarTodosByIds(int[] ids){
        AbrirConexao();
        String idLista = "";
        for(int id: ids){
            idLista+=id+", "; //Adiciona os ids
        }
        idLista += (ids.length > 0) ? ids[0]: 0; //Verifica o tamanho da lista de ids e finaliza a lista
        
        String sql = "SELECT * FROM "+TB_PNEU+" WHERE "+ID+" IN ( "+idLista+" )"; //Script sql para Consultar
        ArrayList<Pneu> arraylist = new ArrayList<Pneu>();
        
        try {
           statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Pneu pneu;
            while (resultSet.next()){
                pneu = new Pneu();
                pneu.setId(resultSet.getInt(ID));
                pneu.setIdItem(resultSet.getInt(ID_ITEM));
                pneu.setFogo(resultSet.getInt(FOGO));
                pneu.setKm(resultSet.getInt(KM));
                pneu.setKmTracao(resultSet.getInt(KMTRACAO));
                pneu.setValor(resultSet.getFloat(VALOR));
                pneu.setStatus(resultSet.getInt(STATUS));
                pneu.setInfo(resultSet.getString(INFO));
                arraylist.add(pneu);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.PesquisarTodosByTipo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }

    
    public ArrayList<Pneu> PesquisarTodosByLivresIds(int[] ids) {
        AbrirConexao();
        String idLista = "";
        for(int id: ids){
            idLista+=id+", "; //Adiciona os ids
        }
        idLista += (ids.length > 0) ? ids[0]: 0; //Verifica o tamanho da lista de ids e finaliza a lista
        
        String sql = "SELECT * FROM "+TB_PNEU+" WHERE "+ID+" NOT IN ( "+idLista+" )"; //Script sql para Consultar
        ArrayList<Pneu> arraylist = new ArrayList<Pneu>();
        
        try {
           statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Pneu pneu;
            while (resultSet.next()){
                pneu = new Pneu();
                pneu.setId(resultSet.getInt(ID));
                pneu.setIdItem(resultSet.getInt(ID_ITEM));
                pneu.setFogo(resultSet.getInt(FOGO));
                pneu.setKm(resultSet.getInt(KM));
                pneu.setKmTracao(resultSet.getInt(KMTRACAO));
                pneu.setValor(resultSet.getFloat(VALOR));
                pneu.setStatus(resultSet.getInt(STATUS));
                pneu.setInfo(resultSet.getString(INFO));
                arraylist.add(pneu);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.PesquisarTodosByTipo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<Pneu> PesquisarTodosByRodandoIds(int[] ids) {
        AbrirConexao();
        String idLista = "";
        for(int id: ids){
            idLista+=id+", "; //Adiciona os ids
        }
        idLista += (ids.length > 0) ? ids[0]: 0; //Verifica o tamanho da lista de ids e finaliza a lista
        
        String sql = "SELECT * FROM "+TB_PNEU+" WHERE "+ID+" IN ( "+idLista+" )"; //Script sql para Consultar
        ArrayList<Pneu> arraylist = new ArrayList<Pneu>();
        
        try {
           statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Pneu pneu;
            while (resultSet.next()){
                pneu = new Pneu();
                pneu.setId(resultSet.getInt(ID));
                pneu.setIdItem(resultSet.getInt(ID_ITEM));
                pneu.setFogo(resultSet.getInt(FOGO));
                pneu.setKm(resultSet.getInt(KM));
                pneu.setKmTracao(resultSet.getInt(KMTRACAO));
                pneu.setValor(resultSet.getFloat(VALOR));
                pneu.setStatus(resultSet.getInt(STATUS));
                pneu.setInfo(resultSet.getString(INFO));
                arraylist.add(pneu);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.PesquisarTodosByTipo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }

    public boolean alterarStatusByIdPneu(int idPneu, int status) {
        boolean retorno = false;
        try {
            AbrirConexao();
            String sql = "UPDATE "+TB_PNEU+" SET "+ STATUS+" = ?  WHERE "+ID+" = ? "; //Script sql para alterar
            
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, status);
            stmt.setInt(2, idPneu);
            stmt.execute();
           retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.AtualizarKMsByIdPneu");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
}
