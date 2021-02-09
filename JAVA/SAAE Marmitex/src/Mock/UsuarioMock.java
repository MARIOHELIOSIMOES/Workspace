package Mock;

import Model.Usuario;

import java.util.ArrayList;
import java.util.Random;

public class UsuarioMock {
    private Usuario usuario;
    private ArrayList<Usuario> usuarioArrayList;

    public UsuarioMock(){
        usuario = new Usuario();
        usuarioArrayList = new ArrayList<Usuario>();
        gerarListaFixa();
    }
    public void adicionar(Usuario u){
        int id = new Random().nextInt(10000);
        u.setId(id);
        usuarioArrayList.add(u);
    }
    public void alterar(Usuario u, int index){
        usuarioArrayList.set(index, usuario);
    }

    public void gerarListaAleatoria(int tamanho){
        usuarioArrayList.clear();
        usuario.setId(1);
        usuario.setNome("mario");
        usuario.setSenha("2108");
        usuarioArrayList.add(usuario);
        for (int i = 0; i<tamanho; i++) {
            int id = new Random().nextInt(10000);
            usuario = new Usuario();
            usuario.setId(id);
            usuario.setNome("User" + id);
            usuario.setSenha("Senha" + id);
            usuarioArrayList.add(usuario);
        }
    }
    public void gerarListaFixa(){
        usuarioArrayList.clear();
        usuario.setId(1);
        usuario.setNome("mario");
        usuario.setSenha("2108");
        usuarioArrayList.add(usuario);
        for (int i = 2; i<10; i++) {
            usuario = new Usuario();
            usuario.setId(i);
            usuario.setNome("User" + i);
            usuario.setSenha("Senha" + i);
            usuarioArrayList.add(usuario);
        }
    }
    public int getIndexByName(String nome){
        for (int i = 0; i< usuarioArrayList.size(); i++){
            if(usuarioArrayList.get(i).getNome().equals(nome)){
                return i;
            }
        }
        return 0;
    }

    public ArrayList<Usuario> todosOsUsuarios(){
        return usuarioArrayList;
    }
}
