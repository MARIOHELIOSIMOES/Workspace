
package model;

public class PneuPosicao {
    
    public PneuPosicao(){
        setIdPneu(0);
        setIdVeiculo(0);
        setPosicao(0);
        setDatamilis(0);
        setKm(0);
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getIdPneu() {
        return idPneu;
    }

    public void setIdPneu(int idPneu) {
        this.idPneu = idPneu;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public long getDatamilis() {
        return datamilis;
    }

    public void setDatamilis(long datamilis) {
        this.datamilis = datamilis;
    }

    private int idVeiculo, idPneu, posicao, km;
    private long datamilis;

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }
}
