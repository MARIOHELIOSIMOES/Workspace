package model;

public class PedidoItem {

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getIdItem() {
        return idItem;
    }

    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public float getQuantidade() {
        return qtde;
    }

    public void setQuantidade(float qtde) {
        this.qtde = qtde;
    }
    
    public PedidoItem(){
        
    }
    public float getValorTotal(){
        return (getValor() * getQuantidade());
    }
    
    private int idPedido=0;
    private int idItem=0;
    private float valor=0;
    private float qtde=0;
    
}
