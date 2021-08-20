package model;

public class PneuDesgaste {

    public int getIdPneu() {
        return idPneu;
    }

    public void setIdPneu(int idPneu) {
        this.idPneu = idPneu;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getKmEntrada() {
        return kmEntrada;
    }

    public void setKmEntrada(int kmEntrada) {
        this.kmEntrada = kmEntrada;
    }

    public int getKmSaida() {
        return kmSaida;
    }

    public void setKmSaida(int kmSaida) {
        this.kmSaida = kmSaida;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    public PneuDesgaste(){
        setIdPneu(0);
        setIdVeiculo(0);
        setKmEntrada(0);
        setKmSaida(0);
        setValor(0);
        setDatamilis(0);
        setPosicao(0);
    }
    
    private int id, idPneu, idVeiculo, kmEntrada, kmSaida, posicao;
    private float valor;
    private long datamilis;

    
    public long getDatamilis() {
        return datamilis;
    }

    public void setDatamilis(long datamilis) {
        this.datamilis = datamilis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getKmPercorrido() {
        int kmPercorrido = getKmSaida() - getKmEntrada();
        kmPercorrido = (kmPercorrido > 0) ? kmPercorrido : 0;
        return kmPercorrido;
    }

    public int getPosicao() {
        return posicao;
    }

    public void setPosicao(int posicao) {
        this.posicao = posicao;
    }

    public float getCustoKm() {
        float custo = 0;
        int kmpercorrido = getKmPercorrido();
        kmpercorrido = kmpercorrido==0? 1: kmpercorrido;
        custo = getValor();
        custo = custo / kmpercorrido;
        
        return custo;
    }
}
