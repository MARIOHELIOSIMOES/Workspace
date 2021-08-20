package control;

import data.UsuarioDAO;
import java.util.ArrayList;
import model.Auxiliar;
import model.Usuario;
import model.UsuarioLogin;
import view.jpGerenciarUsuarios;

public class UsuarioControl {

    UsuarioDAO uDao;
    Auxiliar aux;
    public static int STATUS = 0;
    public static int USUARIO = 1;
    public static int INFO = 2;
    public static int TAMANHO = 3;
    private static String EMPTY_USER = "Nome de usuário em branco";
    private static String SENHA_DIVERGEM = "Senhas divergem"; 
    private static String SENHA_PEQUENA = "A senha deve ter mais de 3 caracteres";
    
    public UsuarioControl(){
        uDao =new UsuarioDAO();
        aux = new Auxiliar();
    }
    //Verificar o ID, buscar no banco de dados e retornar o Usuário pelo ID
    public Usuario getUsuarioById(int ID){
        return uDao.PesquisarById(ID);
    }
    
    //Chama o funcao interno que retorna o Usuário e retorna apenas o nome
    public String getNomeUsuarioById(int ID){
        return getUsuarioById(ID).getNome();
    }
    
    public Object[] login(String nomeUsuario, char[] senha){
        Object[] retorno = new Object[TAMANHO];
        try{
            StringBuilder sb = new StringBuilder();
            for(char a: senha){
                sb.append(a);
            }
            
            Usuario u = uDao.PesquisarByNome(nomeUsuario);
            if(u.getId()==Usuario.ID_USUARIO_VAZIO){
                retorno[STATUS] = false; 
            }else{
                UsuarioLogin login  = uDao.PesquisarByIdUsuario(u.getId());
                retorno[STATUS] = login.gerarCompararSHA(sb.toString());
            }
            retorno[USUARIO]  = u;
       
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioControl.login");
            retorno[STATUS]=false;
        }finally{
             return retorno;
        }
        
        
    }
    public Object[] alterarSenha(Usuario usuario, char[] senhaAtual, char[] senhaNova){
        
        Object[] retorno = new Object[TAMANHO];
        
        try{
            StringBuilder sb = new StringBuilder();
            for(char a: senhaAtual){
                sb.append(a);
            }
            
            StringBuilder sbN = new StringBuilder();
            for(char a: senhaNova){
                sbN.append(a);
            }
            
            UsuarioLogin login = uDao.PesquisarByIdUsuario(usuario.getId());
            if(!login.gerarCompararSHA(sb.toString())){
                retorno[STATUS]=false;
                retorno[INFO]=UsuarioLogin.SENHA_ATUAL_INVALIDA;
                return retorno;
            }else{
                login.setSenha(login.gerarSHA(sbN.toString()));
                retorno[STATUS]=uDao.AlterarUsuarioLogin(login);
                retorno[INFO]=UsuarioLogin.SENHA_ATUALIZADA;
                return retorno;
            }
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioControl.alterarSenha");
            retorno[STATUS]=false;
            retorno[INFO]="FALHA";
            return retorno;
        }
    }
    public boolean inserir(String nome, int tipo, String senha){
        try{
            Usuario usuario = new Usuario();
            usuario.setTipo(tipo);
            usuario.setNome(nome);

            if(!uDao.Inserir(usuario)){
                return false;
            }

            UsuarioLogin login = new UsuarioLogin();
                login.setIdUsuario(uDao.getIDByNomeAndTipo(usuario));
                login.setSenha(login.gerarSHA(senha));
        
            return (uDao.InserirUsuarioLogin(login));
        
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioControl.inserir");
            return false;
        }
    }

    public ArrayList<Usuario> pesquisarTodosUsuarios(boolean admin, boolean opera, boolean visua) {
        return uDao.PesquisarTodos();
    }

    public void alterarUsuario(Usuario u) {
        uDao.Alterar(u);
    }

    public boolean inserirNovo(String usuario, int tipo, char[] password, char[] passwordConfirma) {
        try{
            
            if(usuario.isBlank()){
                jpGerenciarUsuarios.atualizarInfo(EMPTY_USER);
                return false;
            }
            if(password.length<4){
                jpGerenciarUsuarios.atualizarInfo(SENHA_PEQUENA);
                return false;
            }
            StringBuilder sbPass, sbPassC;
            sbPass = new StringBuilder();
            sbPassC = new StringBuilder();
            for(char c: password){
                sbPass.append(c);
            }
            for(char c: passwordConfirma){
                sbPassC.append(c);
            }
            if(!sbPass.toString().equals(sbPassC.toString())){
                jpGerenciarUsuarios.atualizarInfo(SENHA_DIVERGEM);
                return false;
            }
            
            return inserir(usuario, tipo, sbPass.toString());
            
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioControl.inserirNovo");
            return false;
        }
    }

    public boolean excluirUsuario(Usuario usuarioSelecionado) {
        try{
            String id = aux.InputText("Confirme o ID do usuário ("+usuarioSelecionado.getId()+") que deseja excluir para continuar: ");
            if(!id.trim().equalsIgnoreCase(usuarioSelecionado.getId()+"")){
                return false;
            }
            if(uDao.Excluir(usuarioSelecionado)){
                aux.showMessageInformacao("O usuário "+usuarioSelecionado.getNome()+" foi excluído com sucesso.", "Excluir Usuário");
                return true;
            }else{
                aux.showMessageInformacao("Falha ao excluir", "Excluir Usuário");
                return false;
            }
        }catch(Exception e){
            aux.RegistrarLog(e.getMessage(), "UsuarioControl.excluirUsuario");
            return false;
        }
    }
    
    
}
