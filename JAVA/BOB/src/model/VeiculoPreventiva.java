package model;

//Classe para modelar a manutenção preventiva
public class VeiculoPreventiva {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_veiculo() {
        return id_veiculo;
    }

    public void setId_veiculo(int id_veiculo) {
        this.id_veiculo = id_veiculo;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public long getDatamilis() {
        return datamilis;
    }

    public void setDatamilis(long datamilis) {
        this.datamilis = datamilis;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
    public VeiculoPreventiva(){
        
    }
    
    private int id=0;
    private int id_veiculo=0;
    private int tipo=0;
    private int km=0;
    private int kmProx = 0;
    private long datamilis=0;
    private int idPedido = 0;
    private String info="";

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public int getKmProx() {
        return kmProx;
    }

    public void setKmProx(int kmProx) {
        this.kmProx = kmProx;
    }
    

}
