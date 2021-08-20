package model;

public class GraficoItem {

    public GraficoItem(){
        
    }
    public GraficoItem(String nome, int valor){
        setNome(nome);
        setValor(valor);
    }
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
    private String nome;
    private double valor;
}
