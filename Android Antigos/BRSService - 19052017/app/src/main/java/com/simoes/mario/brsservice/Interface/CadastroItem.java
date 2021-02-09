package com.simoes.mario.brsservice.Interface;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.simoes.mario.brsservice.Classes.Constantes;
import com.simoes.mario.brsservice.Classes.Filtro;
import com.simoes.mario.brsservice.Classes.ListSeriazeble;
import com.simoes.mario.brsservice.Classes.Oleo;
import com.simoes.mario.brsservice.Firebase.FireFiltro;
import com.simoes.mario.brsservice.Firebase.FireOleo;
import com.simoes.mario.brsservice.R;

import java.util.ArrayList;
import java.util.List;

public class CadastroItem extends AppCompatActivity {

    String operacao, tipoOleo, tipoFiltro, codFiltro, combustivel;
    Filtro filtro;
    Oleo oleo;

    int indMarca = 0, indModelo = 0;

    Object listaObjetos;
    List<Oleo> listaOleos;
    List<Filtro>listaFiltros;

    List<Oleo> listaOleoModelo;
    List<Filtro>listaFiltroModelo;

    List<Oleo> listaOleoModeloMarca;
    List<Filtro>listaFiltroModeloMarca;


    List <String> listAdapMarcas;
    List <String> listAdapModelos;

    TextView txtItemCadTitulo, txtItemCadModelo;
    EditText edtItemCadMarca, edtItemCadModelo, edtItemCadValor, edtItemCadNome;
    Button btnItemCad;

    RadioGroup rgOleos, rgFiltros, rgItens;
    RadioButton rbOleo, rbFiltro, rbMotor, rbCambio, rbfAr, rbfArcab, rbfComb, rbfOleo;

    Spinner spnItemCadMarca, spnItemCadModelo;


    FireFiltro fireFiltro;
    FireOleo fireOleo;

    Intent dados;

