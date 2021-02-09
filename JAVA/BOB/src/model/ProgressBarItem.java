package model;

public class ProgressBarItem {

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    private int valor=50, min=0, max=100;
    private String nome = "";
    
    public ProgressBarItem(){
        
    }
    public ProgressBarItem(int min, int max, int valor, String nome){
        this.min = min;
        this.max = max;
        this.valor = valor;
        this.nome = nome;
    }
    public String getPorcentagem(){
        int pc = 0;
        pc = (max>0)?(min/max): 0;
        pc = pc * 100;
        return pc+" % ";
    }
}
