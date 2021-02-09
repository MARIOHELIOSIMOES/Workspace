package com.simoes.mario.brsservice.Interface;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.simoes.mario.brsservice.Classes.Filtro;
import com.simoes.mario.brsservice.Classes.ListSeriazeble;
import com.simoes.mario.brsservice.Classes.Oleo;
import com.simoes.mario.brsservice.Classes.Veiculo;
import com.simoes.mario.brsservice.Firebase.FireFiltro;
import com.simoes.mario.brsservice.Firebase.FireOleo;
import com.simoes.mario.brsservice.Firebase.FireVeiculo;
import com.simoes.mario.brsservice.R;
import com.simoes.mario.brsservice.Classes.Constantes;

import java.util.ArrayList;
import java.util.List;

public class CadastroVeiculos extends AppCompatActivity{

    Spinner spnTipo, spnMarca, spnCombustivel, spnOleoMotor, spnOleoCambio, spnOleoDif, spnFiltroOleo, spnFiltroAr, spnFiltroArCab, spnFiltroDH, spnFiltroCom;
    EditText edtModelo, edtAno, edtLitragem;

    DatabaseReference referenceMarcas;


    private boolean isfirst = true;
    private static final int requestCodOleoMotor = 1;
    private static final int requestCodOleoCambio = 2;
    private static final int requestCodFiltroOleo =4;
    private static final int requestCodFiltroAr = 5;
    private static final int requestCodFiltroArCab = 6;
    private static final int requestCodFiltroComb = 7;

    private int spnMotor = 0, spnCambio = 0 , spnFoleo = 0, spnfAr = 0 , spnFArca = 0 , spnfComb = 0, spnIndMarca = 0;

    ListSeriazeble listSeriazeble;

    Context context;

    String [] tipos = new  String[] {Constantes.TIPO_CARRO, Constantes.TIPO_CAMINHAO, Constantes.TIPO_MOTO };
    String [] combustiveis = new  String[] {Constantes.COMB_FLEX, Constantes.COMB_GAS, Constantes.COMB_ETANOL, Constantes.COMB_DIESEL };

    int n_tipos = 3, n_combustiveis = 4;

    final String opcaoNovo = "Cadastrar Novo";

    String op="";

    FireVeiculo fireVeiculo;
    FireOleo fireOleo;
    FireFiltro fireFiltro;

    List<String> listAdapMarcas;
    List<String> listAdapMarcasTotal;
    List<String> listAdapOleoMotor;
    List<String> listAdapOleoCambio;

    List<String> listAdapFiltroOleo;
    List<String> listAdapFiltroCom;
    List<String> listAdapFiltroAr;
    List<String> listAdapFiltroArCab;

    List<Oleo> listOleoMotor;
    List<Oleo> listOleoCambio;

    List<Filtro> listFiltroOleo;
    List<Filtro>listFiltroComb;
    List<Filtro>listFiltroAr;
    List<Filtro>listFiltroArCab;

    List<Oleo> listOleosTotal;
    List<Filtro>listFiltrosTotal;
    List<Veiculo> listVeiculosTotal;

    Veiculo veiculo, v1;
    ProgressDialog progressDialog;

    CoordinatorLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_veiculos);
        context = this;

        spnTipo = (Spinner)findViewById(R.id.spnCadTipoVeiculo);
        spnMarca = (Spinner)findViewById(R.id.spnCadMarcaVeiculo);
        spnCombustivel = (Spinner)findViewById(R.id.spnCadCombustivel);
        spnOleoMotor = (Spinner)findViewById(R.id.spnCadOleoMotor);
        spnOleoCambio = (Spinner)findViewById(R.id.spnCadOleoCambio);
