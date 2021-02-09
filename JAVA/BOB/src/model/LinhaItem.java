package model;

public class LinhaItem {

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getKmTroca() {
        return kmTroca;
    }

    public void setKmTroca(int kmTroca) {
        this.kmTroca = kmTroca;
    }

    public int getKmAtual() {
        return kmAtual;
    }

    public void setKmAtual(int kmAtual) {
        this.kmAtual = kmAtual;
    }

    public int getKmProxima() {
        return kmProxima;
    }

    public void setKmProxima(int kmProxima) {
        this.kmProxima = kmProxima;
    }

    public int getKmDia() {
        return kmDia;
    }

    public void setKmDia(int kmDia) {
        this.kmDia = kmDia;
    }

    
    public LinhaItem(){
        
    }
    public LinhaItem(String nome, int kmTroca, int kmAtual, int kmProxima, int kmDia){
        setNome(nome);
        setKmTroca(kmTroca);
        setKmAtual(kmAtual);
        setKmProxima(kmProxima);
        setKmDia(this.kmDia);
    }
    
    private String nome = "item";
    private int kmTroca=0, kmAtual=0, kmProxima=0;
    private int kmDia = 1;
    
}
