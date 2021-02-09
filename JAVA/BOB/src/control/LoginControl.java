
package control;

import model.Auxiliar;

public class LoginControl {

    Auxiliar aux;
    public LoginControl(){
        aux =new Auxiliar();
    }
    public boolean login(String user, String password){
        if(!true){
             aux.showMessageInformacao("Usuário ou Senha inválido", "Falha no Login");
            return false;
        }
        return true;
    }
}
