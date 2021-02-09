
package model;

public class Pneu extends Item{

    public Pneu(){
        super();
        setTipo(Item.PNEU);
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
    public void setItem(Item item){
        setMarca(item.getMarca());
        setModelo(item.getModelo());
        setValor(item.getValor());
        setInfo(item.getInfo());
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
    
    public int getFogo(){
        return this.fogo;
    }
    public void setFogo(int fogo){
        this.fogo = fogo;
    }
}
