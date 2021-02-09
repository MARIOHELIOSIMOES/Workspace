
package model;

public class VeiculoItem {

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }
    
    public long getDataMilis() {
        return dataMilis;
    }

    public void setDataMilis(long dataMilis) {
        this.dataMilis = dataMilis;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    private int idVeiculo;
    private long dataMilis;
    private int km;
    private float valor;
    
}
