
package model;

public class VeiculoKM {
    public VeiculoKM(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public long getDataMilis() {
        return dataMilis;
    }

    public void setDataMilis(long dataMilis) {
        this.dataMilis = dataMilis;
    }
    private int id=0;
    private int idVeiculo=0;
    private int valor=0;
    private long dataMilis=0;
    private int idUsuario=0;

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
}
