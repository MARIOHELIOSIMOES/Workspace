package data;

import java.util.ArrayList;
import model.Item;

public class ItemDAO extends  ConectionDAO{

    private static final String TB_ITEM = "tb_item";
    private static final String ID = "id";
    private static final String MARCA = "marca";
    private static final String MODELO = "modelo";
    private static final String VALOR = "valor";
    private static final String TIPO = "tipo";
    private static final String INFO = "info";
    
    public ItemDAO(){
        connection = getConexao();
    }
    public ItemDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(Item item){
        AbrirConexao();
                    //INSERT INTO `tb_item` (`id`, `tipo`, `marca`, `modelo`, `valor`, `info`) VALUES (NULL, '1', 'IPIRANGA', '5W30 SEMI SINTÉTICO', '18', 'ÓLEO MOTOR 4 TEMPOS');
        String sql = "INSERT INTO "+TB_ITEM+" ("+TIPO+", "+MARCA+", "+MODELO+", "+VALOR+", "+INFO+") VALUES (?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, item.getTipo());
            stmt.setString(2, item.getMarca());
            stmt.setString(3, item.getModelo());
            stmt.setFloat(4, item.getValor());
            stmt.setString(5, item.getInfo());
            
            stmt.execute();
            stmt.close();
            
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "ItemDAO.Inserir");
             return false;
        }finally{
            FecharConexao();
            return true;
        }
    }

    public boolean Alterar(Item item){
        AbrirConexao();
        String sql = "UPDATE "+TB_ITEM+" SET "+TIPO+" = ?, "+MARCA+" = ?, "+MODELO+" = ?, "+VALOR+" = ?, "+INFO+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, item.getTipo());
            stmt.setString(2, item.getMarca());
            stmt.setString(3, item.getModelo());
            stmt.setFloat(4, item.getValor());
            stmt.setString(5, item.getInfo());
            stmt.setInt(6, item.getId());
            stmt.execute();
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ItemDAO.Alterar");
            
        }finally{
            FecharConexao();
            return true;
        }
    }

    public void Excluir(Item item){
        AbrirConexao();
        String sql = "DELETE FROM "+TB_ITEM+" WHERE "+ID+" = " + item.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ItemDAO.Excluir");
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<Item> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_ITEM; //Script sql para Consultar
        ArrayList<Item> arraylist = new ArrayList<Item>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Item item;
            while (resultSet.next()){
                item = new Item();
                item.setId(resultSet.getInt(ID));
                item.setTipo(resultSet.getInt(TIPO));
                item.setMarca(resultSet.getString(MARCA));
                item.setModelo(resultSet.getString(MODELO));
                item.setValor(resultSet.getFloat(VALOR));
                item.setInfo(resultSet.getString(INFO));
                arraylist.add(item);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ItemDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public Item PesquisarById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_ITEM+" WHERE "+ID+" = "+id;
        Item item = new Item();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                item.setId(resultSet.getInt(ID));
                item.setTipo(resultSet.getInt(TIPO));
                item.setMarca(resultSet.getString(MARCA));
                item.setModelo(resultSet.getString(MODELO));
                item.setValor(resultSet.getFloat(VALOR));
                item.setInfo(resultSet.getString(INFO));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ItemDAO.PesquisarById");
        }finally{
            FecharConexao();
            return item;
        }
    }
    
    public ArrayList<Item> PesquisarTodosByTipo(String numerosSeparadosPorVirgula){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_ITEM+" WHERE "+TIPO+" IN ("+numerosSeparadosPorVirgula+")"; //Script sql para Consultar
        ArrayList<Item> arraylist = new ArrayList<Item>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Item item;
            while (resultSet.next()){
                item = new Item();
                item.setId(resultSet.getInt(ID));
                item.setTipo(resultSet.getInt(TIPO));
                item.setMarca(resultSet.getString(MARCA));
                item.setModelo(resultSet.getString(MODELO));
                item.setValor(resultSet.getFloat(VALOR));
                item.setInfo(resultSet.getString(INFO));
                arraylist.add(item);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ItemDAO.PesquisarTodosByTipo");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public ArrayList<Item> PesquisarByIDs(String numerosSeparadosPorVirgula){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_ITEM+" WHERE "+ID+" IN ("+numerosSeparadosPorVirgula+")"; //Script sql para Consultar
        ArrayList<Item> arraylist = new ArrayList<Item>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Item item;
            while (resultSet.next()){
                item = new Item();
                item.setId(resultSet.getInt(ID));
                item.setTipo(resultSet.getInt(TIPO));
                item.setMarca(resultSet.getString(MARCA));
                item.setModelo(resultSet.getString(MODELO));
                item.setValor(resultSet.getFloat(VALOR));
                item.setInfo(resultSet.getString(INFO));
                arraylist.add(item);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ItemDAO.PesquisarByIDs");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }

    public int getIdByMarcaModelo(String marca, String modelo) {
         AbrirConexao();
        String sql = "SELECT * FROM "+TB_ITEM+" WHERE "+MARCA+" = '"+marca
                                                +"' AND " + MODELO + " = '"+modelo+"'";
        Item item = new Item();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                item.setId(resultSet.getInt(ID));
                item.setTipo(resultSet.getInt(TIPO));
                item.setMarca(resultSet.getString(MARCA));
                item.setModelo(resultSet.getString(MODELO));
                item.setValor(resultSet.getFloat(VALOR));
                item.setInfo(resultSet.getString(INFO));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "ItemDAO.getIdByMarcaModelo");
        }finally{
            FecharConexao();
            return item.getId();
        }
    }
       
}
