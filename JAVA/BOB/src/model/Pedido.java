
package model;

import java.util.ArrayList;

public class Pedido {
    
    public static final int OLEOFILTRO=1, FREIO=2, PNEU=3, OUTRO=4;
    
    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

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

    public long getDatamilis() {
        return datamilis;
    }

    public void setDatamilis(long datamilis) {
        this.datamilis = datamilis;
    }
    private int id=0;
    private int id_veiculo=0;
    private long datamilis=0;
    private int km=0;
    private int tipo = 0;
    private String info="";
    private ArrayList<PedidoItem> arraylist = new ArrayList<PedidoItem>();
    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public ArrayList<PedidoItem> getArraylist() {
        return arraylist;
    }

    public void setArraylist(ArrayList<PedidoItem> arraylist) {
        this.arraylist = arraylist;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
 
}
