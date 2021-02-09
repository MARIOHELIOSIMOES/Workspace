package com.simoes.mario.brsservice.Interface;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.simoes.mario.brsservice.Classes.Constantes;
import com.simoes.mario.brsservice.Classes.Filtro;
import com.simoes.mario.brsservice.Classes.Item;
import com.simoes.mario.brsservice.Classes.Oleo;
import com.simoes.mario.brsservice.Classes.Veiculo;
import com.simoes.mario.brsservice.Firebase.FireFiltro;
import com.simoes.mario.brsservice.Firebase.FireOleo;
import com.simoes.mario.brsservice.Firebase.FireVeiculo;
import com.simoes.mario.brsservice.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Activity_Orcamento extends AppCompatActivity implements TextWatcher, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    TextView txtTipoVeiculo, txtMarcaVeiculo, txtModeloVeiculo, txtAnoVeiculo, txtCombustivel, txtTotalOrcamento;
    TextView txtLitragem, txtOleoMotorVeiculo;
    Handler handler;
    double totalOrcamento, totalAvulso;

    LinearLayout layoutChecklist;

    FloatingActionButton fabAdicionar;

    List<Item> itemsAvulsos;

    ////////////////////Checklist///////////////////////////////////
    // 1 ) Óleo Motor
    CheckBox chkOleoMotor;
    Spinner spnOleoMotor;
    TextView txtModeloOleoMotor, txtPrecoOleoMotor;
    EditText edtQtdeOleoMotor;

    // 2 ) Óleo Cambio
    CheckBox chkOleoCambio;
    Spinner spnOleoCambio;
    TextView txtModeloOleoCambio, txtPrecoOleoCambio;
    EditText edtQtdeOleoCambio;
    /*
        // 3 ) Óleo Diferencial
        CheckBox chkOleoDif; Spinner spnOleoDif;
        TextView txtModeloOleoDif, txtPrecoOleoDif;
        EditText edtQtdeOleoDif;
    */
    // 4 ) Filtro Oleo
    CheckBox chkFiltroOleo;
    Spinner spnFiltroOleo;
    TextView txtModeloFiltroOleo, txtPrecoFiltroOleo;
    EditText edtQtdeFiltroOleo;

    // 5 ) Filtro Combustível
    CheckBox chkFiltroComb;
    Spinner spnFiltroComb;
    TextView txtModeloFiltroComb, txtPrecoFiltroComb;
    EditText edtQtdeFiltroComb;

    // 6 ) Filtro Ar
    CheckBox chkFiltroAr;
    Spinner spnFiltroAr;
    TextView txtModeloFiltroAr, txtPrecoFiltroAr;
    EditText edtQtdeFiltroAr;

    // 7 ) Filtro Ar Cabine
    CheckBox chkFiltroArCab;
    Spinner spnFiltroArCab;
    TextView txtModeloFiltroArCab, txtPrecoFiltroArCab;
    EditText edtQtdeFiltroArCab;
/*
    // 8 ) Filtro Direção Hidráulica
    CheckBox chkFiltroDH; Spinner spnFiltroDH;
    TextView txtModeloFiltroDH, txtPrecoFiltroDH;
    EditText edtQtdeFiltroDH;
*/


    FireVeiculo fireVeiculo;
    FireOleo fireOleo;
    FireFiltro fireFiltro;


    List<String> listAdapOleoMotor;
    //    List<String> listAdapOleoDif;
    List<String> listAdapOleoCambio;
    List<String> listAdapFiltroOleo;
    List<String> listAdapFiltroCom;
    List<String> listAdapFiltroAr;
    List<String> listAdapFiltroArCab;
//    List<String> listAdapFiltroDH;

    List<Oleo> listOleoMotor;
    //    List<Oleo> listOleoDif;
    List<Oleo> listOleoCambio;
    List<Filtro> listFiltroOleo;
    List<Filtro> listFiltroComb;
    List<Filtro> listFiltroAr;
    List<Filtro> listFiltroArCab;
