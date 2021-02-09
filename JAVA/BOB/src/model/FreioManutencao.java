package model;

import java.util.ArrayList;

public class FreioManutencao {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Item> getArraylist() {
        return arraylist;
    }

    public void setArraylist(ArrayList<Item> arraylist) {
        this.arraylist = arraylist;
    }

    public int getIdRoda() {
        return idRoda;
    }

    public void setIdRoda(int idRoda) {
        this.idRoda = idRoda;
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

    public int getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(int idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public FreioManutencao(){
        
    }
    private int id=0;
    private ArrayList<Item> arraylist = new ArrayList<Item>();
    private int idRoda;
    private long dataMilis;
    private int km;
    private int idVeiculo;

}
