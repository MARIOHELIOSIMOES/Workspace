package Control;

import Dao.UsuarioDAO;
import Model.Usuario;

import java.util.ArrayList;

public class UsuarioControl {
    private Usuario usuario;
    private ArrayList<Usuario> usuarioArrayList;
    private final UsuarioDAO usuarioDAO;

    public UsuarioControl(){
        usuarioDAO = new UsuarioDAO();
        usuario = new Usuario();
        usuarioArrayList = new ArrayList<Usuario>();
    }
    public Object[] Login(String nome, String senha){
        Object[] retorno = new Object[3];
        try {
            usuarioArrayList.clear();
            retorno[0]=0;
            
            if (nome.equals(null) || nome.equals(""))
                retorno[0]= -1; //nome inválido
            else {
                //usuarioArrayList = mock.todosOsUsuarios();//MOCK
                usuarioArrayList = usuarioDAO.PesquisarTodosByName(nome);
            }
            for (int i = 0; i < usuarioArrayList.size(); i++) {
                if (usuarioArrayList.get(i).getSenha().equals(senha)&&usuarioArrayList.get(i).getNome().equalsIgnoreCase(nome)) {
                    retorno[0]= 1; //usuario confere, permitir login.
                    retorno[1]=usuarioArrayList.get(i);
                }
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 17: "+e);
        }
        return retorno;
    }
    public int AlterarSenha(String nome, String senha){
        try {
            usuarioArrayList.clear();
            //usuarioArrayList = usuarioDAO.PesquisarTodosByName(nome);
            usuario = usuarioArrayList.get(0);
            usuario.setSenha(senha);
            //usuarioDAO.Alterar(usuario);

            return 1;
        }catch (Exception e){
            throw new RuntimeException("Erro 18: "+e);
        }
    }
    public int AlterarSenha(Usuario usuario, String senhaAtual, String senhaNova){
        if(senhaAtual.equalsIgnoreCase(usuario.getSenha())){
            usuario.setSenha(senhaNova);
            new UsuarioDAO().Alterar(usuario);        
            return 1;
        }else{
            return 0;
        }
        
    }
    
    
    public int NovoUsuario(String nome, String senha){
        try {
            usuario = new Usuario();
            usuario.setNome(nome);
            usuario.setSenha(senha);
            usuarioDAO.Inserir(usuario);
            return 1;
        }catch (Exception e){
            throw new RuntimeException("Erro 19: "+e);
        }
    }

}
