
package model;

public class VeiculoConfiguracao {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getnRodas() {
        return nRodas;
    }

    public void setnRodas(int nRodas) {
        this.nRodas = nRodas;
    }

    public int getnRodasTracao() {
        return nRodasTracao;
    }

    public void setnRodasTracao(int nRodasTracao) {
        this.nRodasTracao = nRodasTracao;
    }
    private int id;
    private String descricao;
    private int nRodas;
    private int nRodasTracao;
}