//        spnOleoDif = (Spinner)findViewById(R.id.spnCadOleoDiferencial);
        spnFiltroAr = (Spinner)findViewById(R.id.spnCadfiltroAr);
        spnFiltroArCab = (Spinner)findViewById(R.id.spnCadfiltroArCab);
        spnFiltroCom = (Spinner)findViewById(R.id.spnCadFiltroComb);
  //      spnFiltroDH = (Spinner)findViewById(R.id.spnCadfiltroDH);
        spnFiltroOleo = (Spinner)findViewById(R.id.spnCadFiltroOleo);

        edtAno = (EditText)findViewById(R.id.edtCadAnoVeiculo);
        edtLitragem = (EditText)findViewById(R.id.edtCadListragem);
        edtModelo = (EditText)findViewById(R.id.edtCadModeloVeiculo);

        listSeriazeble = new ListSeriazeble();

        fireFiltro = new FireFiltro();
        fireOleo = new FireOleo();
        fireVeiculo = new FireVeiculo();

        listAdapMarcas = new ArrayList<String>();
        listAdapMarcasTotal = new ArrayList<String>();
        listAdapFiltroAr = new ArrayList<String>();
        listAdapFiltroArCab = new ArrayList<String>();
        listAdapFiltroCom = new ArrayList<String>();
 //       listAdapFiltroDH = new ArrayList<String>();
        listAdapFiltroOleo  = new ArrayList<String>();
        listAdapOleoCambio  = new ArrayList<String>();
 //       listAdapOleoDif  = new ArrayList<String>();
        listAdapOleoMotor = new ArrayList<String>();

        listFiltroOleo = new ArrayList<Filtro>();
        listFiltroAr = new ArrayList<Filtro>();
        listFiltroArCab= new ArrayList<Filtro>();
        listFiltroComb = new ArrayList<Filtro>();
  //      listFiltroDH = new ArrayList<Filtro>();

        listOleoMotor = new ArrayList<Oleo>();
        listOleoCambio = new ArrayList<Oleo>();
 //       listOleoDif = new ArrayList<Oleo>();

        listVeiculosTotal = new ArrayList<Veiculo>();
        listFiltrosTotal = new ArrayList<Filtro>();
        listOleosTotal = new ArrayList<Oleo>();

        progressDialog = ProgressDialog.show(this,"Obtendo dados","Aguarde...");

        fireVeiculo.getReference().addValueEventListener(listenerVeiculo);
        fireOleo.getReference().addValueEventListener(listenerOleos);
        fireFiltro.getReference().addValueEventListener(listenerFiltros);

        referenceMarcas = Constantes.referenceMarcas;
        referenceMarcas.addValueEventListener(listenerMarcas);


        spnMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
             try{
                spnIndMarca = position;
                if(position == listAdapMarcas.size()-1){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Informe a Marca");
                    final EditText input = new EditText(context);
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
                    builder.setView(input);

                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String novaMarca = input.getText().toString();
                            String key = referenceMarcas.push().getKey();
                            referenceMarcas.child(key).setValue(novaMarca.toUpperCase());
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    builder.show();

                }
             }catch (Exception e){Log.e("SpnMarcas", "Erro no on Item change, message:"+ e.getMessage()); }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnOleoMotor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
              try {
                  spnMotor = position;
                  if (position == (listOleoMotor.size() + 1) ) {
                      CadastrarItem(Constantes.CAD_OLEO, Constantes.OLEO_MOTOR, null);
                  }
              }catch (Exception e){Log.e("Cadastro Veiculo Erro","spnOleo Motor, message: " + e.getMessage());}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnOleoCambio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               try {
                   spnCambio = position;
                   if (position == (listOleoCambio.size() + 1)) {
                       CadastrarItem(Constantes.CAD_OLEO, Constantes.OLEO_CAMBIO, null);
                   }
               }catch (Exception e){Log.e("Cadastro Veiculo Erro","spnOleoCambio, message: " + e.getMessage());}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnFiltroCom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    spnfComb = position;
                    if (position == (listFiltroComb.size() + 1)) {
                        CadastrarItem(Constantes.CAD_FILTRO, Constantes.FILTRO_COMB,null);// listFiltroComb.get(position - 1).getCodigo());
                    }
                }catch (Exception e){Log.e("Cadastro Veiculo Erro","spnFiltroCom, message: " + e.getMessage());}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnFiltroOleo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               try {
                   spnFoleo = position;
                   if (position == (listFiltroOleo.size() + 1)) {
                       CadastrarItem(Constantes.CAD_FILTRO, Constantes.FILTRO_OLEO, null);//listFiltroOleo.get(position).getCodigo());
                   }
               }catch (Exception e){Log.e("Cadastro Veiculo Erro","spnFiltroOleo, message: " + e.getMessage());}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnFiltroAr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               try {
                   spnfAr = position;
                   if (position == (listFiltroAr.size() + 1)) {
                       CadastrarItem(Constantes.CAD_FILTRO, Constantes.FILTRO_AR, null);// listFiltroAr.get(position - 1).getCodigo());
                   }
               }catch (Exception e){Log.e("Cadastro Veiculo Erro","spnFiltroAr, message: " + e.getMessage());}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnFiltroArCab.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               try {
                   spnFArca = position;
                   if (position == (listFiltroArCab.size() + 1)) {
                       CadastrarItem(Constantes.CAD_FILTRO, Constantes.FILTRO_AR_CAB, null);//listFiltroArCab.get(position - 1).getCodigo());
                   }
               }catch (Exception e){Log.e("Cadastro Veiculo Erro","spnFiltroArCab, message: " + e.getMessage());}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnCombustivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(isfirst) {
                   isfirst = false;
                    return;
                }
                carregarOleos();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adtTipos = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tipos);
        adtTipos.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnTipo.setAdapter(adtTipos);
        ArrayAdapter<String> adtComb = new ArrayAdapter<String>(getBaseContext(), android.R.layout.simple_spinner_item, combustiveis);
        adtComb.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnCombustivel.setAdapter(adtComb);

        try {
            op = getIntent().getStringExtra(Constantes.COD_OPERACAO)+"";
            veiculo = new Veiculo();
            v1 = new Veiculo();
            v1 = (Veiculo) getIntent().getSerializableExtra(Constantes.VEICULO);

            edtAno.setText(v1.getAno()+"");
            edtModelo.setText(v1.getModelo()+"");
            edtLitragem.setText(v1.getLitragem()+"");


            for (int j = 0; j < n_tipos; j++) {
                if(v1.getTipo().equals(tipos[j])){
                    spnTipo.setSelection(j);
                }
            }
            for(int j = 0; j< n_combustiveis; j++){
                if(v1.getCombustivel().equals(combustiveis[j])){
                    spnCombustivel.setSelection(j);
                }
            }

            //spnTipo.setSelection();
            /*
            veiculo1.setTipo(tipos[spnTipo.getSelectedItemPosition()]);
            veiculo1.setMarca(listAdapMarcas.get(spnMarca.getSelectedItemPosition()));
            veiculo1.setCombustivel(combustiveis[spnCombustivel.getSelectedItemPosition()]);
            veiculo1.setOleoMotor(listOleoMotor.get(spnOleoMotor.getSelectedItemPosition()-1).getModelo());
            veiculo1.setOleoCambio(listOleoCambio.get(spnOleoCambio.getSelectedItemPosition()-1).getModelo());
            veiculo1.setCodfOleo(listFiltroOleo.get(spnFiltroOleo.getSelectedItemPosition()-1).getCodigo());
            veiculo1.setCodfAr(listFiltroAr.get(spnFiltroAr.getSelectedItemPosition()-1).getCodigo());
            veiculo1.setCodfArCab(listFiltroArCab.get(spnFiltroArCab.getSelectedItemPosition()-1).getCodigo());
            veiculo1.setCodfComb(listFiltroComb.get(spnFiltroCom.getSelectedItemPosition()-1).getCodigo());
            */


        }catch (Exception e){
            Log.e("CadastroVeiculo","getIntent veiculo onCreate try. ");
        }



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSalvarVeiculo);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    Veiculo veiculo1 = new Veiculo();

                    veiculo1.setTipo(tipos[spnTipo.getSelectedItemPosition()]+"");
                    veiculo1.setMarca(listAdapMarcas.get(spnMarca.getSelectedItemPosition())+"");
                    veiculo1.setModelo(edtModelo.getText().toString()+"");
                    veiculo1.setAno(Integer.parseInt(edtAno.getText().toString()));
                    veiculo1.setLitragem(Double.parseDouble(edtLitragem.getText().toString()));
                    veiculo1.setCombustivel(combustiveis[spnCombustivel.getSelectedItemPosition()]);
                    veiculo1.setOleoMotor(listOleoMotor.get(spnOleoMotor.getSelectedItemPosition()-1).getModelo()+"");
                    veiculo1.setOleoCambio(listOleoCambio.get(spnOleoCambio.getSelectedItemPosition()-1).getModelo()+"");
                    veiculo1.setCodfOleo(listFiltroOleo.get(spnFiltroOleo.getSelectedItemPosition()-1).getCodigo()+"");
                    veiculo1.setCodfAr(listFiltroAr.get(spnFiltroAr.getSelectedItemPosition()-1).getCodigo()+"");
                    veiculo1.setCodfArCab(listFiltroArCab.get(spnFiltroArCab.getSelectedItemPosition()-1).getCodigo()+"");
                    veiculo1.setCodfComb(listFiltroComb.get(spnFiltroCom.getSelectedItemPosition()-1).getCodigo()+"");


                    if(op.equals(Constantes.COD_ATUALIZAR_VEICULO)){
                        veiculo1.setId(v1.getId());
                        fireVeiculo.Atualizar(veiculo1);
                        view = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutCadVeiculos);
                        Snackbar.make(view, "Atualizado com sucesso!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }else{
                        fireVeiculo.SalvarNovo(veiculo1);
                        view = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutCadVeiculos);
                        Snackbar.make(view, "Salvo com sucesso!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
                    }
                }catch (Exception e){
                    Toast.makeText(getBaseContext(),"Verifique os Campos e tente novamente!!!",Toast.LENGTH_LONG).show();
                    Log.e("ERRO", "Erro no método FAB onClick, message: " + e.getMessage());}
            }
        });
    }
    ValueEventListener listenerVeiculo = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                listVeiculosTotal.clear();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.d(" OnDataChange ", child.getValue().toString());
                    veiculo = child.getValue(Veiculo.class);
                    listVeiculosTotal.add(veiculo);
                }
                Log.d("listener Veiculo Ok", "Tamanho da lista de veículos: "+listVeiculosTotal.size());
                progressDialog.dismiss();
            }catch(Exception e){
                Log.d("Erro!","Erro no listener Veiculos, message: " + e.getMessage());
            }
            carregarMarca();
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener listenerOleos = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                listOleosTotal.clear();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.d(" OnDataChange ", child.getValue().toString());
                    Oleo oleo = child.getValue(Oleo.class);
                    listOleosTotal.add(oleo);

                    progressDialog.dismiss();
                }
