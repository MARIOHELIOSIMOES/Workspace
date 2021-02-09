package control;

import data.UsuarioDAO;
import model.Usuario;

public class UsuarioControl {

    UsuarioDAO uDao;
    public UsuarioControl(){
        uDao =new UsuarioDAO();
    }
    //Verificar o ID, buscar no banco de dados e retornar o Usuário pelo ID
    public Usuario getUsuarioById(int ID){
        return uDao.PesquisarById(ID);
    }
    
    //Chama o método interno que retorna o Usuário e retorna apenas o nome
    public String getNomeUsuarioById(int ID){
        return getUsuarioById(ID).getNome();
    }
    
    
}
