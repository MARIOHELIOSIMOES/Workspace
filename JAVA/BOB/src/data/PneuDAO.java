package data;

import java.util.ArrayList;
import model.Pneu;

public class PneuDAO extends  ConectionDAO{

    private static final String TABELA = "tb_pneu";
    private static final String ID = "id";
    private static final String ID_ITEM = "iditem";
    private static final String FOGO = "fogo";
    private static final String KM = "km";
    private static final String KMTRACAO = "kmtracao";
    
    public PneuDAO(){
        connection = getConexao();
    }
    public PneuDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(Pneu pneu){
        AbrirConexao();
                    //INSERT INTO `tb_pneu` (`id`, `iditem`, `fogo`, `km`, `kmtracao`) VALUES (NULL, '10', '15', '0', '0');
        String sql = "INSERT INTO "+TABELA+" ("+ID_ITEM+", "+FOGO+", "+KM+", "+KMTRACAO+") VALUES (?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pneu.getIdItem());
            stmt.setInt(2, pneu.getFogo());
            stmt.setInt(3, pneu.getKm());
            stmt.setInt(4, pneu.getKmTracao());
            
            stmt.execute();
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "PneuDAO.Inserir");
             return false;
        }finally{
            FecharConexao();
            return true;
        }
    }

    public boolean Alterar(Pneu pneu){
        AbrirConexao();
        String sql = "UPDATE "+TABELA+" SET "+ID_ITEM+" = ?, "+FOGO+" = ?, "+KM+" = ?, "+KMTRACAO+" = ?  WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pneu.getIdItem());
            stmt.setInt(2, pneu.getFogo());
            stmt.setInt(3, pneu.getKm());
            stmt.setInt(4, pneu.getKmTracao());
            stmt.setInt(6, pneu.getId());
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
        String sql = "DELETE FROM "+TABELA+" WHERE "+ID+" = " + pneu.getId(); //Script sql para Excluir
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
        String sql = "SELECT * FROM "+TABELA; //Script sql para Consultar
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
        String sql = "SELECT * FROM "+TABELA+" WHERE "+ID+" = "+id;
        Pneu pneu = new Pneu();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                pneu.setId(resultSet.getInt(ID));
                pneu.setIdItem(resultSet.getInt(ID_ITEM));
                pneu.setFogo(resultSet.getInt(FOGO));
                pneu.setKm(resultSet.getInt(KM));
                pneu.setKmTracao(resultSet.getInt(KMTRACAO));
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
        String sql = "SELECT * FROM "+TABELA+" WHERE "+ID_ITEM+" = "+iditem; //Script sql para Consultar
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
        
        String sql = "SELECT * FROM "+TABELA+" WHERE "+ID_ITEM+" IN ( "+idLista+" )"; //Script sql para Consultar
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
                
                arraylist.add(pneu);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "PneuDAO.PesquisarTodosByTipo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
}