//olhar aqui
                Log.d("Listener Oleos Ok", "Tamanho da lista de oleos: "+listOleosTotal.size());

            }catch(Exception e){Log.e("Erro!","Erro no listener Oleos - " + e.getMessage()); }
            carregarOleos();
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener listenerFiltros = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                listFiltrosTotal.clear();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.d(" OnDataChange ", child.getValue().toString());
                    Filtro filtro = child.getValue(Filtro.class);
                    listFiltrosTotal.add(filtro);
                }
                Log.d("OnDataChange Ok", "Tamanho da lista de filtros: "+listFiltrosTotal.size());
                progressDialog.dismiss();
            }catch(Exception e){Log.e("Erro!","Erro no listener Filtros - " + e.getMessage()); }
            carregarFiltros();
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener listenerMarcas = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            try {
                listAdapMarcasTotal.clear();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.d(" listener Marcas ", child.getValue().toString());
                    String novaMarca = child.getValue().toString();
                    listAdapMarcasTotal.add(novaMarca);
                }
                Log.d("OnDataChange Ok", "Tamanho da lista de filtros: "+listFiltrosTotal.size());
                progressDialog.dismiss();
            }catch(Exception e){Log.e("Erro!","Erro no listener Filtros - " + e.getMessage()); }
            carregarMarca();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    private void carregarMarca(){
        try {
            listAdapMarcas.clear();
            listAdapMarcas.add("Selecione..");
            for (int i = 0; i < listAdapMarcasTotal.size(); i++) {
                String marca = listAdapMarcasTotal.get(i);
                if (listAdapMarcas.contains(marca)) {
                    Log.d("BuscaActivity", "Já contém a marca : " + marca);
                } else {
                    Log.d("BuscaActivity", "Adicionando a marca: " + marca);
                    listAdapMarcas.add(marca);
                }
            }
            listAdapMarcas.add(opcaoNovo);
            ArrayAdapter<String> adapMarcas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapMarcas);
            adapMarcas.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnMarca.setAdapter(adapMarcas);
            spnMarca.setSelection(spnIndMarca);

            for(int j =0; j< listAdapMarcas.size(); j++) {
                if (v1.getMarca().equals(listAdapMarcas.get(j))) {
                    spnMarca.setSelection(j);
                }
            }


        }catch(Exception e){Log.e("Erro!","Erro no Método CarregarMarca - " + e.getMessage()); }
    }
    private void carregarFiltros(){ // terminar de atualizar todos os valores
        try {
            listAdapFiltroAr.clear();
            listAdapFiltroOleo.clear();
            listAdapFiltroArCab.clear();
            listAdapFiltroCom.clear();

            listAdapFiltroAr.add("Selecione..");
            listAdapFiltroOleo.add("Selecione..");
            listAdapFiltroArCab.add("Selecione..");
            listAdapFiltroCom.add("Selecione..");

            listFiltroOleo.clear();
            listFiltroComb.clear();
            listFiltroArCab.clear();
            listFiltroAr.clear();

            for (int i = 0; i < listFiltrosTotal.size(); i++) {
                    String tipo = listFiltrosTotal.get(i).getTipo();
                    Log.d("AtualizarLists", "Adicinando o filtro com o codigo:  " + listFiltrosTotal.get(i).getCodigo());
                    switch (tipo) {
                        case Constantes.FILTRO_AR:
                            listFiltroAr.add(listFiltrosTotal.get(i));
                            listAdapFiltroAr.add(listFiltrosTotal.get(i).getModelo() + " - " + listFiltrosTotal.get(i).getMarca());
                            Log.d("AtualizarLists", "Adicinando o filtro de ar tipo:  " + tipo);
                            break;
                        case Constantes.FILTRO_AR_CAB:
                            listFiltroArCab.add(listFiltrosTotal.get(i));
                            listAdapFiltroArCab.add(listFiltrosTotal.get(i).getModelo() + " - " + listFiltrosTotal.get(i).getMarca());
                            Log.d("AtualizarLists", "Adicinando o filtro de ar cabine tipo:  " + tipo);
                            break;
                        case Constantes.FILTRO_COMB:
                            listFiltroComb.add(listFiltrosTotal.get(i));
                            listAdapFiltroCom.add(listFiltrosTotal.get(i).getModelo() + " - " + listFiltrosTotal.get(i).getMarca());
                            Log.d("AtualizarLists", "Adicinando o filtro de combustível tipo:  " + tipo);
                            break;
                        case Constantes.FILTRO_OLEO:
                            listFiltroOleo.add(listFiltrosTotal.get(i));
                            listAdapFiltroOleo.add(listFiltrosTotal.get(i).getModelo() + " - " + listFiltrosTotal.get(i).getMarca());
                            Log.d("AtualizarLists", "Adicinando o filtro oleo tipo:  " + tipo);
                            break;
                        default:
                            Log.e("Activity Cadastro", "Valor inválido no Switch Filtros: " + tipo);
                    }
            }
            listAdapFiltroAr.add(opcaoNovo);
            listAdapFiltroArCab.add(opcaoNovo);
            listAdapFiltroCom.add(opcaoNovo);
     //       listAdapFiltroDH.add(opcaoNovo);
            listAdapFiltroOleo.add(opcaoNovo);

            ArrayAdapter<String> adapFiltroOleo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapFiltroOleo);
            adapFiltroOleo.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnFiltroOleo.setAdapter(adapFiltroOleo);
            //spnFiltroOleo.setSelection(listAdapFiltroOleo.size()-1);


            ArrayAdapter<String> adapFiltroCom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapFiltroCom);
            adapFiltroCom.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnFiltroCom.setAdapter(adapFiltroCom);
            //spnFiltroCom.setSelection(listAdapFiltroCom.size()-1);

            ArrayAdapter<String> adapFiltroAr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapFiltroAr);
            adapFiltroAr.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnFiltroAr.setAdapter(adapFiltroAr);
            //spnFiltroAr.setSelection(listAdapFiltroAr.size()-2);

            ArrayAdapter<String> adapFiltroArCab = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapFiltroArCab);
            adapFiltroArCab.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnFiltroArCab.setAdapter(adapFiltroArCab);
            //spnFiltroArCab.setSelection(listAdapFiltroArCab.size());

            spnFiltroAr.setSelection(spnfAr);
            spnFiltroArCab.setSelection(spnFArca);
            spnFiltroCom.setSelection(spnfComb);
            spnFiltroOleo.setSelection(spnFoleo);

        }catch(Exception e){Log.e("Erro!","Erro no Método Carregar Filtros - " + e.getMessage()); }
    }
    private void carregarOleos(){
        try {
            listOleoMotor.clear();
            listOleoCambio.clear();

            listAdapOleoCambio.clear();
            listAdapOleoCambio.add("Selecione..");
            listAdapOleoMotor.clear();
            listAdapOleoMotor.add("Selecione..");

            for (int i = 0; i < listOleosTotal.size(); i++) {
                String tipo = listOleosTotal.get(i).getTipo();
                if (tipo.equals(Constantes.OLEO_CAMBIO)) {
                    listOleoCambio.add(listOleosTotal.get(i));
                    listAdapOleoCambio.add(listOleosTotal.get(i).getMarca()+" - " + listOleosTotal.get(i).getModelo());
                    Log.d("AtualizarLists", "Adicinando oleo de cambio tipo:  " + tipo);
                }
                if (tipo.equals(Constantes.OLEO_MOTOR)) {
                    int spnCom = spnCombustivel.getSelectedItemPosition();
                    if(listOleosTotal.get(i).getCombustivel().equals(combustiveis[spnCom])){
                        listOleoMotor.add(listOleosTotal.get(i));
                        listAdapOleoMotor.add(listOleosTotal.get(i).getMarca()+" - " + listOleosTotal.get(i).getModelo());
                        Log.d("AtualizarLists", "Adicinando oleo de motor tipo:  " + tipo);
                    }
                }
            }
            listAdapOleoCambio.add(opcaoNovo);
     //       listAdapOleoDif.add(opcaoNovo);
            listAdapOleoMotor.add(opcaoNovo);

            ArrayAdapter<String> adapOleoMotor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapOleoMotor);
            adapOleoMotor.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnOleoMotor.setAdapter(adapOleoMotor);
      //      spnOleoMotor.setSelection(listAdapOleoMotor.size()-1);

            ArrayAdapter<String> adapOleoCambio = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapOleoCambio);
            adapOleoCambio.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnOleoCambio.setAdapter(adapOleoCambio);
        //    spnOleoCambio.setSelection(listAdapOleoCambio.size()-1);
            spnOleoMotor.setSelection(spnMotor); //Verificar se está selecionando o item correto.
            spnOleoCambio.setSelection(spnCambio);

        }catch (Exception e){
            Log.e("Cadastrar Veiculo","Erro no método carregar óleo, message: "+ e.getMessage());
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode){
            case requestCodFiltroAr:
                if (requestCode == RESULT_OK){


                }
                break;
            case requestCodFiltroArCab:
                if (requestCode == RESULT_OK){


                }
                break;
            case requestCodFiltroComb:
                if (requestCode == RESULT_OK){


                }
                break;
            case requestCodFiltroOleo:
                if (requestCode == RESULT_OK){


                }
                break;
            case requestCodOleoCambio:
                if (requestCode == RESULT_OK){


                }
                break;
            case requestCodOleoMotor:
                if (requestCode == RESULT_OK){


                }
                break;
        }

    }
    private void CadastrarItem(String operacao, String tipoItem, String filtroCod){
       try {
           int requestCod = 0;
           Intent i = new Intent(getBaseContext(), CadastroItem.class);
           i.putExtra(Constantes.COD_OPERACAO, operacao);


           switch (operacao) {
               case Constantes.CAD_FILTRO:// PASSAR O CODFILTRO
                   i.putExtra(Constantes.COD_FILTRO, Constantes.COD_FILTRO);
                   switch (tipoItem) {
                       case Constantes.FILTRO_AR:
                           i.putExtra(Constantes.TIPO_FILTRO, Constantes.FILTRO_AR);
                           listSeriazeble.addAll(listFiltroAr);
                           requestCod = requestCodFiltroAr;
                           break;
                       case Constantes.FILTRO_AR_CAB:
                           i.putExtra(Constantes.TIPO_FILTRO, Constantes.FILTRO_AR_CAB);
                           listSeriazeble.addAll(listFiltroArCab);
                           requestCod = requestCodFiltroArCab;
                           break;
                       case Constantes.FILTRO_OLEO:
                           requestCod = requestCodFiltroOleo;
                           listSeriazeble.addAll(listFiltroOleo);
                           i.putExtra(Constantes.TIPO_FILTRO, Constantes.FILTRO_OLEO);
                           break;
                       case Constantes.FILTRO_COMB:
                           requestCod = requestCodFiltroComb;
                           listSeriazeble.addAll(listFiltroComb);
                           i.putExtra(Constantes.TIPO_FILTRO, Constantes.FILTRO_COMB);
                           break;
                   }
                   break;
               case Constantes.CAD_OLEO:
                   i.putExtra(Constantes.COMBUSTIVEL, combustiveis[spnCombustivel.getSelectedItemPosition()]);
                   switch (tipoItem) {
                       case Constantes.OLEO_MOTOR:
                           requestCod = requestCodOleoMotor;
                           listSeriazeble.addAll(listOleoMotor);
                           i.putExtra(Constantes.TIPO_OLEO, Constantes.OLEO_MOTOR);
                           break;
                       case Constantes.OLEO_CAMBIO:
                           requestCod = requestCodOleoCambio;
                           listSeriazeble.addAll(listOleoCambio);
                           i.putExtra(Constantes.TIPO_OLEO, Constantes.OLEO_CAMBIO);
                           break;

                   }
                   break;
           }
           i.putExtra(Constantes.LISTA_OBJETOS, listSeriazeble);
           startActivityForResult(i, requestCod);
       }catch(Exception e){Log.e("Erro!","Erro no Método Cadastrar Item- " + e.getMessage()); }
    }
}