//    List<Filtro>listFiltroDH;

    List<Oleo> listOleosTotal;
    List<Filtro> listFiltrosTotal;

    Veiculo veiculo;
    Oleo oleo;
    Filtro filtro;
    String key_veiculo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orcamento);
        veiculo = (Veiculo) getIntent().getSerializableExtra(Constantes.VEICULO); // conferir aqui...
        totalOrcamento = 0;
        handler = new Handler();


        itemsAvulsos = new ArrayList<Item>();
        layoutChecklist = (LinearLayout) findViewById(R.id.layoutChecklist);

        txtTotalOrcamento = (TextView) findViewById(R.id.txtTotalOrcamento);

        txtTipoVeiculo = (TextView) findViewById(R.id.txtTipoVeiculo);
        txtMarcaVeiculo = (TextView) findViewById(R.id.txtMarcaVeiculo);
        txtModeloVeiculo = (TextView) findViewById(R.id.txtModeloVeiculo);
        txtAnoVeiculo = (TextView) findViewById(R.id.txtAnoVeiculo);
        txtCombustivel = (TextView) findViewById(R.id.txtCombustivel);
        txtLitragem = (TextView) findViewById(R.id.txtListragem);
        txtOleoMotorVeiculo = (TextView) findViewById(R.id.txtOleoMotorVeiculo);

        // 1 ) Óleo Motor
        chkOleoMotor = (CheckBox) findViewById(R.id.chkOleoMotor);
        spnOleoMotor = (Spinner) findViewById(R.id.spnOleoMotor);
        txtModeloOleoMotor = (TextView) findViewById(R.id.txtModeloOleoMotor);
        txtPrecoOleoMotor = (EditText) findViewById(R.id.txtPrecoOleoMotor);
        edtQtdeOleoMotor = (EditText) findViewById(R.id.edtQtdeOleoMotor);

        // 2 ) Óleo Cambio
        chkOleoCambio = (CheckBox) findViewById(R.id.chkOleoCambio);
        spnOleoCambio = (Spinner) findViewById(R.id.spnOleoCambio);
        txtModeloOleoCambio = (TextView) findViewById(R.id.txtModeloOleoCambio);
        txtPrecoOleoCambio = (EditText) findViewById(R.id.txtPrecoOleoCambio);
        edtQtdeOleoCambio = (EditText) findViewById(R.id.edtQtdeOleoCambio);
/*
        // 3 ) Óleo Diferencial
        chkOleoDif = (CheckBox)findViewById(R.id.chkOleoDif);
        spnOleoDif = (Spinner)findViewById(R.id.spnOleoDif);
        txtModeloOleoDif = (TextView)findViewById(R.id.txtModeloOleoDif);
        txtPrecoOleoDif = (EditText)findViewById(R.id.txtPrecoOleoDif);
        edtQtdeOleoDif = (EditText) findViewById(R.id.edtQtdeOleoDif);
*/
        // 4 ) Filtro Oleo
        chkFiltroOleo = (CheckBox) findViewById(R.id.chkFiltroOleo);
        spnFiltroOleo = (Spinner) findViewById(R.id.spnFiltroOleo);
        txtModeloFiltroOleo = (TextView) findViewById(R.id.txtModeloFiltroOleo);
        txtPrecoFiltroOleo = (EditText) findViewById(R.id.txtPrecoFiltroOleo);
        edtQtdeFiltroOleo = (EditText) findViewById(R.id.edtQtdeFiltroOleo);

        // 5 ) Filtro Combustível
        chkFiltroComb = (CheckBox) findViewById(R.id.chkFiltroComb);
        spnFiltroComb = (Spinner) findViewById(R.id.spnFiltroComb);
        txtModeloFiltroComb = (TextView) findViewById(R.id.txtModeloFiltroComb);
        txtPrecoFiltroComb = (EditText) findViewById(R.id.txtPrecoFiltroComb);
        edtQtdeFiltroComb = (EditText) findViewById(R.id.edtQtdeFiltroComb);

        // 6 ) Filtro Ar
        chkFiltroAr = (CheckBox) findViewById(R.id.chkFiltroAr);
        spnFiltroAr = (Spinner) findViewById(R.id.spnFiltroAr);
        txtModeloFiltroAr = (TextView) findViewById(R.id.txtModeloFiltroAr);
        txtPrecoFiltroAr = (EditText) findViewById(R.id.txtPrecoFiltroAr);
        edtQtdeFiltroAr = (EditText) findViewById(R.id.edtQtdeFiltroAr);


        // 7 ) Filtro Ar Cabine
        chkFiltroArCab = (CheckBox) findViewById(R.id.chkFiltroArCab);
        spnFiltroArCab = (Spinner) findViewById(R.id.spnFiltroArCab);
        txtModeloFiltroArCab = (TextView) findViewById(R.id.txtModeloFiltroArCab);
        txtPrecoFiltroArCab = (EditText) findViewById(R.id.txtPrecoFiltroArCab);
        edtQtdeFiltroArCab = (EditText) findViewById(R.id.edtQtdeFiltroArCab);
