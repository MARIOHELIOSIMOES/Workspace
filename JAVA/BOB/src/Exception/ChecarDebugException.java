package Exception;

import javax.swing.JOptionPane;
import model.Auxiliar;

public class ChecarDebugException extends Exception{

    public ChecarDebugException(){
        
    }
    public ChecarDebugException(String metodo) {
        new Auxiliar().showMessage("Verificar método: "+metodo , "Inesperado", JOptionPane.WARNING_MESSAGE);
    }
    

}
