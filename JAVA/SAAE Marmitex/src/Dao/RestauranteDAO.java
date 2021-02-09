package Dao;

import Model.Restaurante;
import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class RestauranteDAO extends ConectionDAO{

    private Connection connection;
    private PreparedStatement stmt;
    private Statement statement;
    private ResultSet resultSet;
    private final ArrayList<Restaurante> restauranteArrayList = new ArrayList<Restaurante>();

    public RestauranteDAO(){
        //connection = new ConectionDAO().getConexao();
        connection = getConexao();
    }
    public RestauranteDAO(String host){
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
    
    
    public void Inserir(Restaurante restaurante){
        String sql = "INSERT INTO "+TB_RESTAURANTE+" ("+NOME+", "+TELEFONE+", "+ENDERECO+") VALUES (?, ?, ?)"; //Script sql para inserção
        try {
            AbrirConexao();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, restaurante.getNome());
            stmt.setString(2, restaurante.getTelefone());
            stmt.setString(3, restaurante.getEndereco());
            stmt.execute();
            stmt.close();
        }catch (Exception e){
            throw new RuntimeException("Erro 7: "+e);
        }finally{
            FecharConexao();
        }
    }

    public void Alterar(Restaurante restaurante){
        String sql = "UPDATE "+TB_RESTAURANTE+" SET "+NOME+" = ?, "+TELEFONE+" = ?, "+ENDERECO+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {
            AbrirConexao();
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, restaurante.getNome());
            stmt.setString(2, restaurante.getTelefone());
            stmt.setString(3, restaurante.getEndereco());
            stmt.setInt(4, restaurante.getId());
            stmt.execute();
            stmt.close();

        }catch (Exception e){
            throw new RuntimeException("Erro 8: "+e);
        }finally{
            FecharConexao();
        }
    }

    public void Excluir(Restaurante restaurante){
        String sql = "DELETE FROM "+TB_RESTAURANTE+" WHERE "+ID+" = " + restaurante.getId(); //Script sql para Excluir
        try {
            AbrirConexao();
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        }catch (Exception e){
            throw new RuntimeException("Erro 9: "+e);
        }finally{
            FecharConexao();
        }
    }

    public ArrayList<Restaurante> PesquisarTodos(){
        String sql = "SELECT * FROM "+TB_RESTAURANTE; //Script sql para Consultar
        restauranteArrayList.clear();
        try {
            AbrirConexao();
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
        FecharConexao();
        return restauranteArrayList;
    }
    public ArrayList<Restaurante> PesquisarTodosByName(String nome){
        String sql = "SELECT * FROM "+TB_RESTAURANTE+" WHERE "+NOME+" LIKE '%"+nome+"%'"; //Script sql para Consultar
        restauranteArrayList.clear();
        try {
            AbrirConexao();
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
        FecharConexao();
        return restauranteArrayList;
    }
    public Restaurante PesquisarById(int id){
        
        String sql = "SELECT * FROM "+TB_RESTAURANTE+" WHERE "+ID+" = "+id;
        Restaurante r = new Restaurante();
               
        try {
            AbrirConexao();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            resultSet.next();
                r.setId(resultSet.getInt(ID));
                r.setNome(resultSet.getString(NOME));
                r.setTelefone(resultSet.getString(TELEFONE));
                r.setEndereco(resultSet.getString(ENDERECO));
        }catch (Exception e){
            throw new RuntimeException("Erro 11: "+e);
        }
        FecharConexao();
        return r;    
    }

}