/*
        // 8 ) Filtro Direção Hidráulica
        chkFiltroDH = (CheckBox)findViewById(R.id.chkFiltroDH);
        spnFiltroDH = (Spinner)findViewById(R.id.spnFiltroDH);
        txtModeloFiltroDH = (TextView)findViewById(R.id.txtModeloFiltroDH);
        txtPrecoFiltroDH = (EditText)findViewById(R.id.txtPrecoFiltroDH);
        edtQtdeFiltroDH= (EditText) findViewById(R.id.edtQtdeFiltroDH);
*/

        fireFiltro = new FireFiltro();
        fireOleo = new FireOleo();
        fireVeiculo = new FireVeiculo();

        listAdapFiltroAr = new ArrayList<String>();
        listAdapFiltroArCab = new ArrayList<String>();
        listAdapFiltroCom = new ArrayList<String>();
        //       listAdapFiltroDH = new ArrayList<String>();
        listAdapFiltroOleo = new ArrayList<String>();
        listAdapOleoCambio = new ArrayList<String>();
        //       listAdapOleoDif  = new ArrayList<String>();
        listAdapOleoMotor = new ArrayList<String>();

        listFiltroOleo = new ArrayList<Filtro>();
        listFiltroAr = new ArrayList<Filtro>();
        listFiltroArCab = new ArrayList<Filtro>();
        listFiltroComb = new ArrayList<Filtro>();
//        listFiltroDH = new ArrayList<Filtro>();

        listOleoMotor = new ArrayList<Oleo>();
        listOleoCambio = new ArrayList<Oleo>();
//        listOleoDif = new ArrayList<Oleo>();

        listFiltrosTotal = new ArrayList<Filtro>();
        listOleosTotal = new ArrayList<Oleo>();

        fireFiltro.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                try {
                    listFiltrosTotal.clear();
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Log.d(" OnDataChange ", child.getValue().toString());
                        Filtro f = child.getValue(Filtro.class);
                        listFiltrosTotal.add(f);
                    }
                    AtualizarLists();
                    CarregarCampos();
                    Log.d("OnDataChange Ok", "Tamanho da lista de filtros: " + listFiltrosTotal.size());

                } catch (Exception e) {
                    Log.e(" Erro!", "Erro no metodo FireFiltro ValueListener - " + e.getMessage());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        fireOleo.getReference().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listOleosTotal.clear();
                try {
                    for (DataSnapshot child : dataSnapshot.getChildren()) {
                        Log.d(" OnDataChange ", child.getValue().toString());
                        Oleo o = child.getValue(Oleo.class);
                        listOleosTotal.add(o);
                    }
                    AtualizarLists();
                    CarregarCampos();
                    Log.d("OnDataChange Ok", "Tamanho da lista de filtros: " + listOleosTotal.size());

                } catch (Exception e) {
                    Log.e("Erro!", "Erro no método FireOleo Listener, message: " + e.getMessage());

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        chkOleoMotor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ExibirTotal();
                spnOleoMotor.setEnabled(isChecked);
                txtPrecoOleoMotor.setEnabled(isChecked);
                edtQtdeOleoMotor.setEnabled(isChecked);
            }
        });
        chkOleoCambio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                ExibirTotal();
                spnOleoCambio.setEnabled(isChecked);
                txtPrecoOleoCambio.setEnabled(isChecked);
                edtQtdeOleoCambio.setEnabled(isChecked);
            }
        });
  /*      chkOleoDif.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spnOleoDif.setEnabled(isChecked);
                txtPrecoOleoDif.setEnabled(isChecked);
                edtQtdeOleoDif.setEnabled(isChecked);
                ExibirTotal();
            }
        });
    */
        chkFiltroOleo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spnFiltroOleo.setEnabled(isChecked);
                txtPrecoFiltroOleo.setEnabled(isChecked);
                edtQtdeFiltroOleo.setEnabled(isChecked);
                ExibirTotal();
            }
        });
        chkFiltroComb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spnFiltroComb.setEnabled(isChecked);
                ExibirTotal();
                txtPrecoFiltroComb.setEnabled(isChecked);
                edtQtdeFiltroComb.setEnabled(isChecked);
            }
        });
        chkFiltroAr.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spnFiltroAr.setEnabled(isChecked);
                txtPrecoFiltroAr.setEnabled(isChecked);
                ExibirTotal();
                edtQtdeFiltroAr.setEnabled(isChecked);
            }
        });
        chkFiltroArCab.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spnFiltroArCab.setEnabled(isChecked);
                txtPrecoFiltroArCab.setEnabled(isChecked);
                ExibirTotal();
                edtQtdeFiltroArCab.setEnabled(isChecked);
            }
        });
  /*      chkFiltroDH.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spnFiltroDH.setEnabled(isChecked);
                txtPrecoFiltroDH.setEnabled(isChecked);
                ExibirTotal();
                edtQtdeFiltroDH.setEnabled(isChecked);
            }
        });
*/
        spnFiltroAr.setOnItemSelectedListener(this);
        spnFiltroArCab.setOnItemSelectedListener(this);
