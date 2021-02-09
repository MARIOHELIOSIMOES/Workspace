
package model;

public class Item {
    
    public static String[] TIPO_STRING={"Todos", "Óleo Motor", "Óleo Cambio","Óleo Diferencial",
                                           "Filtro de Ar", "Filtro de Combustivel","Filtro de Óleo do Motor", 
                                            "Freio", "Pneu", "Outro", "PU","Mão de Obra"};
    public static int[] TIPOS_INT={ Item.TIPO_TODOS, Item.OLEO_MOTOR, Item.OLEO_CAMBIO, Item.OLEO_DIFERENCIAL,
       Item.FILTRO_AR, Item.FILTRO_COMBUSTIVEL, Item.FILTRO_OLEO_MOTOR,
       Item.FREIO, Item.PNEU, Item.OUTRO, Item.PU, Item.MAOOBRA
    };
    
    public static final int TIPO_TODOS = 0;
    public static final int OLEO_MOTOR = 1, OLEO_CAMBIO = 2, OLEO_DIFERENCIAL=3;
    public static final int FILTRO_AR = 4, FILTRO_COMBUSTIVEL = 5, FILTRO_OLEO_MOTOR=6;
    public static final int FREIO = 7;
    public static final int PNEU = 8;
    public static final int OUTRO = 9, PU=10, MAOOBRA=11;
    
    public static int[] OLEOSFILTROS ={
       OLEO_MOTOR,
        OLEO_CAMBIO,
        OLEO_DIFERENCIAL,
        FILTRO_AR,
        FILTRO_COMBUSTIVEL,
        FILTRO_OLEO_MOTOR,
        //PU
     //   MAOOBRA,
       // OUTRO,
    };
    public static int[] OUTROS={
        MAOOBRA,
        OUTRO,
    };

    
    
    
    private int id=0;
    private int tipo=0;
    private String marca="";
    private String modelo="";
    private float valor=0;
    private String info="";

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the modelo
     */
    public String getModelo() {
        return modelo;
    }
    public String getMarcaModelo(){
        return (getMarca()+" - "+getModelo());
    }

    /**
     * @param modelo the modelo to set
     */
    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    /**
     * @return the info
     */
    public String getInfo() {
        return info;
    }

    /**
     * @param info the info to set
     */
    public void setInfo(String info) {
        this.info = info;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    

}
