package data;

import java.util.ArrayList;
import model.Usuario;

public class UsuarioDAO extends  ConectionDAO{

    private static final String TB_USUARIO = "tb_usuario";
    private static final String TB_USUARIO_TIPO = "tb_usuario_tipo";
    private static final String ID = "id";
    private static final String ID_TIPO = "id_tipo";
    private static final String NOME = "nome";
    private static final String DESCRICAO = "descricao";
    public UsuarioDAO(){
        
        connection = getConexao();
    }
    public UsuarioDAO(String host){
        connection = getConexaoHost(host);
    }
    
    private boolean Inserir(Usuario usuario, int idTipo){
        AbrirConexao();
        boolean retorno=false;
                    //INSERT INTO `tb_usuario` (`id`, `nome`, `id_tipo`) VALUES (NULL, 'admin', '3');
        String sql = "INSERT INTO "+TB_USUARIO+" ("+NOME+", "+ID_TIPO+") VALUES (?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.getIdTipoUsuario());
            stmt.execute();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "UsuarioDAO.Inserir");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean InserirTipoUsuario(String descricao){
        AbrirConexao();
        boolean retorno = false;
        //INSERT INTO `tb_documento` (`id`, `n_registro`, `validade`, `info`) VALUES (NULL, '51551515', '151515', 'teste');
        String sql = "INSERT INTO "+TB_USUARIO_TIPO+" ("+DESCRICAO+") VALUES (?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, descricao);
            stmt.execute();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "UsuarioDAO.InserirTipoUsuario");
             retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean Alterar(Usuario usuario){
        AbrirConexao();
        boolean retorno = false;
        String sql = "UPDATE "+TB_USUARIO+" SET "+NOME+" = ?, "+ID_TIPO+"= ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.getIdTipoUsuario());
            stmt.setInt(3, usuario.getId());
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.Alterar");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean AlterarTipoUsuario(int id, String descricao){
        AbrirConexao();
        boolean retorno = false;
        String sql = "UPDATE "+TB_USUARIO_TIPO+" SET "+DESCRICAO+" = ? WHERE "+ID+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, descricao);
            stmt.setInt(2, id);
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.AlterarTipoUsuario");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean Excluir(Usuario usuario){
        AbrirConexao();
        boolean retorno = false;
        String sql = "DELETE FROM "+TB_USUARIO+" WHERE "+ID+" = " + usuario.getId(); //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.Excluir");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public ArrayList<Usuario> PesquisarTodos(){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_USUARIO; //Script sql para Consultar
        ArrayList<Usuario> arraylist = new ArrayList<Usuario>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
             Usuario u;
            while (resultSet.next()){
                u = new Usuario();
                u.setId(resultSet.getInt(ID));
                u.setIdTipoUsuario(resultSet.getInt(Integer.parseInt(ID_TIPO)));
                u.setNome(resultSet.getString(NOME));
                
                arraylist.add(u);
            }
            
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.PesquisarTodos");
        }finally{
            FecharConexao();
            return arraylist;
        }
    }
    public Usuario PesquisarById(int id){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_USUARIO+" WHERE "+ID+" = "+id;
        Usuario u = new Usuario();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                u.setId(resultSet.getInt(ID));
                u.setIdTipoUsuario(resultSet.getInt(ID_TIPO));
                u.setNome(resultSet.getString(NOME));
                break;
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.PesquisarById");
        }finally{
            FecharConexao();
            return u;
        }
    }
    public ArrayList<Usuario> PesquisarTodosByIDTipo(int id_tipo){
        AbrirConexao();
            //SELECT * FROM tb_usuario WHERE id_tipo IN (SELECT id FROM tb_usuario_tipo WHERE id = 3)
        String sql = "SELECT * FROM " +TB_USUARIO+" WHERE "+ID_TIPO+" IN (SELECT "+ ID+" FROM "+TB_USUARIO_TIPO+" WHERE "+ID+" = "+id_tipo+")";
        ArrayList<Usuario> arraylist = new ArrayList<Usuario>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Usuario u;
            while (resultSet.next()){
                u = new Usuario();
                u.setId(resultSet.getInt(ID));
                u.setIdTipoUsuario(resultSet.getInt(Integer.parseInt(ID_TIPO)));
                u.setNome(resultSet.getString(NOME));
                
                arraylist.add(u);
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.PesquisarTodosByIDTipo");
        }
        finally{
            FecharConexao();
            return arraylist;
        }
        
    }
    public Usuario UltimoDocumento(){
        ArrayList<Usuario> lista = PesquisarTodos();
        if(lista.size()>0){
            return lista.get(lista.size()-1);
        }else{
            return new Usuario();
        }
    }    
}
