package com.simoes.mario.brsservice.Classes;

import java.io.Serializable;

/**
 * Created by Mário on 11/02/2017.
 */

public class Filtro implements Serializable {

        private String id;
        private String codigo;
        private String tipo;
        private String marca;
        private String modelo;
        private double valor;


        public Filtro(){

        }
        public Filtro(String id, String codigo, String tipo, String marca, String modelo, double valor){
            this.id = id;
            this.codigo=codigo;
            this.marca = marca.toUpperCase().trim();
            this.modelo=modelo.toUpperCase().trim();
            this.valor = valor;
            this.tipo = tipo.toUpperCase().trim();
            if(codigo.equals(Constantes.GERAR_NOVO_COD)){
                this.codigo = this.id;
            }
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


        public String getCodigo() {
            return codigo;
        }

        public void setCodigo(String codigo) {
            this.codigo = codigo;
        }

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo.toUpperCase().trim();
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo.toUpperCase().trim();
        }
}
