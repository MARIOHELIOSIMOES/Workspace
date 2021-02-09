package Control;

//baixar a lib iText e importar no projeto

import Model.Pedido;
import Model.Restaurante;
import Model.Usuario;


public class GeradorPDF {
    
    Pedido pedido;
    Restaurante restaurante;
    Usuario usuario;
    
    public GeradorPDF(){
        
    }
    public GeradorPDF(Pedido pedido, Restaurante restaurante, Usuario usuario){
        this.pedido = pedido;
        this.restaurante = restaurante;
        this.usuario = usuario;
      
    }
    private void CriarDocumentoPDF(){
        
    }
    
    
    
    
    
}
