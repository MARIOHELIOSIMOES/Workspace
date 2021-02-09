package com.simoes.mario.brsservice.Interface;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.simoes.mario.brsservice.Classes.Constantes;
import com.simoes.mario.brsservice.R;

import java.util.Random;

/**
 * Created by Mário on 11/02/2017.
 */
public class MainActivityBRS extends AppCompatActivity {

    String[] tipo = new String[]{"Caminhão", "Carro", "Moto"};
    String[] combu = new String[]{"Gasolina", "Etanol", "Flex"};
    String[] marcas = new String[]{"GM", "Honda", "Citroen", "Fiat"};
    String[] Modelos = new String[]{"Modelo 1", "Modelo 2", "Modelo 3", "Modelo 4"};
    String[] Anos = new String[]{"2011", "2012", "2013", "2014"};


    Random random = new Random();
    ImageButton imgBusca, imgNovo, imbCadastro, imgItens;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brsmain);


        imbCadastro = (ImageButton) findViewById(R.id.imgCadastro);
        imgBusca = (ImageButton) findViewById(R.id.img2);
//        imgNovo = (ImageButton)findViewById(R.id.img3);
        imgItens = (ImageButton) findViewById(R.id.imgItens);

        imgItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getBaseContext(), CadastroItem.class);
                i.putExtra(Constantes.COD_OPERACAO, Constantes.CAD_ATUALIZAR);
                startActivity(i);
            }
        });
        imgBusca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), Pesquisar.class);
                startActivity(i);

            }
        });
        imbCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CadastroVeiculos.class);
                startActivity(i);
            }
        });

  /*      imgNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              /*  Veiculo veiculo = new Veiculo();
                veiculo.setAno(Integer.parseInt(Anos[random.nextInt(4)]));
                veiculo.setTipo(tipo[random.nextInt(3)]);
                veiculo.setCombustivel(combu[random.nextInt(3)]);
                veiculo.setMarca(marcas[random.nextInt(4)]);
                veiculo.setModelo(Modelos[random.nextInt(4)]);
                veiculo.setLitragem(random.nextInt(4));
                veiculo.setOleoMotor(""+random.nextInt(4));
                veiculo.setCodfOleo(""+random.nextInt(4));

                List<Oleo> listaOleos = new ArrayList<Oleo>();
                List<Filtro> listaFiltros = new ArrayList<Filtro>();
                List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();

                FireVeiculo fireVeiculo = new FireVeiculo();
                FireFiltro fireFiltro = new FireFiltro();
                FireOleo fireOleo = new FireOleo();


                Veiculo v1 = new Veiculo();
                v1.setTipo(Constantes.TIPO_CARRO);
                v1.setMarca("CHEVROLET");
                v1.setAno(2017);
                v1.setModelo("CAMARO");
                v1.setCombustivel(Constantes.COMB_FLEX);
                v1.setLitragem(4);
                v1.setOleoCambio("OleoCambio"+v1.getModelo());
                v1.setOleoMotor("OleoMotor"+v1.getModelo());

                v1.setCodfOleo("codfOleo");
                v1.setCodfArCab("codfArCab");
                v1.setCodfComb("codfcomb");
                v1.setCodfAr("codfAr");

                listaVeiculos.add(v1);

                Oleo motor1 = new Oleo();
                motor1.setMarca("marca Oleo Motor 1");
                motor1.setModelo(v1.getOleoMotor());
                motor1.setCombustivel(Constantes.COMB_FLEX);
                motor1.setNome("Oleo para "+v1.getModelo());
                motor1.setTipo(Constantes.OLEO_MOTOR);
                motor1.setValor(15);

                listaOleos.add(motor1);

                Oleo motor2 = new Oleo();
                motor2.setCombustivel(Constantes.COMB_GAS);
                motor2.setMarca("marca Oleo Motor 2");
                motor2.setModelo(v1.getOleoMotor());
                motor2.setNome("Oleo para "+v1.getModelo());
                motor2.setTipo(Constantes.OLEO_MOTOR);
                motor2.setValor(20);

                listaOleos.add(motor2);

                Oleo cambio1 = new Oleo();
                cambio1.setCombustivel(Constantes.COMB_FLEX);
                cambio1.setMarca("marca oleo cambio 1");
                cambio1.setModelo(v1.getOleoCambio());
                cambio1.setNome("Oleo para "+v1.getModelo());
                cambio1.setTipo(Constantes.OLEO_CAMBIO);
                cambio1.setValor(16);

                listaOleos.add(cambio1);

                Oleo cambio2 = new Oleo();
                cambio2.setMarca("marca oleo cambio 2");
                cambio2.setNome("Oleo para "+v1.getModelo());
                cambio2.setModelo(v1.getOleoCambio());
                cambio2.setTipo(Constantes.OLEO_CAMBIO);
                cambio2.setValor(21);

                listaOleos.add(cambio2);

                Filtro filtro1 = new Filtro();
                filtro1.setCodigo(v1.getCodfOleo());
                filtro1.setMarca("Marca Filtro Ar 1");
                filtro1.setValor(10);
                filtro1.setModelo("Modelo AR " + v1.getModelo());
                filtro1.setTipo(Constantes.FILTRO_AR);

                listaFiltros.add(filtro1);

                Filtro filtro2 = new Filtro();
                filtro2.setCodigo(v1.getCodfOleo());
                filtro2.setMarca("Marca Filtro Ar 2");
                filtro2.setValor(15);
                filtro2.setModelo("Modelo Ar " + v1.getModelo());
                filtro2.setTipo(Constantes.FILTRO_AR);

                listaFiltros.add(filtro2);

                Filtro filtro3 = new Filtro();
                filtro3.setCodigo(v1.getCodfOleo());
                filtro3.setMarca("Marca filtro Ar cab 1");
                filtro3.setValor(11);
                filtro3.setModelo("Modelo ar cab " + v1.getModelo());
                filtro3.setTipo(Constantes.FILTRO_AR_CAB);

                listaFiltros.add(filtro3);

                Filtro filtro4 = new Filtro();
                filtro4.setCodigo(v1.getCodfOleo());
                filtro4.setMarca("Marca filtro Ar Cab 2");
                filtro4.setValor(16);
                filtro4.setModelo("Modelo arcab " + v1.getModelo());
                filtro4.setTipo(Constantes.FILTRO_AR_CAB);

                listaFiltros.add(filtro4);

                Filtro filtro5 = new Filtro();
                filtro5.setCodigo(v1.getCodfOleo());
                filtro5.setMarca("Marca filtro comb 1 ");
                filtro5.setValor(12);
                filtro5.setModelo("Modelo comb " + v1.getModelo());
                filtro5.setTipo(Constantes.FILTRO_COMB);

                listaFiltros.add(filtro5);

                Filtro filtro6 = new Filtro();
                filtro6.setCodigo(v1.getCodfOleo());
                filtro6.setMarca("Marca filtro comb 2");
                filtro6.setValor(17);
                filtro6.setModelo("Modelo cOMBUS " + v1.getModelo());
                filtro6.setTipo(Constantes.FILTRO_COMB);

                listaFiltros.add(filtro6);

                Filtro filtro9 = new Filtro();
                filtro9.setCodigo(v1.getCodfOleo());
                filtro9.setMarca("Marca filtro oleo 1");
                filtro9.setValor(10);
                filtro9.setModelo("Modelo X1 oleo " + v1.getModelo());
                filtro9.setTipo(Constantes.FILTRO_OLEO);

                listaFiltros.add(filtro9);

                Filtro filtro10 = new Filtro();
                filtro10.setCodigo(v1.getCodfOleo());
                filtro10.setMarca("Marca filtro oleo 2");
                filtro10.setValor(15);
                filtro10.setModelo("Modelo X oleo 2 " + v1.getModelo());
                filtro10.setTipo(Constantes.FILTRO_OLEO);

                listaFiltros.add(filtro10);

                fireFiltro.SalvarNovo(listaFiltros);
                fireOleo.SalvarNovo(listaOleos);
                fireVeiculo.SalvarNovo(v1);

            }
        });

*/


    }
}
