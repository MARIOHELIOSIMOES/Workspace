package com.simoes.mario.brsservice.Interface;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.simoes.mario.brsservice.Classes.Constantes;
import com.simoes.mario.brsservice.Classes.Veiculo;
import com.simoes.mario.brsservice.Firebase.FireVeiculo;
import com.simoes.mario.brsservice.R;

import java.util.ArrayList;
import java.util.List;

public class Pesquisar extends AppCompatActivity {


    FloatingActionButton fabOrcamento;

    List<String> listTipos;
    List<String> listMarcas;
    List<String> listModelos;
    List<String> listCombustivel;
    List<String> listAnos;

    List<Veiculo> listVeiculosTotal;
    List<Veiculo> listVeiculosBusca;
    List<Veiculo> listVeiculoTipo;
    List<Veiculo> listVeiculoMarcas;
    List<Veiculo> listVeiculoModelo;
    List<Veiculo> listVeiculoComb;
    List<Veiculo> listVeiculoAno;


    Veiculo veiculo;
    FireVeiculo fireVeiculo;
    ProgressDialog progressDialog;


    Spinner spinnerMarca, spinnerModelo, spinnerAno,spinnerTipo,spinnerCombustivel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar);

        spinnerTipo = (Spinner)findViewById(R.id.spinnerTipo);
        spinnerMarca = (Spinner)findViewById(R.id.spinnerMarca);
        spinnerModelo = (Spinner)findViewById(R.id.spinnerModelo);
        spinnerCombustivel = (Spinner)findViewById(R.id.spinnerCombustível);
        spinnerAno = (Spinner)findViewById(R.id.spinnerAno);

        fabOrcamento = (FloatingActionButton)findViewById(R.id.fabOrcamento);

        listVeiculosTotal = new ArrayList<Veiculo>();
        listVeiculosBusca = new ArrayList<Veiculo>();

        listVeiculoAno = new ArrayList<Veiculo>();
        listVeiculoComb = new ArrayList<Veiculo>();
        listVeiculoMarcas = new ArrayList<Veiculo>();
        listVeiculoModelo = new ArrayList<Veiculo>();
        listVeiculoTipo = new ArrayList<Veiculo>();

        listTipos =  new ArrayList<String>();
        listMarcas = new ArrayList<String>();
        listModelos = new ArrayList<String>();
        listCombustivel = new ArrayList<String>();
        listAnos = new ArrayList<String>();

        fireVeiculo = new FireVeiculo();
        fabOrcamento.setEnabled(false);

        progressDialog = ProgressDialog.show(this,"Obtendo dados","Aguarde...");
        fireVeiculo.getReference().addValueEventListener(listenerVeiculo);

        spinnerTipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carregarMarcas(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carregarModelo(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carregarCombustiveis(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerCombustivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                carregarAno(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerAno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fabOrcamento.setEnabled(true);
                Snackbar.make(view, "Veículo localizado, clique no botão para ir ao orçamento.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                veiculo = listVeiculoComb.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        fabOrcamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent i = new Intent(getBaseContext(), Activity_Orcamento.class);
                    i.putExtra(Constantes.VEICULO, veiculo);
                    startActivity(i);
                }catch (Exception e){
                    Log.e("Pesquisar", "Erro no botão ");
                }
                /*
                Bundle bundle = new Bundle();

                i.putExtra("key_veiculo", veiculo.getId());
                i.putExtra(Constantes.tipo, veiculo.getTipo());
                i.putExtra(Constantes.marca, veiculo.getMarca());
                i.putExtra(Constantes.modelo, veiculo.getModelo());
                i.putExtra(Constantes.ano, veiculo.getAno());
                i.putExtra(Constantes.litragem, veiculo.getLitragem());
                i.putExtra(Constantes.codfAr, veiculo.getCodfAr());
                i.putExtra(Constantes.codfArCab, veiculo.getCodfArCab());
             //   i.putExtra(Constantes.codfComb, veiculo);

                */

            }
        });

    }

    ValueEventListener listenerVeiculo = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {

                fireVeiculo.ClearListVeiculo();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.d(" OnDataChange ", child.getValue().toString());
                    veiculo = child.getValue(Veiculo.class);
                    fireVeiculo.AddVeiculoLista(veiculo);
                }
                Log.d("OnDataChange Ok", "Tamanho da lista de Ocorrencias: "+fireVeiculo.getListVeiculo().size());

            }catch(Exception e){
                Log.d("OnDataChange Erro!","Try - onDataChange erro - " + e.getMessage());
                Toast.makeText(getApplicationContext(),"Ocorreu um erro!", Toast.LENGTH_SHORT).show();
            }
            progressDialog.dismiss();
            carregarTipos();
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    private void carregarTipos(){
        try {
            listTipos.clear();
            listVeiculosTotal = fireVeiculo.getListVeiculo();
            String tipo = "";
            for (int i = 0; i < listVeiculosTotal.size(); i++) {
                tipo = listVeiculosTotal.get(i).getTipo();
                if (listTipos.contains(tipo)) {
                    Log.d("BuscaActivity", "Já contém a marca : " + tipo);
                } else {
                    Log.d("BuscaActivity", "Adicionando a marca: " + tipo);
                    listTipos.add(tipo);
                }
            }
            ArrayAdapter<String> apt = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listTipos);
            apt.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spinnerTipo.setAdapter(apt);
            spinnerMarca.setAdapter(null);
            spinnerModelo.setAdapter(null);
            spinnerCombustivel.setAdapter(null);
            spinnerAno.setAdapter(null);
        }catch(Exception e){Log.e("Erro!","Erro no Método Carregar tipos- " + e.getMessage()); }
    }
    private void carregarMarcas(int indice){
        //pre
        try{
        listMarcas.clear();
        listVeiculosTotal = fireVeiculo.getListVeiculo();
        listVeiculoTipo.clear();
        String tipo= listTipos.get(indice);

        for(int i =0; i< listVeiculosTotal.size(); i++){
            String t = listVeiculosTotal.get(i).getTipo();
            if(t.equals(tipo)){
                listVeiculoTipo.add(listVeiculosTotal.get(i));
                String marca = listVeiculosTotal.get(i).getMarca();
                if(listMarcas.contains(marca)){
                    Log.d("BuscaActivity", "Já contém a marca : " + marca);
                }else {
                    Log.d("BuscaActivity", "Adicionando a marca: " + marca);
                    listMarcas.add(marca);
                }
            }
        }

        ArrayAdapter<String> apt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listMarcas);
        apt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerMarca.setAdapter(apt);
        spinnerModelo.setAdapter(null);
        spinnerCombustivel.setAdapter(null);
        spinnerAno.setAdapter(null);
        }catch(Exception e){Log.e("Erro!","Erro no Método Carregar Marcas - " + e.getMessage()); }
    }
    private void carregarModelo(int indice){
        try{
        List<Veiculo> lista = new ArrayList<Veiculo>();
        lista.addAll(listVeiculoTipo);
        listVeiculoMarcas.clear();
        listModelos.clear();
        String marca= listMarcas.get(indice);

        for(int i =0; i< lista.size(); i++){
            String m = lista.get(i).getMarca();
            if(m.equals(marca)){
                listVeiculoMarcas.add(lista.get(i));

                String modelo = lista.get(i).getModelo();
                if(listModelos.contains(modelo)){
                    Log.d("BuscaActivity", "Já contém o modelo : " + modelo);
                }else {
                    Log.d("BuscaActivity", "Adicionando o modelo: " + modelo);
                    listModelos.add(modelo);
                }
            }
        }
        ArrayAdapter<String> apt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listModelos);
        apt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerModelo.setAdapter(apt);
        spinnerCombustivel.setAdapter(null);
        spinnerAno.setAdapter(null);
        }catch(Exception e){Log.e("Erro!","Erro no Método Carregar Modelo - " + e.getMessage()); }
    }
    private void carregarCombustiveis(int indice){
        try{
        List<Veiculo> lista = new ArrayList<Veiculo>();
        lista.addAll(listVeiculoMarcas);
        listVeiculoModelo.clear();
        listCombustivel.clear();
        String modelo= listModelos.get(indice);

        for(int i =0; i< lista.size(); i++){
            String m = lista.get(i).getModelo();
            if(m.equals(modelo)){
                listVeiculoModelo.add(lista.get(i));
                String comb = lista.get(i).getCombustivel();
                if(listCombustivel.contains(comb)){
                    Log.d("BuscaActivity", "Já contém o combustivel : " + comb);
                }else {
                    Log.d("BuscaActivity", "Adicionando o combustivel: " + comb);
                    listCombustivel.add(comb);
                }
            }
        }
        ArrayAdapter<String> apt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listCombustivel);
        apt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerCombustivel.setAdapter(apt);
        spinnerAno.setAdapter(null);
        }catch(Exception e){Log.e("Erro!","Erro no Método Carregar Combustíveis - " + e.getMessage()); }
    }
    private void carregarAno(int indice){
        try{
        List<Veiculo> lista = new ArrayList<Veiculo>();
        lista.addAll(listVeiculoModelo);
        listAnos.clear();
        listVeiculoComb.clear();

        String combustivel= listCombustivel.get(indice);

        for(int i =0; i< lista.size(); i++){
            String c = lista.get(i).getCombustivel();
            if(c.equals(combustivel)){
                listVeiculoComb.add(lista.get(i));
                listAnos.add(lista.get(i).getAno()+"");
            }
        }
        ArrayAdapter<String> apt = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, listAnos);
        apt.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinnerAno.setAdapter(apt);
        }catch(Exception e){Log.e("Erro!","Erro no Método Carregar Ano - " + e.getMessage()); }
    }
}
