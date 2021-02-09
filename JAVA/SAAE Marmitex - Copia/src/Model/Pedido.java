package Model;

import java.util.Date;

public class Pedido {
    private int id;
    private int idUsuario;
    private int idRestaurante;
    private String data;
    private String descricao;

    public Pedido(){

    }
    public Pedido(int id, int idUsuario, int idRestaurante, String data, String descricao){
        this.id = id;
        this.idRestaurante = idRestaurante;
        this.idUsuario = idUsuario;
        this.data = data;
        this.descricao = descricao;
    }
    public Pedido(int idUsuario, int idRestaurante, String data, String descricao){
        this.idUsuario = idUsuario;
        this.idRestaurante = idRestaurante;
        this.descricao = descricao;
        this.data = data;
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
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