    ProgressDialog progressDialog;
    CoordinatorLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_item);

        layout = (CoordinatorLayout) findViewById(R.id.layout_cad_item);
        dados = new Intent();

        listaFiltros = new ArrayList<>();
        listaOleos = new ArrayList<>();

        listaFiltroModelo = new ArrayList<>();
        listaOleoModelo = new ArrayList<>();

        listaFiltroModeloMarca = new ArrayList<>();
        listaOleoModeloMarca = new ArrayList<>();

        listAdapMarcas = new ArrayList<>();
        listAdapModelos = new ArrayList<>();

        rgFiltros = (RadioGroup)findViewById(R.id.rgFiltros);
        rgOleos = (RadioGroup)findViewById(R.id.rgOles);
        rgItens = (RadioGroup)findViewById(R.id.rgItens);

        rbCambio = (RadioButton)findViewById(R.id.rbCambio);
        rbMotor = (RadioButton)findViewById(R.id.rbMotor);

        rbOleo = (RadioButton)findViewById(R.id.rbOleo);
        rbFiltro = (RadioButton)findViewById(R.id.rbFiltro);

        rbfAr = (RadioButton)findViewById(R.id.rbFar);
        rbfArcab = (RadioButton)findViewById(R.id.rbArcab);
        rbfComb = (RadioButton)findViewById(R.id.rbFComb);
        rbfOleo = (RadioButton)findViewById(R.id.rbFoleo);

        rgItens.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                Boolean filtro=false, oleo=false;
                indModelo = 0;
                indMarca = 0;
                switch (checkedId){
                    case R.id.rbOleo:
                        oleo = true;
                        break;
                    case R.id.rbFiltro:
                        filtro = true;
                        break;
                }
                for (int i = 0; i < rgFiltros.getChildCount(); i++) {
                    rgFiltros.getChildAt(i).setEnabled(filtro);

                    if(filtro){
                        rgFiltros.check(rgFiltros.getChildAt(0).getId());
                    }

                }
                for (int i = 0; i < rgOleos.getChildCount(); i++) {
                    rgOleos.getChildAt(i).setEnabled(oleo);

                    if(oleo){
                        rgOleos.check(rgOleos.getChildAt(0).getId());
                    }
                }
                CarregarMarcas();
            }
        });
        rgFiltros.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                CarregarMarcas();
                indModelo = 0;
                indMarca = 0;

            }
        });
        rgOleos.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                CarregarMarcas();
                indModelo = 0;
                indMarca = 0;
            }
        });

        txtItemCadModelo = (TextView)findViewById(R.id.txtItemCadModelo);
        txtItemCadTitulo = (TextView)findViewById(R.id.txtItemCadTitulo);
        edtItemCadMarca = (EditText)findViewById(R.id.edtItemCadMarca);
        edtItemCadModelo = (EditText)findViewById(R.id.edtItemCadModelo);
        edtItemCadNome = (EditText)findViewById(R.id.edtItemCadNome);
        edtItemCadValor = (EditText)findViewById(R.id.edtItemCadValor);
        btnItemCad = (Button)findViewById(R.id.btnCadItem);

        spnItemCadMarca = (Spinner)findViewById(R.id.spnItemCadMarca);
        spnItemCadModelo = (Spinner)findViewById(R.id.spnItemCadModelo);


        spnItemCadMarca.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                try {
                    indMarca = position;
                    edtItemCadMarca.setHint(listAdapMarcas.get(spnItemCadMarca.getSelectedItemPosition()));
                    CarregarModelos();
                }catch (Exception e){Log.e("Cadastro Item","Erro no spnIntemCadMarca, message: "+ e.getMessage());}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spnItemCadModelo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(operacao.equals(Constantes.CAD_ATUALIZAR)){
                    indModelo = position;
                    if(rbFiltro.isChecked()){
                        edtItemCadMarca.setText(listaFiltroModeloMarca.get(position).getMarca());
                        edtItemCadModelo.setText(listaFiltroModeloMarca.get(position).getModelo());
                        edtItemCadNome.setText(listaFiltroModeloMarca.get(position).getModelo());
                        edtItemCadNome.setEnabled(false);
                        edtItemCadValor.setText(listaFiltroModeloMarca.get(position).getValor()+"");
                    }
                    if(rbOleo.isChecked()){
                        edtItemCadNome.setEnabled(true);
                        edtItemCadMarca.setText(listaOleoModeloMarca.get(position).getMarca());
                        edtItemCadModelo.setText(listaOleoModeloMarca.get(position).getModelo());
                        edtItemCadNome.setText(listaOleoModeloMarca.get(position).getNome());
                        edtItemCadValor.setText(listaOleoModeloMarca.get(position).getValor()+"");
                    }
                }else {
                    edtItemCadModelo.setHint(listAdapModelos.get(spnItemCadModelo.getSelectedItemPosition()));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        fireFiltro = new FireFiltro();
        fireOleo = new FireOleo();

        try {
            operacao = getIntent().getStringExtra(Constantes.COD_OPERACAO);
            listaObjetos = getIntent().getSerializableExtra(Constantes.LISTA_OBJETOS);

            if (operacao.equals(Constantes.CAD_FILTRO)) {
                tipoFiltro = getIntent().getStringExtra(Constantes.TIPO_FILTRO);
                listaFiltros.addAll((ArrayList)listaObjetos);
                //     codFiltro = getIntent().getStringExtra(Constantes.COD_FILTRO);
                ExecutarFiltro();
            } else if (operacao.equals(Constantes.CAD_OLEO)) {
                listaOleos.addAll((ArrayList)listaObjetos);
                combustivel = getIntent().getStringExtra(Constantes.COMBUSTIVEL);
                tipoOleo = getIntent().getStringExtra(Constantes.TIPO_OLEO);
                ExecutarOleo();
            }
            if (operacao.equals(Constantes.CAD_ATUALIZAR)){

                ExecutarAtualizar();

            }

        }catch (Exception e){
            Log.e("Erro ", "Erro no método OnCreate, message: "+ e.getMessage());}

        btnItemCad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(operacao.equals(Constantes.CAD_ATUALIZAR)){
                        if(rbFiltro.isChecked()){

                            filtro = new Filtro();
                            filtro = listaFiltroModeloMarca.get(spnItemCadModelo.getSelectedItemPosition());
                            filtro.setMarca(edtItemCadMarca.getText().toString());
                            filtro.setModelo(edtItemCadModelo.getText().toString());
                            filtro.setValor(Double.parseDouble(edtItemCadValor.getText().toString()));

                            fireFiltro.Atualizar(filtro);

                        }
                        if(rbOleo.isChecked()){

                            oleo = new Oleo();
                            oleo = listaOleoModeloMarca.get(spnItemCadModelo.getSelectedItemPosition());
                            oleo.setMarca(edtItemCadMarca.getText().toString());
                            oleo.setModelo(edtItemCadModelo.getText().toString());
                            oleo.setNome(edtItemCadNome.getText().toString());
                            oleo.setValor(Double.parseDouble(edtItemCadValor.getText().toString()));

                            fireOleo.Atualizar(oleo);
                        }
                        Toast.makeText(getBaseContext(),"Atualizado!!",Toast.LENGTH_LONG).show();
                    }
                    if (operacao.equals(Constantes.CAD_FILTRO)) {
                        filtro = new Filtro();
                        filtro.setTipo(tipoFiltro + "");
                        if(spnItemCadModelo.getSelectedItemPosition()==0){
                            filtro.setCodigo(Constantes.GERAR_NOVO_COD);
                        }else {
                            filtro.setCodigo(listaFiltroModelo.get(spnItemCadModelo.getSelectedItemPosition()-1).getCodigo());
                        }
                        filtro.setMarca(edtItemCadMarca.getText().toString() + "");
                        filtro.setModelo(edtItemCadModelo.getText().toString() + "");
                        filtro.setValor(Double.parseDouble(edtItemCadValor.getText().toString()));
                        fireFiltro.SalvarNovo(filtro);
                        Toast.makeText(getBaseContext(), "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
                        setResult(1, dados);
                        finish();

                    }
                    if (operacao.equals(Constantes.CAD_OLEO)) {
                        oleo = new Oleo();
                        oleo.setTipo(tipoOleo + "");
                        oleo.setMarca(edtItemCadMarca.getText().toString() + "");
                        oleo.setModelo(edtItemCadModelo.getText().toString() + "");
                        oleo.setCombustivel(combustivel);
                        oleo.setNome(edtItemCadNome.getText().toString() + "");
                        oleo.setValor(Double.parseDouble(edtItemCadValor.getText().toString()));
                        fireOleo.SalvarNovo(oleo);
                        Toast.makeText(getBaseContext(), "Salvo com Sucesso!", Toast.LENGTH_SHORT).show();
                        setResult(1, dados);
                        finish();
                    }
                }catch (Exception e){
                    Toast.makeText(getBaseContext(),"Verifique os campos, há valores inválidos!!!", Toast.LENGTH_LONG).show();
                    Log.e("Erro ", "Erro no método btnItemCadd, message: "+ e.getMessage());}
            }
        });
    }

    private void ExecutarAtualizar(){
        btnItemCad.setText("Atualizar!!");
        progressDialog = ProgressDialog.show(this,"Obtendo dados","Aguarde...");
        fireFiltro.getReference().addValueEventListener(listenerFiltros);
        fireOleo.getReference().addValueEventListener(listenerOleo);

        rgItens.check(rgItens.getChildAt(0).getId());
        rgOleos.check(rgOleos.getChildAt(0).getId());
    }
    private void CarregarFiltros(){     CarregarMarcas();    }
    private void CarregarOleos(){
        CarregarMarcas();
    }

    ValueEventListener listenerOleo = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            try {
                listaOleos.clear();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.d(" OnDataChange ", child.getValue().toString());
                    Oleo oleo = child.getValue(Oleo.class);
                    listaOleos.add(oleo);
                    progressDialog.dismiss();
                }
                Log.d("OnDataChange Ok", "Tamanho da lista de filtros: "+listaOleos.size());
                //progressDialog.dismiss();
            }catch(Exception e){Log.e("Erro!","Erro no listener Filtros - " + e.getMessage()); }
            CarregarOleos();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener listenerFiltros = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {

            try {
                listaFiltros.clear();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.d(" OnDataChange ", child.getValue().toString());
                    Filtro filtro = child.getValue(Filtro.class);
                    listaFiltros.add(filtro);
                    progressDialog.dismiss();
                }
                Log.d("OnDataChange Ok", "Tamanho da lista de filtros: "+listaFiltros.size());
                //progressDialog.dismiss();
            }catch(Exception e){Log.e("Erro!","Erro no listener Filtros - " + e.getMessage()); }
            CarregarFiltros();
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };



    private void CarregarMarcas(){
        try {
            listAdapMarcas = new ArrayList<>();
            listAdapMarcas.clear();

            if(!operacao.equals(Constantes.CAD_ATUALIZAR)) {
                listAdapMarcas.add("Novo..");
            }
            if(operacao.equals(Constantes.CAD_ATUALIZAR)){
                if(rbFiltro.isChecked()){

                    listaFiltroModelo.clear();
                    String tipoF = "";

                    switch (rgFiltros.getCheckedRadioButtonId()){
                        case R.id.rbFar:
                            tipoF = Constantes.FILTRO_AR;
                            break;
                        case R.id.rbFComb:
                            tipoF = Constantes.FILTRO_COMB;
                            break;
                        case R.id.rbArcab:
                            tipoF = Constantes.FILTRO_AR_CAB;
                            break;
                        case R.id.rbFoleo:
                            tipoF = Constantes.FILTRO_OLEO;
                            break;
                    }

                    for (int i = 0; i < listaFiltros.size(); i++) {

                        String marca = listaFiltros.get(i).getMarca();
                        String tipo = listaFiltros.get(i).getTipo();

                        if(tipo.equals(tipoF)){
                            listaFiltroModelo.add(listaFiltros.get(i));
                            if (listAdapMarcas.contains(marca)) {
                                Log.d("BuscaActivity", "Já contém a marca : " + marca);
                            } else {
                                Log.d("BuscaActivity", "Adicionando a marca: " + marca);
                                listAdapMarcas.add(marca);
                            }
                        }
                    }
                }
                if(rbOleo.isChecked()){
                    listaOleoModelo.clear();
                    String tipoF = "";

                    switch (rgOleos.getCheckedRadioButtonId()){
                        case R.id.rbMotor:
                            tipoF = Constantes.OLEO_MOTOR;
                            break;
                        case R.id.rbCambio:
                            tipoF = Constantes.OLEO_CAMBIO;
                            break;
                    }

                    for (int i = 0; i < listaOleos.size(); i++) {

                        String marca = listaOleos.get(i).getMarca();
                        String tipo = listaOleos.get(i).getTipo();

                        if(tipo.equals(tipoF)){
                            listaOleoModelo.add(listaOleos.get(i));
                            if (listAdapMarcas.contains(marca)) {
                                Log.d("BuscaActivity", "Já contém a marca : " + marca);
                            } else {
                                Log.d("BuscaActivity", "Adicionando a marca: " + marca);
                                listAdapMarcas.add(marca);
                            }
                        }
                    }
                }
            }

            if(operacao.equals(Constantes.CAD_FILTRO)){
                for (int i = 0; i < listaFiltros.size(); i++) {
                    String marca = listaFiltros.get(i).getMarca();
                    if (listAdapMarcas.contains(marca)) {
                        Log.d("BuscaActivity", "Já contém a marca : " + marca);
                    } else {
                        Log.d("BuscaActivity", "Adicionando a marca: " + marca);
                        listAdapMarcas.add(marca);
                    }
                }
            }
            if(operacao.equals(Constantes.CAD_OLEO)){
                for (int i = 0; i < listaFiltros.size(); i++) {
                    String marca = listaFiltros.get(i).getMarca();
                    if (listAdapMarcas.contains(marca)) {
                        Log.d("BuscaActivity", "Já contém a marca : " + marca);
                    } else {
                        Log.d("BuscaActivity", "Adicionando a marca: " + marca);
                        listAdapMarcas.add(marca);
                    }
                }
            }
            ArrayAdapter<String> adapMarcas = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapMarcas);
            adapMarcas.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnItemCadMarca.setAdapter(adapMarcas);

            if(operacao.equals(Constantes.CAD_ATUALIZAR)){
                if(indMarca>0){
                    spnItemCadMarca.setSelection(indMarca);
                }
            }
            CarregarModelos();

        }catch(Exception e){Log.e("Erro!","Erro no Método CarregarMarca - " + e.getMessage()); }

    }
    private void CarregarModelos(){

        try {
            listAdapModelos = new ArrayList<>();
            listAdapModelos.clear();
            listaOleoModeloMarca.clear();
            listaFiltroModeloMarca.clear();

            String marca = listAdapMarcas.get(spnItemCadMarca.getSelectedItemPosition());

            if(!operacao.equals(Constantes.CAD_ATUALIZAR)){
                listAdapModelos.add("Novo..");
            }
            if(operacao.equals(Constantes.CAD_ATUALIZAR)){

                if(rbFiltro.isChecked()){

                    for (int i = 0; i < listaFiltroModelo.size(); i++) {
                        String modelo = listaFiltroModelo.get(i).getModelo();
                        if(marca.equals(listaFiltroModelo.get(i).getMarca())){
                            listaFiltroModeloMarca.add(listaFiltroModelo.get(i));
                            listAdapModelos.add(modelo);

                        }

                    }
                }
                if(rbOleo.isChecked()){
                    for (int i = 0; i < listaOleoModelo.size(); i++) {
                        String modelo = listaOleoModelo.get(i).getModelo();
                        if(marca.equals(listaOleoModelo.get(i).getMarca())){
                            listaOleoModeloMarca.add(listaOleoModelo.get(i));
                            listAdapModelos.add(modelo);
                        }

                    }
                }
            }

            if(operacao.equals(Constantes.CAD_FILTRO)){
                for (int i = 0; i < listaFiltros.size(); i++) {
                    String modelo = listaFiltros.get(i).getModelo();
                    if(marca.equals(listaFiltros.get(i).getMarca())){
                        listAdapModelos.add(modelo);
                        listaFiltroModelo.add(listaFiltros.get(i));
                    }

                }
            }
            if(operacao.equals(Constantes.CAD_OLEO)){
                for (int i = 0; i < listaOleos.size(); i++) {
                    String modelo = listaOleos.get(i).getModelo();
                    if(marca.equals(listaOleos.get(i).getMarca())){
                        listAdapModelos.add(modelo);
                        listaOleoModelo.add(listaOleos.get(i));
                    }

                }
            }
            ArrayAdapter<String> adapModelo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapModelos);
            adapModelo.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnItemCadModelo.setAdapter(adapModelo);

            if(operacao.equals(Constantes.CAD_ATUALIZAR)){
                if(indModelo>0){
                    spnItemCadModelo.setSelection(indModelo);
                }
            }

        }catch(Exception e){Log.e("Erro!","Erro no Método CarregarMarca - " + e.getMessage()); }

    }
    private void ExecutarFiltro(){
        try {

            rgItens.check(R.id.rbFiltro);

            txtItemCadTitulo.setText("Cadastro de Filtro");
            txtItemCadModelo.setText("Modelo");
           // edtItemCadTipo.setText(tipoFiltro);

            Boolean ar=false, arcab=false, oleo=false, comb=false;

            dados.putExtra(Constantes.COD_OPERACAO, operacao);
            switch (tipoFiltro) {
                case Constantes.FILTRO_AR:
                    ar = true;
                    rgFiltros.check(R.id.rbFar);
                    codFiltro = Constantes.FILTRO_AR;
                    dados.putExtra(Constantes.TIPO_FILTRO, Constantes.FILTRO_AR);
                    break;
                case Constantes.FILTRO_AR_CAB:
                    arcab = true;
                    rgFiltros.check(R.id.rbArcab);
                    codFiltro = Constantes.FILTRO_AR_CAB;
                    dados.putExtra(Constantes.TIPO_FILTRO, Constantes.FILTRO_AR_CAB);
                    break;
                 case Constantes.FILTRO_OLEO:
                     oleo = true;
                     rgFiltros.check(R.id.rbFoleo);
                    codFiltro = Constantes.FILTRO_OLEO;
                    dados.putExtra(Constantes.TIPO_FILTRO, Constantes.FILTRO_OLEO);
                    break;
                case Constantes.FILTRO_COMB:
                    comb = true;
                    rgFiltros.check(R.id.rbFComb);
                    codFiltro = Constantes.FILTRO_COMB;
                    dados.putExtra(Constantes.TIPO_FILTRO, Constantes.FILTRO_COMB);
                    break;
            }
            rgFiltros.getChildAt(rgFiltros.indexOfChild(rbfAr)).setEnabled(ar);
            rgFiltros.getChildAt(rgFiltros.indexOfChild(rbfArcab)).setEnabled(arcab);
            rgFiltros.getChildAt(rgFiltros.indexOfChild(rbfComb)).setEnabled(comb);
            rgFiltros.getChildAt(rgFiltros.indexOfChild(rbfOleo)).setEnabled(oleo);

            CarregarMarcas();

        }catch (Exception e){Log.e("Erro ", "Erro no método Executar Filtro, message: "+ e.getMessage());}
    }

    private void ExecutarOleo(){
      try {
          rgItens.check(R.id.rbOleo);

          txtItemCadTitulo.setText("Cadastro de Óleo");
          txtItemCadModelo.setText("Viscosidade");
        //  edtItemCadTipo.setText(tipoOleo);

          boolean motor = false, cambio = false;

          switch (tipoOleo) {
              case Constantes.OLEO_MOTOR:
                  motor = true;
                  rgOleos.check(R.id.rbMotor);
                  dados.putExtra(Constantes.TIPO_OLEO, Constantes.OLEO_MOTOR);
                  break;
              case Constantes.OLEO_CAMBIO:
                  cambio = true;
                  rgOleos.check(R.id.rbCambio);
                  dados.putExtra(Constantes.TIPO_OLEO, Constantes.OLEO_CAMBIO);
                  break;
          }
          rgOleos.getChildAt(rgOleos.indexOfChild(rbMotor)).setEnabled(motor);
          rgOleos.getChildAt(rgOleos.indexOfChild(rbCambio)).setEnabled(cambio);

      }catch (Exception e){Log.e("Erro ", "Erro no método ExecutarOleo, message: "+ e.getMessage());}
    }
}
