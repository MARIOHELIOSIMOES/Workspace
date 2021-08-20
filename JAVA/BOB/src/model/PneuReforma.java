package model;

public class PneuReforma {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPneu() {
        return idPneu;
    }

    public void setIdPneu(int idPneu) {
        this.idPneu = idPneu;
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

    public String getOficina() {
        return oficina;
    }

    public void setOficina(String oficina) {
        this.oficina = oficina;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    private int id, idPneu, km;
    private float valor;
    private String oficina, info;
    private long datamilis;
    private static final String EMPTY = "";
    public PneuReforma(){
        setId(0);
        setIdPneu(0);
        setKm(0);
        setValor(0);
        setOficina(EMPTY);
        setInfo(EMPTY);
        setDatamilis(0);
    }

    public long getDatamilis() {
        return datamilis;
    }

    public void setDatamilis(long datamilis) {
        this.datamilis = datamilis;
    }
}