//        spnFiltroDH.setOnItemSelectedListener(this);
        spnFiltroComb.setOnItemSelectedListener(this);
        spnFiltroOleo.setOnItemSelectedListener(this);
        spnOleoMotor.setOnItemSelectedListener(this);
        spnOleoCambio.setOnItemSelectedListener(this);
        //      spnOleoDif.setOnItemSelectedListener(this);

        edtQtdeOleoMotor.addTextChangedListener(this);
        edtQtdeOleoCambio.addTextChangedListener(this);
        //    edtQtdeOleoDif.addTextChangedListener(this);
        edtQtdeFiltroOleo.addTextChangedListener(this);
        //    edtQtdeFiltroDH.addTextChangedListener(this);
        edtQtdeFiltroComb.addTextChangedListener(this);
        edtQtdeFiltroAr.addTextChangedListener(this);
        edtQtdeFiltroArCab.addTextChangedListener(this);

        txtPrecoOleoMotor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    listOleoMotor.get(spnOleoMotor.getSelectedItemPosition()).setValor(Double.parseDouble(txtPrecoOleoMotor.getText().toString()));
                    MostrarPreco();
                } catch (Exception e) {
                    Log.e("Erro ", "Erro no Método txtPrecoOleoMotor textChanged, message: " + e.getMessage());
                }
            }
        });
        txtPrecoOleoCambio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    listOleoCambio.get(spnOleoCambio.getSelectedItemPosition()).setValor(Double.parseDouble(txtPrecoOleoCambio.getText().toString()));
                    MostrarPreco();
                } catch (Exception e) {
                    Log.e("Erro ", "Erro no Método txtprecoOleoCambio textChanged, message: " + e.getMessage());
                }
            }
        });
        txtPrecoFiltroAr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    listFiltroAr.get(spnFiltroAr.getSelectedItemPosition()).setValor(Double.parseDouble(txtPrecoFiltroAr.getText().toString()));
                    MostrarPreco();
                } catch (Exception e) {
                    Log.e("Erro ", "Erro no Método txtprecofiltro AR textChanged, message: " + e.getMessage());
                }
            }
        });
        txtPrecoFiltroOleo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    listFiltroOleo.get(spnFiltroOleo.getSelectedItemPosition()).setValor(Double.parseDouble(txtPrecoFiltroOleo.getText().toString()));
                    MostrarPreco();
                } catch (Exception e) {
                    Log.e("Erro ", "Erro no Método txtprecofiltro oleo textChanged, message: " + e.getMessage());
                }
            }
        });
        txtPrecoFiltroArCab.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    listFiltroArCab.get(spnFiltroArCab.getSelectedItemPosition()).setValor(Double.parseDouble(txtPrecoFiltroArCab.getText().toString()));
                    MostrarPreco();
                } catch (Exception e) {
                    Log.e("Erro ", "Erro no Método txtpreco filtro ar cab textChanged, message: " + e.getMessage());
                }
            }
        });
        txtPrecoFiltroComb.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    listFiltroComb.get(spnFiltroComb.getSelectedItemPosition()).setValor(Double.parseDouble(txtPrecoFiltroComb.getText().toString()));
                    MostrarPreco();
                } catch (Exception e) {
                }
            }
        });

        fabAdicionar = (FloatingActionButton) findViewById(R.id.fabAdicionar);

        fabAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Item item = new Item(getBaseContext(), itemsAvulsos.size() + 1);
                    //            item.checkBox.setOnCheckedChangeListener(this);
                    //            item.txtValor.addTextChangedListener(this);
                    itemsAvulsos.add(item);
                    layoutChecklist.addView(item.getLayoutPrincipal());
                } catch (Exception e) {

                }
            }
        });


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabEmail);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try {
                    StringBuilder sb = new StringBuilder();

                    int i = 1;

                    DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm");

                    String data = df.format(new Date());

                    sb.append("BRS MOTOR SERVICE - ORÇAMENTO");
                    sb.append("\nData: " + data);
                    sb.append("\n\n INFORMAÇÕES DO VEÍCULO");
                    sb.append("\nMarca: " + veiculo.getMarca());
                    sb.append("\nModelo: " + veiculo.getModelo());
                    sb.append("\nAno: " + veiculo.getAno());
                    sb.append("\nCombustível: " + veiculo.getCombustivel());

                    sb.append("\n\n ITENS DO ORÇAMENTO");

                    if (chkOleoMotor.isChecked()) {
                        sb.append("\n\n" + i + "º ) - Óleo do Motor ");
                        i++;
                        sb.append("\nTipo do Óleo: " + veiculo.getOleoMotor());
                        sb.append(" - " + veiculo.getLitragem() + " Lts.");
                        sb.append("\nMarca: " + listOleoMotor.get(spnOleoMotor.getSelectedItemPosition()).getMarca());
                        sb.append("\nValor Unitário: R$" + listOleoMotor.get(spnOleoMotor.getSelectedItemPosition()).getValor());
                        sb.append("\nTotal: R$" + (listOleoMotor.get(spnOleoMotor.getSelectedItemPosition()).getValor()) * veiculo.getLitragem());
                    }
                    if (chkOleoCambio.isChecked()) {
                        sb.append("\n\n" + i + "º ) - Óleo do Câmbio ");
                        i++;
                        sb.append("\nTipo do Óleo: " + veiculo.getOleoCambio());
                        //   sb.append(" - " + veiculo.getLitragem() + " Lts.");
                        sb.append("\nMarca: " + listOleoCambio.get(spnOleoCambio.getSelectedItemPosition()).getMarca());
                        sb.append("\nValor Unitário: R$" + listOleoCambio.get(spnOleoCambio.getSelectedItemPosition()).getValor());
                        sb.append("\nTotal: R$" + (listOleoCambio.get(spnOleoCambio.getSelectedItemPosition()).getValor()) * Double.parseDouble(edtQtdeOleoCambio.getText().toString()));
                    }
                    if (chkFiltroAr.isChecked()) {
                        sb.append("\n\n" + i + "º ) - Filtro de Ar ");
                        i++;
                        sb.append("\nMarca: " + listFiltroAr.get(spnFiltroAr.getSelectedItemPosition()).getMarca());
                        sb.append("\nValor Unitário: R$" + listFiltroAr.get(spnFiltroAr.getSelectedItemPosition()).getValor());
                        sb.append("\nTotal: R$" + (listFiltroAr.get(spnFiltroAr.getSelectedItemPosition()).getValor()) * Double.parseDouble(edtQtdeFiltroAr.getText().toString()));
                    }
                    if (chkFiltroArCab.isChecked()) {
                        sb.append("\n\n" + i + "º ) - Filtro de  Ar Cabine");
                        i++;
                        sb.append("\nMarca: " + listFiltroArCab.get(spnFiltroArCab.getSelectedItemPosition()).getMarca());
                        sb.append("\nValor Unitário: R$" + listFiltroArCab.get(spnFiltroArCab.getSelectedItemPosition()).getValor());
                        sb.append("\nTotal: R$" + (listFiltroArCab.get(spnFiltroArCab.getSelectedItemPosition()).getValor()) * Double.parseDouble(edtQtdeFiltroArCab.getText().toString()));
                    }
                    if (chkFiltroOleo.isChecked()) {
                        sb.append("\n\n" + i + "º ) - Filtro de Óleo ");
                        i++;
                        sb.append("\nMarca: " + listFiltroOleo.get(spnFiltroOleo.getSelectedItemPosition()).getMarca());
                        sb.append("\nValor Unitário: R$" + listFiltroOleo.get(spnFiltroOleo.getSelectedItemPosition()).getValor());
                        sb.append("\nTotal: R$" + (listFiltroOleo.get(spnFiltroOleo.getSelectedItemPosition()).getValor()) * Double.parseDouble(edtQtdeFiltroOleo.getText().toString()));
                    }
                    if (chkFiltroComb.isChecked()) {
                        sb.append("\n\n" + i + "º ) - Filtro de Combustível");
                        i++;
                        sb.append("\nMarca: " + listFiltroComb.get(spnFiltroComb.getSelectedItemPosition()).getMarca());
                        sb.append("\nValor Unitário: R$" + listFiltroComb.get(spnFiltroComb.getSelectedItemPosition()).getValor());
                        sb.append("\nTotal: R$" + (listFiltroComb.get(spnFiltroComb.getSelectedItemPosition()).getValor()) * Double.parseDouble(edtQtdeFiltroComb.getText().toString()));
                    }
                    for (int k = 0; k < itemsAvulsos.size(); k++) {
                        if (itemsAvulsos.get(k).isChecked()) {
                            sb.append("\n\n" + i + "º ) - Item");
                            i++;
                            sb.append("\nDescrição: " + itemsAvulsos.get(k).getItemNome());
                            sb.append("\nValor : R$" + itemsAvulsos.get(k).getItemValor());
                        }
                    }

                    sb.append("\n\n\n Total dos Serviços: R$" + totalOrcamento);

                    Intent email = new Intent(Intent.ACTION_SEND);
                    String subject = "BRS SERVICE - ORÇAMENTO";
                    email.putExtra(Intent.EXTRA_SUBJECT, subject);
                    email.putExtra(Intent.EXTRA_TEXT, sb.toString());
                    email.setType("text/plain");

                    //startActivity(Intent.createChooser(email, "E-mail"));
                    startActivity(email);

                } catch (Exception e) {
                    Log.e("Activity Orcamento", "Ocorreu um erro no Botão Enviar Orçamento, message: " + e.getMessage());
                }


            }
        });
    }

    private void MostrarPreco() {
        try {
            //  Snackbar.make(view, "Total acumulado: " + String.format("%.2f", totalOrcamento), Snackbar.LENGTH_SHORT).show();

            double oleomotor = 0, oleocambio = 0, oleodif = 0, filtrooleo = 0, filtrocomb = 0, filtroar = 0, filtroarcab = 0, filtrodh = 0;

            if (chkOleoMotor.isChecked()) {
                oleomotor = Double.parseDouble(txtPrecoOleoMotor.getText().toString()) * Double.parseDouble(edtQtdeOleoMotor.getText().toString());
            }
            if (chkOleoCambio.isChecked()) {
                oleocambio = Double.parseDouble(txtPrecoOleoCambio.getText().toString()) * Double.parseDouble(edtQtdeOleoCambio.getText().toString());
            }
            //   if(chkOleoDif.isChecked()){
            //      oleodif = Double.parseDouble(txtPrecoOleoDif.getText().toString())* Double.parseDouble(edtQtdeOleoDif.getText().toString());
            // }
            if (chkFiltroAr.isChecked()) {
                filtroar = Double.parseDouble(txtPrecoFiltroAr.getText().toString()) * Double.parseDouble(edtQtdeFiltroAr.getText().toString());
            }
            if (chkFiltroArCab.isChecked()) {
                filtroarcab = Double.parseDouble(txtPrecoFiltroArCab.getText().toString()) * Double.parseDouble(edtQtdeFiltroArCab.getText().toString());
            }
            if (chkFiltroOleo.isChecked()) {
                filtrooleo = Double.parseDouble(txtPrecoFiltroOleo.getText().toString()) * Double.parseDouble(edtQtdeFiltroOleo.getText().toString());
            }
            // if(chkFiltroDH.isChecked()){
            //     filtrodh = Double.parseDouble(txtPrecoFiltroDH.getText().toString()) * Double.parseDouble(edtQtdeFiltroDH.getText().toString());
            // }
            if (chkFiltroComb.isChecked()) {
                filtrocomb = Double.parseDouble(txtPrecoFiltroComb.getText().toString()) * Double.parseDouble(edtQtdeFiltroComb.getText().toString());
            }

            try {
                totalAvulso = 0;
                for (int k = 0; k < itemsAvulsos.size(); k++) {
                    if (itemsAvulsos.get(k).isChecked()) {
                        totalAvulso += itemsAvulsos.get(k).getItemValor();
                        Log.d("ITENS AVULSOS", "Adicionando valor..");
                    }
                }
            } catch (Exception e) {
                Log.e("TESTE", "Erro no btnCalcular, " + e.getMessage());
            }

            totalOrcamento = filtroar + filtroarcab + filtrodh + filtrocomb + filtrooleo + oleomotor + oleocambio + oleodif + totalAvulso;

            Log.d("EXIBIR TOTAL", "ORÇAMENTO ATUAL: " + String.format("%.2f", totalOrcamento));
            //   Toast.makeText(this,"Total acumulado: " + String.format("%.2f", totalOrcamento),Toast.LENGTH_SHORT).show();
            CoordinatorLayout view = (CoordinatorLayout) findViewById(R.id.coordinatorLayoutOrcamento);
            CardView cardView = (CardView) findViewById(R.id.cardviewOrcamento);

            txtTotalOrcamento.setText("Total acumulado: " + String.format("%.2f", totalOrcamento));
            Snackbar.make(cardView, "Total acumulado: " + String.format("%.2f", totalOrcamento), Snackbar.LENGTH_LONG).setAction("Action", null).show();

        } catch (Exception e) {
            Log.e("ERRO", "ERRO NO MÉTODO EXIBIR ORÇAMENTO, CAUSA: " + e.getMessage());
        }

    }

    private void ExibirTotal() {
        try {
            //  Snackbar.make(view, "Total acumulado: " + String.format("%.2f", totalOrcamento), Snackbar.LENGTH_SHORT).show();

            txtModeloOleoMotor.setText(listOleoMotor.get(spnOleoMotor.getSelectedItemPosition()).getModelo().toString());
            txtPrecoOleoMotor.setText(listOleoMotor.get(spnOleoMotor.getSelectedItemPosition()).getValor() + "");

            txtModeloOleoCambio.setText(listOleoCambio.get(spnOleoCambio.getSelectedItemPosition()).getModelo().toString());
            txtPrecoOleoCambio.setText(listOleoCambio.get(spnOleoCambio.getSelectedItemPosition()).getValor() + "");

   /*         txtModeloOleoDif.setText(listOleoDif.get(spnOleoDif.getSelectedItemPosition()).getModelo().toString());
            txtPrecoOleoDif.setText(listOleoDif.get(spnOleoDif.getSelectedItemPosition()).getValor()+"");
*/
            txtModeloFiltroOleo.setText(listFiltroOleo.get(spnFiltroOleo.getSelectedItemPosition()).getModelo().toString());
            txtPrecoFiltroOleo.setText(listFiltroOleo.get(spnFiltroOleo.getSelectedItemPosition()).getValor() + "");

            txtModeloFiltroAr.setText(listFiltroAr.get(spnFiltroAr.getSelectedItemPosition()).getModelo().toString());
            txtPrecoFiltroAr.setText(listFiltroAr.get(spnFiltroAr.getSelectedItemPosition()).getValor() + "");

            txtModeloFiltroArCab.setText(listFiltroArCab.get(spnFiltroArCab.getSelectedItemPosition()).getModelo().toString());
            txtPrecoFiltroArCab.setText(listFiltroArCab.get(spnFiltroArCab.getSelectedItemPosition()).getValor() + "");

            txtModeloFiltroComb.setText(listFiltroComb.get(spnFiltroComb.getSelectedItemPosition()).getModelo().toString());
            txtPrecoFiltroComb.setText(listFiltroComb.get(spnFiltroComb.getSelectedItemPosition()).getValor() + "");

            //          txtModeloFiltroDH.setText(listFiltroDH.get(spnFiltroDH.getSelectedItemPosition()).getModelo().toString());
            //        txtPrecoFiltroDH.setText(listFiltroDH.get(spnFiltroDH.getSelectedItemPosition()).getValor()+"");
            MostrarPreco();
        } catch (Exception e) {
            Log.e("ERRO", "ERRO NO MÉTODO EXIBIR ORÇAMENTO, CAUSA: " + e.getMessage());
        }
    }

    private void limparLists() {
        try {
            listAdapFiltroAr.clear();
            listAdapFiltroOleo.clear();
//        listAdapFiltroDH.clear();
            listAdapFiltroArCab.clear();
            listAdapFiltroCom.clear();
            listAdapOleoCambio.clear();
//        listAdapOleoDif.clear();
            listAdapOleoMotor.clear();

            listFiltroOleo.clear();
            listFiltroComb.clear();
//        listFiltroDH.clear();
            listFiltroArCab.clear();
            listFiltroAr.clear();

            listOleoMotor.clear();
//        listOleoDif.clear();
            listOleoCambio.clear();
        } catch (Exception e) {
            Log.e("Erro ", "Erro no método limparLists, message: " + e.getMessage());
        }
    }

    private void AtualizarLists() { // terminar de atualizar todos os valores

        try {
            limparLists();

            String oleo_motor = veiculo.getOleoMotor();
            String oleo_cambio = veiculo.getOleoCambio();


            for (int i = 0; i < listFiltrosTotal.size(); i++) {
                String tipo = listFiltrosTotal.get(i).getTipo();
                switch (tipo) {
                    case Constantes.FILTRO_AR:
                        String codfAr = listFiltrosTotal.get(i).getCodigo();
                        if (codfAr.equals(veiculo.getCodfAr())) {
                            listFiltroAr.add(listFiltrosTotal.get(i));
                            listAdapFiltroAr.add(listFiltrosTotal.get(i).getMarca());
                            Log.d("AtualizarLists", "Adicinando o filtro de ar tipo:  " + tipo);
                        }
                        break;
                    case Constantes.FILTRO_AR_CAB:
                        String codfArCab = listFiltrosTotal.get(i).getCodigo();
                        if (codfArCab.equals(veiculo.getCodfArCab())) {
                            listFiltroArCab.add(listFiltrosTotal.get(i));
                            listAdapFiltroArCab.add(listFiltrosTotal.get(i).getMarca());
                            Log.d("AtualizarLists", "Adicinando o filtro de ar cabine tipo:  " + tipo);
                        }
                        break;
                    case Constantes.FILTRO_COMB:
                        String codfcomb = listFiltrosTotal.get(i).getCodigo();
                        if (codfcomb.equals(veiculo.getCodfComb())) {
                            listFiltroComb.add(listFiltrosTotal.get(i));
                            listAdapFiltroCom.add(listFiltrosTotal.get(i).getMarca());
                            Log.d("AtualizarLists", "Adicinando o filtro de combustível tipo:  " + tipo);
                        }
                        break;
      /*                  case Constantes.FILTRO_DH:
                            listFiltroDH.add(listFiltrosTotal.get(i));
                            listAdapFiltroDH.add(listFiltrosTotal.get(i).getMarca());
                            Log.d("AtualizarLists", "Adicinando o filtro de direção hidraulica tipo:  " + tipo);
                            break;
        */
                    case Constantes.FILTRO_OLEO:
                        String codfoleo = listFiltrosTotal.get(i).getCodigo();
                        if (codfoleo.equals(veiculo.getCodfOleo())) {
                            listFiltroOleo.add(listFiltrosTotal.get(i));
                            listAdapFiltroOleo.add(listFiltrosTotal.get(i).getMarca());
                            Log.d("AtualizarLists", "Adicinando o filtro oleo tipo:  " + tipo);
                        }
                        break;
                    default:
                        Log.e("Erro Activity Orçamento", "Valor inválido no Switch Filtros: " + tipo);
                }
            }

            for (int i = 0; i < listOleosTotal.size(); i++) {
                String modelo = listOleosTotal.get(i).getModelo();
                String comb = listOleosTotal.get(i).getCombustivel();
                if (modelo.equals(oleo_cambio)) {
                    listOleoCambio.add(listOleosTotal.get(i));
                    listAdapOleoCambio.add(listOleosTotal.get(i).getMarca());
                    Log.d("AtualizarLists", "Adicinando oleo de cambio tipo:  " + modelo);
                }
          /*      if (modelo.equals(oleo_dif)) {
                    listOleoDif.add(listOleosTotal.get(i));
                    listAdapOleoDif.add(listOleosTotal.get(i).getMarca());
                    Log.d("AtualizarLists", "Adicinando oleo de Diferencial tipo:  " + modelo);
                }
            */
                if (modelo.equals(oleo_motor) && comb.equals(veiculo.getCombustivel())) {
                    listOleoMotor.add(listOleosTotal.get(i));
                    listAdapOleoMotor.add(listOleosTotal.get(i).getMarca());
                    Log.d("AtualizarLists", "Adicinando oleo de motor tipo:  " + modelo);
                }
            }
        } catch (Exception e) {
            Log.e("ERRO", "METODO ATUALIZAR LISTS");
        }
    }

    public void CarregarCampos() {
        try {
            ArrayAdapter<String> adapOleoMotor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapOleoMotor);
            adapOleoMotor.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnOleoMotor.setAdapter(adapOleoMotor);

            ArrayAdapter<String> adapOleoCambio = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapOleoCambio);
            adapOleoCambio.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnOleoCambio.setAdapter(adapOleoCambio);
