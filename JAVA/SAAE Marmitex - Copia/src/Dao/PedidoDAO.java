package Dao;

import Model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class PedidoDAO extends ConectionDAO {
    private final Connection connection;
    private PreparedStatement stmt;
    private Statement statement;
    private ResultSet resultSet;
    private final ArrayList<Pedido> pedidoArrayList = new ArrayList<Pedido>();

    public PedidoDAO(){
        connection = new ConectionDAO().getConexao();
    }
    public void Inserir(Pedido pedido){
        String sql = "INSERT INTO "+TB_PEDIDO+" ("+ID_USUARIO+", "+ID_RESTAURANTE+", "+DATA+", "+DESCRICAO+") VALUES (?, ?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pedido.getIdUsuario());
            stmt.setInt(2, pedido.getIdRestaurante());
            //stmt.setDate(3, Date.valueOf(pedido.getData().toString()));
            stmt.setString(3, pedido.getData().toString());
            stmt.setString(4, pedido.getDescricao());
            stmt.execute();
            stmt.close();
        }catch (Exception e){
            throw new RuntimeException("Erro 12: "+e);
        }
    }

    public void Alterar(Pedido pedido){
        String sql = "UPDATE "+TB_PEDIDO+" SET "+ID_USUARIO+" = ?, "+ID_RESTAURANTE+" = ?, "+DATA+" = ?, "+DESCRICAO+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, pedido.getIdUsuario());
            stmt.setInt(2, pedido.getIdRestaurante());
            //stmt.setDate(3, Date.valueOf(pedido.getData().toString()));
            stmt.setString(3, pedido.getData());
            stmt.setString(4, pedido.getDescricao());
            stmt.setInt(5, pedido.getId());
            stmt.execute();
            stmt.close();

        }catch (Exception e){
            throw new RuntimeException("Erro 13: "+e);
        }
    }

    public void Excluir(Pedido pedido){
        String sql = "DELETE FROM "+TB_PEDIDO+" WHERE "+ID+" = " + pedido.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        }catch (Exception e){
            throw new RuntimeException("Erro 14: "+e);
        }
    }

    public ArrayList<Pedido> PesquisarTodos(){
        String sql = "SELECT * FROM "+TB_PEDIDO; //Script sql para Consultar
        pedidoArrayList.clear();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Pedido p = new Pedido();
                p.setId(resultSet.getInt(ID));
                p.setIdUsuario(resultSet.getInt(ID_USUARIO));
                p.setIdRestaurante(resultSet.getInt(ID_RESTAURANTE));
                p.setData(resultSet.getString(DATA));
                p.setDescricao(resultSet.getString(DESCRICAO));

                pedidoArrayList.add(p);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 15: "+e);
        }
        return pedidoArrayList;
    }
    public ArrayList<Pedido> PesquisarTodosByDesc(String desc){
        String sql = "SELECT * FROM "+TB_PEDIDO+" WHERE "+DESCRICAO+" LIKE '%"+desc+"%'"; //Script sql para Consultar
        pedidoArrayList.clear();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Pedido p = new Pedido();
                p.setId(resultSet.getInt(ID));
                p.setIdUsuario(resultSet.getInt(ID_USUARIO));
                p.setIdRestaurante(resultSet.getInt(ID_RESTAURANTE));
                p.setData(resultSet.getString(DATA));
                p.setDescricao(resultSet.getString(DESCRICAO));

                pedidoArrayList.add(p);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 16: "+e);
        }
        return pedidoArrayList;
    }
}
