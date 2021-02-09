
package model;

public class VeiculoPneuConf {

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

    public long getDataMilis() {
        return dataMilis;
    }

    public void setDataMilis(long dataMilis) {
        this.dataMilis = dataMilis;
    }
    private int idVeiculo;
    private int idPneu;
    private int posicao;
    private long dataMilis;
}
