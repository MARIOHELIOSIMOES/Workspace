package Dao;

import Model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class UsuarioDAO extends  ConectionDAO{

    private final Connection connection;
    private PreparedStatement stmt;
    private Statement statement;
    private ResultSet resultSet;
    private final ArrayList<Usuario> usuarioArrayList = new ArrayList<Usuario>();

    public UsuarioDAO(){
        connection = new ConectionDAO().getConexao();
    }
    public void Inserir(Usuario usuario){
        String sql = "INSERT INTO "+TB_USUARIO+" ("+NOME+", "+SENHA+") VALUES (?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2,usuario.getSenha());
            stmt.execute();
            stmt.close();
        }catch (Exception e){
            throw new RuntimeException("Erro 2: "+e);
        }
    }

    public void Alterar(Usuario usuario){
        String sql = "UPDATE "+TB_USUARIO+" SET "+NOME+" = ?, "+SENHA+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setString(2,usuario.getSenha());
            stmt.setInt(3,usuario.getId());
            stmt.execute();
            stmt.close();

        }catch (Exception e){
            throw new RuntimeException("Erro 3: "+e);
        }
    }

    public void Excluir(Usuario usuario){
        String sql = "DELETE FROM "+TB_USUARIO+" WHERE "+ID+" = " + usuario.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            statement.close();
        }catch (Exception e){
            throw new RuntimeException("Erro 4: "+e);
        }
    }

    public ArrayList<Usuario> PesquisarTodos(){
        String sql = "SELECT * FROM "+TB_USUARIO; //Script sql para Consultar
        usuarioArrayList.clear();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Usuario u = new Usuario();
                u.setId(resultSet.getInt(ID));
                u.setNome(resultSet.getString(NOME));
                u.setSenha(resultSet.getString(SENHA));
                usuarioArrayList.add(u);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 5: "+e);
        }
        return usuarioArrayList;
    }
    public ArrayList<Usuario> PesquisarTodosByName(String nome){
        String sql = "SELECT * FROM "+TB_USUARIO+" WHERE "+NOME+" LIKE '%"+nome+"%'"; //Script sql para Consultar
        usuarioArrayList.clear();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Usuario u = new Usuario();
                u.setId(resultSet.getInt(ID));
                u.setNome(resultSet.getString(NOME));
                u.setSenha(resultSet.getString(SENHA));
                usuarioArrayList.add(u);
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 6: "+e);
        }
        return usuarioArrayList;
    }
}
