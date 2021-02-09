package Control;

import Dao.UsuarioDAO;
import Mock.UsuarioMock;
import Model.Usuario;

import java.util.ArrayList;

public class UsuarioControl {
    private Usuario usuario;
    private ArrayList<Usuario> usuarioArrayList;
    private final UsuarioDAO usuarioDAO;
    private UsuarioMock mock;

    public UsuarioControl(){
        usuarioDAO = new UsuarioDAO();
        usuario = new Usuario();
        usuarioArrayList = new ArrayList<Usuario>();
        mock = new UsuarioMock();
    }
    public int Login(String nome, String senha){
        try {
            usuarioArrayList.clear();
            if (nome.equals(null) || nome.equals(""))
                return -1; //nome inválido
            else {
                //usuarioArrayList = mock.todosOsUsuarios();//MOCK
                usuarioArrayList = usuarioDAO.PesquisarTodosByName(nome);
            }
            for (int i = 0; i < usuarioArrayList.size(); i++) {
                if (usuarioArrayList.get(i).getSenha().equals(senha)) {
                    return 1; //usuario confere, permitir login.
                }
            }
        }catch (Exception e){
            throw new RuntimeException("Erro 17: "+e);
        }
                return 0;// nome ou senha incorreta
    }
    public int AlterarSenha(String nome, String senha){
        try {
            usuarioArrayList.clear();
            //usuarioArrayList = usuarioDAO.PesquisarTodosByName(nome);
            usuarioArrayList = mock.todosOsUsuarios();//MOCK
            usuario = usuarioArrayList.get(0);
            usuario.setSenha(senha);
            //usuarioDAO.Alterar(usuario);

            return 1;
        }catch (Exception e){
            throw new RuntimeException("Erro 18: "+e);
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
