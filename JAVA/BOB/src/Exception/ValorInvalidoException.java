package Exception;

import javax.swing.JOptionPane;
import model.Auxiliar;

public class ValorInvalidoException extends Exception{
    public  ValorInvalidoException (){
        
    }
    public  ValorInvalidoException (String nome, String valor){
        new Auxiliar().showMessage("Verifique o campo: "+nome+", preenchido: '"+valor+"'", "Valor Inválido", JOptionPane.WARNING_MESSAGE);
    }
    public void MostrarMensagem(String nome, String valor){
        new Auxiliar().showMessage("Verifique o campo: "+nome+", preenchido: '"+valor+"'", "Valor Inválido", JOptionPane.WARNING_MESSAGE);
    }
}
