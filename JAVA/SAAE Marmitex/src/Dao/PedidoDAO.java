package Dao;

import Model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class PedidoDAO extends ConectionDAO {
    private Connection connection;
    private PreparedStatement stmt;
    private Statement statement;
    private ResultSet resultSet;
    private final ArrayList<Pedido> pedidoArrayList = new ArrayList<Pedido>();

    public PedidoDAO(){
        //connection = new ConectionDAO().getConexao();
        connection = getConexao();
    }
    public PedidoDAO(String host){
         connection = new ConectionDAO().getConexaoHost(host);
    }
    private void AbrirConexao(){
        try{
            //connection = new ConectionDAO().getConexao();
            connection = getConexao();
        }catch(Exception e){
            
        }
    }
    private void FecharConexao(){
        try{
            connection.close();
            statement.close();
            resultSet.close();
        }catch(Exception e){}
    }
    public void Inserir(Pedido pedido){
        String sql = "INSERT INTO "+TB_PEDIDO+" ("+ID_USUARIO+", "+ID_RESTAURANTE+", "+TIMEMILI+", "+QUANTIDADE+", "+DESCRICAO+") VALUES (?, ?, ?, ?, ?)"; //Script sql para inserção
        try {
            AbrirConexao();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pedido.getIdUsuario());
            stmt.setInt(2, pedido.getIdRestaurante());
            //stmt.setDate(3, Date.valueOf(pedido.getData().toString()));
            stmt.setLong(3, pedido.getTimeMili());
            stmt.setInt(4, pedido.getQuantidade());
            stmt.setString(5, pedido.getDescricao());
            stmt.execute();
            stmt.close();
        }catch (Exception e){
            throw new RuntimeException("Erro 12: "+e);
        }
        finally{
            FecharConexao();
        }
    }

    public void Alterar(Pedido pedido){
        String sql = "UPDATE "+TB_PEDIDO+" SET "+ID_USUARIO+" = ?, "+ID_RESTAURANTE+" = ?, "+TIMEMILI+" = ?, "+QUANTIDADE+" = ?, "+DESCRICAO+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {
            AbrirConexao();
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pedido.getIdUsuario());
            stmt.setInt(2, pedido.getIdRestaurante());
            //stmt.setDate(3, Date.valueOf(pedido.getData().toString()));
            stmt.setLong(3, pedido.getTimeMili());
            stmt.setInt(4, pedido.getQuantidade());
            stmt.setString(5, pedido.getDescricao());
            stmt.setInt(6, pedido.getId());
            stmt.execute();
            stmt.close();

        }catch (Exception e){
            throw new RuntimeException("Erro 13: "+e);
        }
        finally{
            FecharConexao();
        }
    }

    public void Excluir(Pedido pedido){
        String sql = "DELETE FROM "+TB_PEDIDO+" WHERE "+ID+" = " + pedido.getId(); //Script sql para Excluir
        try {
            AbrirConexao();
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        }catch (Exception e){
            throw new RuntimeException("Erro 14: "+e);
        }
        finally{
            FecharConexao();
        }
    }

    public ArrayList<Pedido> PesquisarTodos(){
        String sql = "SELECT * FROM "+TB_PEDIDO; //Script sql para Consultar
        pedidoArrayList.clear();
        try {
            AbrirConexao();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Pedido p = new Pedido();
                p.setId(resultSet.getInt(ID));
                p.setIdUsuario(resultSet.getInt(ID_USUARIO));
                p.setIdRestaurante(resultSet.getInt(ID_RESTAURANTE));
                p.setTimeMili(resultSet.getLong(TIMEMILI));
                p.setQuantidade(resultSet.getInt(QUANTIDADE));
                p.setDescricao(resultSet.getString(DESCRICAO));

                pedidoArrayList.add(p);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 15: "+e);
        }finally{
            FecharConexao();
        }
        
        return pedidoArrayList;
    }
    public ArrayList<Pedido> PesquisarTodosByDesc(String desc){
        String sql = "SELECT * FROM "+TB_PEDIDO+" WHERE "+DESCRICAO+" LIKE '%"+desc+"%'"; //Script sql para Consultar
        pedidoArrayList.clear();
        try {
            AbrirConexao();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Pedido p = new Pedido();
                p.setId(resultSet.getInt(ID));
                p.setIdUsuario(resultSet.getInt(ID_USUARIO));
                p.setIdRestaurante(resultSet.getInt(ID_RESTAURANTE));
                p.setTimeMili(resultSet.getLong(TIMEMILI));
                p.setQuantidade(resultSet.getInt(QUANTIDADE));
                p.setDescricao(resultSet.getString(DESCRICAO));

                pedidoArrayList.add(p);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 16: "+e);
        }finally{
            FecharConexao();
        }
        
        return pedidoArrayList;
    }
    public ArrayList<Pedido> PesquisarTodosBySQLstring(String parametros){
        String sql = "SELECT * FROM "+TB_PEDIDO+" WHERE "+ parametros; //Script sql para Consultar
        pedidoArrayList.clear();
        try {
            AbrirConexao();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Pedido p = new Pedido();
                p.setId(resultSet.getInt(ID));
                p.setIdUsuario(resultSet.getInt(ID_USUARIO));
                p.setIdRestaurante(resultSet.getInt(ID_RESTAURANTE));
                p.setTimeMili(resultSet.getLong(TIMEMILI));
                p.setQuantidade(resultSet.getInt(QUANTIDADE));
                p.setDescricao(resultSet.getString(DESCRICAO));
                pedidoArrayList.add(p);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 16: "+e);
        }finally{
            FecharConexao();
        }
        
        return pedidoArrayList;
    }
    public Pedido UltimoPedido(){
        ArrayList<Pedido> lista = PesquisarTodos();
        return lista.get(lista.size()-1);
    }
}
