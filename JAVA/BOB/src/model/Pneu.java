
package model;

public class Pneu extends Item{

    public static int ESTOQUE = 0;
    public static int RODANDO = 1;
    public static int REFORMA = 2;
    public static int SUCATA = 3;
    public static int VENDIDO = 4;
    
    public static String[] STATUS_PNEU = {"Estoque", "Rodando", "Reforma", "Sucata", "Vendido"};
    
    public Pneu(){
        super();
        setTipo(Item.PNEU);
    }
    public String getStatusString(){
        return STATUS_PNEU[getStatus()];
    }
    public int getKm() {
        return km;
    }

    public void setKm(int km) {
        this.km = km;
    }

    public int getKmTracao() {
        return kmTracao;
    }

    public void setKmTracao(int kmTracao) {
        this.kmTracao = kmTracao;
    }
    public int getKmTotal(){
        return getKm() + getKmTracao();
    }
    public void setIdItem(int idItem) {
        this.idItem = idItem;
    }
    public int getIdItem(){
        return idItem;
    }
    
    private int idItem = 0;
    private int fogo=0;
    private int km = 0, kmTracao = 0;
    private int status = ESTOQUE;
    private float valor = 0;
    
    public void setItem(Item item){
        setMarca(item.getMarca());
        setModelo(item.getModelo());
      //setValor(item.getValor());
      //  setInfo(item.getInfo());
        setTipo(item.getTipo());
    }
    public Item getItem(){
        Item i = new Item();
        i.setId(getIdItem());
        i.setMarca(getMarca());
        i.setModelo(getModelo());
        i.setValor(getValor());
        i.setInfo(getInfo());
        i.setTipo(getTipo());
        return i;
    }
    public float getCustoKm(){
        float custo = 0;
        custo = (getValor()/(getKmTotal()+1));
        return custo;
    }
    public int getFogo(){
        return this.fogo;
    }
    public void setFogo(int fogo){
        this.fogo = fogo;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
