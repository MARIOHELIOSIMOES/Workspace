package com.simoes.mario.brsservice.Classes;

import android.net.Uri;

import java.io.Serializable;

/**
 * Created by Mário on 11/02/2017.
 */

public class Veiculo implements Serializable{
    private String id;
    private String tipo;
    private String marca;
    private String modelo;
    private String combustivel;
    private int ano;
    private double litragem;
    private String oleoMotor;
    private String codfOleo;
    private String codfAr;
    private String codfArCab;
    private String codfComb;
    private String oleoCambio;

    public Veiculo(){

    }
    public Veiculo(String id, String tipo, String marca, String modelo, String combustivel,
                   int ano, double litragem, String oleoMotor, String oleoCambio, String codfOleo, String codfAr, String codfArCab, String codfComb){
        this. id = id;
        setTipo(tipo);
        setMarca(marca);
        setModelo(modelo);
        setCombustivel(combustivel);
        setAno(ano);
        setLitragem(litragem);
        setOleoMotor(oleoMotor);
        setOleoCambio(oleoCambio);
        setCodfOleo(codfOleo);
        setCodfAr(codfAr);
        setCodfArCab(codfArCab);
        setCodfComb(codfComb);

    }


    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca.toUpperCase().trim();
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo.toUpperCase().trim();
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getLitragem() {
        return litragem;
    }

    public void setLitragem(double litragem) {
        this.litragem = litragem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOleoMotor() {
        return oleoMotor;
    }

    public void setOleoMotor(String oleoMotor) {
        this.oleoMotor = oleoMotor.toUpperCase().trim();
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo.toUpperCase().trim();
    }

    public String getCombustivel() {
        return combustivel;
    }

    public void setCombustivel(String combustivel) {
        this.combustivel = combustivel.toUpperCase().trim();
    }

    public String getCodfOleo() {
        return codfOleo;
    }

    public void setCodfOleo(String codfOleo) {
        this.codfOleo = codfOleo;
    }

    public String getOleoCambio() {
        return oleoCambio;
    }

    public void setOleoCambio(String oleoCambio) {
        this.oleoCambio = oleoCambio.toUpperCase().trim();
    }

    public String getCodfAr() {
        return codfAr;
    }

    public void setCodfAr(String codfAr) {
        this.codfAr = codfAr;
    }

    public String getCodfArCab() {
        return codfArCab;
    }

    public void setCodfArCab(String codfArCab) {
        this.codfArCab = codfArCab;
    }

    public String getCodfComb() {
        return codfComb;
    }

    public void setCodfComb(String codfComb) {
        this.codfComb = codfComb;
    }
}
