
package model;

public abstract class Veiculo {

    public abstract String[] getConfLabels();
    public static int[] QTDE_EIXOS;

    private int id=0;
    private String placa="";
    private String data="";
    private String marca="";
    private String modelo="";
    private int tipo=0;
    private String carroceria="";
    private int configuracao=0;
    private String info="";
    private int ano=0;
    private int combustivel = 0;
    private VeiculoConfiguracao veiculoConfiguracao;
  
    public static final String [] TIPOS_STRING = {"Caminhão", "Carro", "Moto", "Engate", "Outro"};
    public static final int CAMINHAO = 0, CARRO = 1, MOTO=2, ENGATE =3, OUTRO_VEICULO = 4;
    
    public static final String[] COMBUSTIVEIS = {"Flex", "Gasolina", "Etanol", "Diesel", "Outro"};
    public static final int FLEX = 0, GASOLINA = 1, ETANOL = 2, DIESEL = 3, OUTRO_COMBUSTIVEL = 4;
    public int[] QTDE_PNEUS;
    
    public Veiculo(){
        preencherPadrao();
    }
    
    public void preencherPadrao(){
        setPlaca("placa");
        setMarca("marca");
        setModelo("modelo");
        //setTipo(0);
        //setConfiguracao(conf4x2);
        //setVeiculoConfiguracao(veiculoConfiguracaoArray[conf4x2]);
        setId(0);
        setInfo("info");
        setCarroceria("carroceria");
        setAno(2020);
    }
    public abstract int getQtdePneu();
    public abstract VeiculoConfiguracao getVeiculoConfiguracaoByIndex(int index);
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }
    
    public void setData(String data) {
        this.data = data;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getCarroceria() {
        return carroceria;
    }

    public void setCarroceria(String carroceria) {
        this.carroceria = carroceria;
    }

    public int getConfiguracao() {
        return configuracao;
    }

    public void setConfiguracao(int configuracao) {
        this.configuracao = configuracao;
    }
    
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPlaca() {
        return placa;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public int getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(int combustivel) {
        this.combustivel = combustivel;
    }

    public VeiculoConfiguracao getVeiculoConfiguracao() {
        return veiculoConfiguracao;
    }

    public void setVeiculoConfiguracao(VeiculoConfiguracao veiculoConfiguracao) {
        this.veiculoConfiguracao = veiculoConfiguracao;
    }

    public abstract String getConfiguracaoLabel();

    public String getTipoLabel() {
        return TIPOS_STRING[getTipo()];
    }
        

    
    
}
