package Model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Pedido {

    
    private int id;
    private int idUsuario;
    private int idRestaurante;
    private long timeMili;
    private int quantidade;
    private String descricao;

    public Pedido(){

    }
    public Pedido(int id, int idUsuario, int idRestaurante, long timeMili, int quantidade, String descricao){
        this.id = id;
        this.idRestaurante = idRestaurante;
        this.idUsuario = idUsuario;
        this.timeMili = timeMili;
        this.quantidade = quantidade;
        this.descricao = descricao;
    }
    public Pedido(int idUsuario, int idRestaurante, long timeMili, int quantidade, String descricao){
        this.idUsuario = idUsuario;
        this.idRestaurante = idRestaurante;
        this.descricao = descricao;
        this.timeMili = timeMili;
        this.quantidade = quantidade;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public int getIdRestaurante() {
        return idRestaurante;
    }
    public void setIdRestaurante(int idRestaurante) {
        this.idRestaurante = idRestaurante;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setTimeMili(Long timeMili){
        this.timeMili = timeMili;
    }
    public long getTimeMili(){
        return timeMili;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public Date getDate(){
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(timeMili);
        return c.getTime();
    }
    public String getDataString(){
        String data = "";
        int dia = getDia();
        int mes = getMes();
        data+= dia<10 ? "0"+dia+"/": dia+"/";
        data+= mes<10 ? "0"+mes+"/": mes+"/";
        data+=getAno();
        return data;
        //return getDia()+"/"+(getMes()+1)+"/"+getAno();
    }
    public String getDataHoraString(){
        return getDataString()+" "+getTempo();
    }
    public int getDia(){
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(timeMili);
        return c.get(Calendar.DAY_OF_MONTH);
    }
    public int getMes(){
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(timeMili);
        return (c.get(Calendar.MONTH)+1);
    }
    public int getAno(){
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(timeMili);
        return c.get(Calendar.YEAR);
    }

    private String getTempo() {
        Calendar c = new GregorianCalendar();
        c.setTimeInMillis(timeMili);
        return c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE);
    }
    
    
}
