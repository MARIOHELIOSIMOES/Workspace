package com.simoes.mario.brsservice.Classes;

import java.io.Serializable;

/**
 * Created by Mário on 11/02/2017.
 */

public class Oleo implements Serializable{
    private String id;
    private String tipo;
    private String marca;
    private String modelo;
    private String nome;
    private String combustivel;
    private double valor;


    public Oleo(){

    }
    public Oleo(String id, String tipo, String marca, String modelo, String nome, String combustivel, double valor){
        this.id = id;
        this.tipo = tipo.toUpperCase().trim();
        this.marca = marca.toUpperCase().trim();
        this.modelo = modelo.toUpperCase().trim();
        this.nome = nome.toUpperCase().trim();
        this.combustivel = combustivel.toUpperCase().trim();
        this.valor = valor;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca.toUpperCase().trim();
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.toUpperCase().trim();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase().trim();
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel.toUpperCase().trim();
    }
}