/*
            ArrayAdapter<String> adapOleoDif = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapOleoDif);
            adapOleoDif.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnOleoDif.setAdapter(adapOleoDif);
*/
            ArrayAdapter<String> adapFiltroOleo = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapFiltroOleo);
            adapFiltroOleo.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnFiltroOleo.setAdapter(adapFiltroOleo);

            ArrayAdapter<String> adapFiltroCom = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapFiltroCom);
            adapFiltroCom.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnFiltroComb.setAdapter(adapFiltroCom);

            ArrayAdapter<String> adapFiltroAr = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapFiltroAr);
            adapFiltroAr.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnFiltroAr.setAdapter(adapFiltroAr);

            ArrayAdapter<String> adapFiltroArCab = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapFiltroArCab);
            adapFiltroArCab.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnFiltroArCab.setAdapter(adapFiltroArCab);
/*
            ArrayAdapter<String> adapFiltroDH = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, listAdapFiltroDH);
            adapFiltroDH.setDropDownViewResource(android.R.layout.simple_spinner_item);
            spnFiltroDH.setAdapter(adapFiltroDH);
*/

            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub.
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            txtTipoVeiculo.setText(veiculo.getTipo());
                            txtMarcaVeiculo.setText(veiculo.getMarca());
                            txtCombustivel.setText(veiculo.getCombustivel());
                            txtAnoVeiculo.setText(veiculo.getAno() + "");
                            txtLitragem.setText(veiculo.getLitragem() + " Litros");
                            txtModeloVeiculo.setText(veiculo.getModelo());
                            txtOleoMotorVeiculo.setText(veiculo.getOleoMotor());

                            edtQtdeOleoMotor.setText(veiculo.getLitragem() + "");
                            edtQtdeOleoCambio.setText("1");
                            //             edtQtdeOleoDif.setText("1");
                            edtQtdeFiltroAr.setText("1");
                            edtQtdeFiltroArCab.setText("1");
                            edtQtdeFiltroComb.setText("1");
                            //           edtQtdeFiltroDH.setText("1");
                            edtQtdeFiltroOleo.setText("1");
                        }
                    });
                }
            });
            t.start();

        } catch (Exception e) {
            Log.e("ERRO", "METODO CARREGAR CAMPOS");
        }
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        ExibirTotal();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ExibirTotal();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        ExibirTotal();
    }
}