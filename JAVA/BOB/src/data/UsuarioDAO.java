package data;

import java.util.ArrayList;
import model.Usuario;
import model.UsuarioLogin;

public class UsuarioDAO extends  ConectionDAO{

    private static final String TB_USUARIO = "tb_usuario";
    private static final String TB_USUARIO_LOGIN = "tb_usuario_login";
    private static final String ID = "id";
    private static final String ID_TIPO = "id_tipo";
    private static final String ID_USUARIO = "id_usuario";
    private static final String NOME = "nome";
    private static final String PASS_SHA = "pass_sha";
    public UsuarioDAO(){
        
        connection = getConexao();
    }
    public UsuarioDAO(String host){
        connection = getConexaoHost(host);
    }
    
    public boolean Inserir(Usuario usuario){
        AbrirConexao();
        boolean retorno=false;
                    //INSERT INTO `tb_usuario` (`id`, `nome`, `id_tipo`) VALUES (NULL, 'admin', '3');
        String sql = "INSERT INTO "+TB_USUARIO+" ("+NOME+", "+ID_TIPO+") VALUES (?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.getTipo());
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
    public boolean InserirUsuarioLogin(UsuarioLogin login){
        AbrirConexao();
        boolean retorno = false;
        //INSERT INTO `INSERT INTO `tb_usuario_login` (`id_usuario`, `pass_sha`) VALUES ('1', '1234567980123456798012345679801234567980');
        String sql = "INSERT INTO "+TB_USUARIO_LOGIN+" ("+ID_USUARIO+", "+ PASS_SHA+ ") VALUES (?, ?)"; //Script sql para inserção
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setInt(1, login.getIdUsuario());
            stmt.setString(2, login.getSenha());
            stmt.execute();
            retorno = true;
        }catch (Exception e){
             aux.RegistrarLog(e.getMessage(), "UsuarioDAO.InserirUsuarioLogin");
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
            stmt.setInt(2, usuario.getTipo());
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
    public boolean AlterarUsuarioLogin(UsuarioLogin login){
        AbrirConexao();
        boolean retorno = false;
        String sql = "UPDATE "+TB_USUARIO_LOGIN+" SET "+PASS_SHA+" = ? WHERE "+ID_USUARIO+" = ? "; //Script sql para alterar
        try {

            stmt = connection.prepareStatement(sql);
            
            stmt.setString(1, login.getSenha());
            stmt.setInt(2, login.getIdUsuario());
            
            stmt.execute();
            retorno = true;

        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.AlterarUsuarioLogin");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    private boolean ExcluirUsuarioLogin(int idUsuario){
         AbrirConexao();
        boolean retorno = false;
        String sql = "DELETE FROM "+TB_USUARIO_LOGIN+" WHERE "+ID_USUARIO+" = " + idUsuario; //Script sql para Excluir
        try {
            statement = connection.createStatement();
            statement.execute(sql);
            retorno = true;
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.ExcluirUsuarioLogin");
            retorno = false;
        }finally{
            FecharConexao();
            return retorno;
        }
    }
    public boolean Excluir(Usuario usuario){
        if(!ExcluirUsuarioLogin(usuario.getId())){
            return false;
        }
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
                u.setNome(resultSet.getString(NOME));
                u.setTipo(resultSet.getInt(ID_TIPO));
                
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
                u.setTipo(resultSet.getInt(ID_TIPO));
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
    public Usuario PesquisarByNome(String nome){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_USUARIO+" WHERE "+NOME+" LIKE '"+nome+"'";
        Usuario u = new Usuario();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                u.setId(resultSet.getInt(ID));
                u.setTipo(resultSet.getInt(ID_TIPO));
                u.setNome(resultSet.getString(NOME));
                break;
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.PesquisarByNome");
        }finally{
            FecharConexao();
            return u;
        }
    }
   
    public int getIDByNomeAndTipo(Usuario usuario){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_USUARIO+" WHERE "+ID_TIPO+" = "+usuario.getTipo()+
                                                     " AND " + NOME +" LIKE '" + usuario.getNome()+"'";
        Usuario u = new Usuario();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                u.setId(resultSet.getInt(ID));
                u.setTipo(resultSet.getInt(ID_TIPO));
                u.setNome(resultSet.getString(NOME));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.getIDByNomeAndTipo");
        }finally{
            FecharConexao();
            return u.getId();
        }
    }
    
    public ArrayList<Usuario> PesquisarTodosByIDTipo(int id_tipo){
        AbrirConexao();
            //SELECT * FROM tb_usuario WHERE id_tipo IN (SELECT id FROM tb_usuario_tipo WHERE id = 3)
        String sql = "SELECT * FROM " +TB_USUARIO+" WHERE "+ID_TIPO+" IN (SELECT "+ ID+" FROM "+TB_USUARIO_LOGIN+" WHERE "+ID+" = "+id_tipo+")";
        ArrayList<Usuario> arraylist = new ArrayList<Usuario>();
        
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            Usuario u;
            while (resultSet.next()){
                u = new Usuario();
                u.setId(resultSet.getInt(ID));
                u.setTipo(resultSet.getInt(Integer.parseInt(ID_TIPO)));
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
    
    public UsuarioLogin PesquisarByIdUsuario(int idusuario){
        AbrirConexao();
        String sql = "SELECT * FROM "+TB_USUARIO_LOGIN+" WHERE "+ID_USUARIO+" = "+idusuario;
        UsuarioLogin login = new UsuarioLogin();
               
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                login.setIdUsuario(resultSet.getInt(ID_USUARIO));
                login.setSenha(resultSet.getString(PASS_SHA));
            }
        }catch (Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioDAO.PesquisarByIdUsuario");
        }finally{
            FecharConexao();
            return login;
        }
    }
    
}
