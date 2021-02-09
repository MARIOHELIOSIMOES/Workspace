
package model;

public class Veiculo {

    private int id=0;
    private String placa="";
    private String data="";
    private String marca="";
    private String modelo="";
    private String tipo="";
    private String carroceria="";
    private int configuracao=0;
    private String info="";
    private int ano=0;
    private int combustivel = 0;
    private static int conf4x2 = 0;
    private static int conf4x4 = 1;
    private static int conf6x2 = 2;
    private static int conf8x2 = 3;
    private static int conf8x4 = 4;
    private static int conf2x1 = 5;
    public static final String[] confLabels={"4x2","4x4","6x2","8x2","8x4","2x1"};
    public static final String[] COMBUSTIVEIS = {"Flex", "Gasolina", "Etanol", "Diesel", "Outro"};
    public static final int FLEX = 0, GASOLINA = 1, ETANOL = 2, DIESEL = 3, OUTRO = 4;
    
    public Veiculo(){
        
    }
    public void preencherPadrao(){
        setPlaca("placa");
        setMarca("marca");
        setModelo("modelo");
        setTipo("tipo");
        setConfiguracao(conf4x2);
        setId(0);
        setInfo("info");
        setCarroceria("carroceria");
        setAno(2020);
    }
    
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
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
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
    
}
