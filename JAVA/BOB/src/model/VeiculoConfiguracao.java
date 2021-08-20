
package model;

import java.util.ArrayList;

public class VeiculoConfiguracao {

    public VeiculoConfiguracao() {
        configuracao = 0;
        descricao = "4x2";
        nRodas = 4;
        nEixos = 2;
        posicaoTracao = new int[]{0,0,1,1};
    }

    public int getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(int configuracao) {
        this.configuracao = configuracao;
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

    public int[] getPosicoesTracao() {
        return posicaoTracao;
    }

    public void setPosicoesTracao(int[] nRodasTracao) {
        this.posicaoTracao = nRodasTracao;
    }
    
    public VeiculoConfiguracao(int configuracao, String descricao, int nRodas, int nEixos, int[] RodasTracao){
        this.configuracao = configuracao;
        this.descricao = descricao;
        this.nEixos = nEixos;
        this.posicaoTracao = RodasTracao;
        this.nRodas = nRodas;
    }
    
    
    private int configuracao; //Conf4x2
    private String descricao; //4 x 2
    private int nRodas; //4
    private int[] posicaoTracao; //0,0,1,1
    private int nEixos;//2
    
    public int getnEixos() {
        return nEixos;
    }

    public void setnEixos(int nEixos) {
        this.nEixos = nEixos;
    }
}
