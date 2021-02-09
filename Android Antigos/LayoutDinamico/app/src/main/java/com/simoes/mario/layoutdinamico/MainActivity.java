package com.simoes.mario.layoutdinamico;

import android.content.res.Resources;
import android.graphics.Color;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout;
    TextView txtTitulo, txtLinha1, txtLinha2;
    Button btnNovo,btnCalcular;

    List<Item> lista = new ArrayList<>();
    int i =0;
    double total = 0;

    int match_parente = ViewGroup.LayoutParams.MATCH_PARENT;
    int wrap_content = ViewGroup.LayoutParams.WRAP_CONTENT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        layout = (LinearLayout)findViewById(R.id.layout);
        txtTitulo = (TextView)findViewById(R.id.txtTitulo);
        btnNovo = (Button)findViewById(R.id.btnNovo);
        btnCalcular = (Button)findViewById(R.id.btnCalcular);


        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item item = new Item(getBaseContext());
                lista.add(item);
                layout.addView(item.getLayoutPrincipal());

            }
        });
        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    total = 0;
                    for(int k = 0; k < lista.size(); k ++){
                        if(lista.get(k).isChecked()){
                            total+= lista.get(k).getItemValor();
                            Log.d("TESTE", "Adicionando valor..");
                        }
                    }
                    Toast.makeText(getBaseContext(),"Total: R$ "+ total,Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.e("TESTE","Erro no btnCalcular, "+e.getMessage());
                }
            }
        });
/*
        txtLinha1 = new TextView(layout.getContext());
        txtLinha2 = new TextView(layout.getContext());

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        txtLinha1.setText("Linha 1");
        txtLinha2.setText("Linha 2");

        txtLinha1.setBackgroundColor(Color.BLUE);
        txtLinha2.setBackgroundColor(Color.BLUE);

        //txtLinha1.setLayoutParams(layoutParams);
        //txtLinha2.setLayoutParams(layoutParams);
        layout.addView(txtLinha1);
        layout.addView(txtLinha2);
*/

    }
}
