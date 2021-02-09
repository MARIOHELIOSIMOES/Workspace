package com.simoes.mario.brsservice.Classes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StreamDownloadTask;

import java.security.PublicKey;

/**
 * Created by Mário on 15/10/2016.
 */

public class Constantes {

    public static String VEICULO = "VEICULO";

    public static final String COD_OPERACAO = "COD_OPERACAO";
    public static final String CAD_OLEO = "CAD_OLEO";
    public static final String CAD_FILTRO = "CAD_FILTRO";
    public static final String CAD_ATUALIZAR = "CAD_ATUALIZAR";
    public static final String COD_ATUALIZAR_VEICULO = "COD_ATUALIZAR_VEICULO";

    public static final String LISTA_OLEOS = "LISTA_OLEOS";
    public static final String LISTA_FILTROS = "LISTA_FILTROS";
    public static final String LISTA_OBJETOS = "LISTA_OBJETOS";

    public static final String GERAR_NOVO_COD = "GERAR_NOVO_COD";


    public static final String TIPO_OLEO = "TIPO_OLEO";
    public static final String TIPO_FILTRO = "TIPO_FILTRO";
    public static final String COD_FILTRO = "COD_FILTRO";

    public static final String FILTRO_AR = "FILTRO_AR";
    public static final String FILTRO_AR_CAB = "FILTRO_AR_CAB";
    public static final String FILTRO_OLEO = "FILTRO_OLEO";
    public static final String FILTRO_COMB = "FILTRO_COMB";
//  public static final String FILTRO_DH = "FILTRO_DH";

    public static final String TIPO_CARRO = "CARRO";
    public static final String TIPO_MOTO = "MOTO";
    public static final String TIPO_CAMINHAO = "CAMINHÃO";

    public static final String COMBUSTIVEL = "COMBUSTÍVEL";
    public static final String COMB_FLEX = "FLEX";
    public static final String COMB_GAS = "GASOLINA";
    public static final String COMB_ETANOL = "ETANOL";
    public static final String COMB_DIESEL = "DIESEL";

    public static final String OLEO_MOTOR = "OLEO MOTOR";
    public static final String OLEO_CAMBIO = "OLEO CÂMBIO";
    public static final String OLEO_DIF = "OLEO DIFERENCIAL";

  //  ORIGINAL
    public static String TB_VEICULOS ="TB_VEICULO";
    public static String TB_OLEO ="TB_OLEO";
    public static String TB_FILTRO ="TB_FILTRO";
    public static String TB_MARCAS = "TB_MARCAS";
/*
   /// TESTES
    public static String TB_VEICULOS ="TB_VEICULO_TESTE";
    public static String TB_OLEO ="TB_OLEO_TESTE";
    public static String TB_FILTRO ="TB_FILTRO_TESTE";
    public static String TB_MARCAS = "TB_MARCAS_TESTE";
//*/

    public static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

     public static final DatabaseReference referenceMarcas = firebaseDatabase.getReference(TB_MARCAS);
     public static final DatabaseReference referenceVeiculo = firebaseDatabase.getReference(TB_VEICULOS);
     public static final DatabaseReference referenceOleo = firebaseDatabase.getReference(TB_OLEO);
     public static final DatabaseReference referenceFiltro = firebaseDatabase.getReference(TB_FILTRO);
}
