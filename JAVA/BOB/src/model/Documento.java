
package model;

import java.util.Calendar;

public class Documento {

    private int id;
    private String n_registro;
    private long validade_milis;
    private String info;
    
    public String getDataString(){
        return new Auxiliar(validade_milis).getDataString();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getN_registro() {
        return n_registro;
    }

    public void setN_registro(String n_registro) {
        this.n_registro = n_registro;
    }

    public long getValidade_milis() {
        return validade_milis;
    }

    public void setValidade_milis(long validade_milis) {
        this.validade_milis = validade_milis;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
    
}
