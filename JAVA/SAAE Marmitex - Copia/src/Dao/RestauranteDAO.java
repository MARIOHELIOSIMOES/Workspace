package Dao;

import Model.Restaurante;
import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RestauranteDAO extends ConectionDAO{

    private final Connection connection;
    private PreparedStatement stmt;
    private Statement statement;
    private ResultSet resultSet;
    private final ArrayList<Restaurante> restauranteArrayList = new ArrayList<Restaurante>();

    public RestauranteDAO(){
        connection = new ConectionDAO().getConexao();
    }
    public void Inserir(Restaurante restaurante){
        String sql = "INSERT INTO "+TB_RESTAURANTE+" ("+NOME+", "+TELEFONE+", "+ENDERECO+") VALUES (?, ?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, restaurante.getNome());
            stmt.setString(2, restaurante.getTelefone());
            stmt.setString(3, restaurante.getEndereco());
            stmt.execute();
            stmt.close();
        }catch (Exception e){
            throw new RuntimeException("Erro 7: "+e);
        }
    }

    public void Alterar(Restaurante restaurante){
        String sql = "UPDATE "+TB_RESTAURANTE+" SET "+NOME+" = ?, "+TELEFONE+" = ?, "+ENDERECO+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, restaurante.getNome());
            stmt.setString(2, restaurante.getTelefone());
            stmt.setString(3, restaurante.getEndereco());
            stmt.setInt(4, restaurante.getId());
            stmt.execute();
            stmt.close();

        }catch (Exception e){
            throw new RuntimeException("Erro 8: "+e);
        }
    }

    public void Excluir(Restaurante restaurante){
        String sql = "DELETE FROM "+TB_RESTAURANTE+" WHERE "+ID+" = " + restaurante.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        }catch (Exception e){
            throw new RuntimeException("Erro 9: "+e);
        }
    }

    public ArrayList<Restaurante> PesquisarTodos(){
        String sql = "SELECT * FROM "+TB_RESTAURANTE; //Script sql para Consultar
        restauranteArrayList.clear();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Restaurante r = new Restaurante();
                r.setId(resultSet.getInt(ID));
                r.setNome(resultSet.getString(NOME));
                r.setTelefone(resultSet.getString(TELEFONE));
                r.setEndereco(resultSet.getString(ENDERECO));
                restauranteArrayList.add(r);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 10: "+e);
        }
        return restauranteArrayList;
    }
    public ArrayList<Restaurante> PesquisarTodosByName(String nome){
        String sql = "SELECT * FROM "+TB_RESTAURANTE+" WHERE "+NOME+" LIKE '%"+nome+"%'"; //Script sql para Consultar
        restauranteArrayList.clear();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Restaurante r = new Restaurante();
                r.setId(resultSet.getInt(ID));
                r.setNome(resultSet.getString(NOME));
                r.setTelefone(resultSet.getString(TELEFONE));
                r.setEndereco(resultSet.getString(ENDERECO));
                restauranteArrayList.add(r);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 11: "+e);
        }
        return restauranteArrayList;
    }

}
