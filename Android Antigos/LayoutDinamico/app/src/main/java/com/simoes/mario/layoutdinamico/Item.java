package com.simoes.mario.layoutdinamico;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.Format;

/**
 * Created by MARIO on 26/03/2017.
 */

public class Item {


    Context context;
    LinearLayout layoutPrincipal,layoutItem, layoutValor;

    private CheckBox checkBox;
    private TextView txtItem;
    private EditText item;
    private TextView txtValor;
    private EditText valor;

    int match_parente = LinearLayout.LayoutParams.MATCH_PARENT;
    int wrap_content = LinearLayout.LayoutParams.WRAP_CONTENT;

    LinearLayout.LayoutParams layoutParams, layoutParamsPrincipal;

    public Item(Context context){
        this.context = context;
        layoutPrincipal = new LinearLayout(context);
        layoutItem = new LinearLayout(context);
        layoutValor = new LinearLayout(context);

        layoutPrincipal.setOrientation(LinearLayout.HORIZONTAL);
        layoutItem.setOrientation(LinearLayout.VERTICAL);
        layoutValor.setOrientation(LinearLayout.VERTICAL);

        layoutParams = new LinearLayout.LayoutParams(match_parente,wrap_content,1);
        layoutParamsPrincipal = new LinearLayout.LayoutParams(match_parente,match_parente); ///adiciinei essa linha


        checkBox = new CheckBox(layoutPrincipal.getContext());
        txtItem = new TextView(layoutItem.getContext());
        item = new EditText(layoutItem.getContext());
        txtValor = new TextView(layoutValor.getContext());
        valor = new EditText(layoutValor.getContext());

        item.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        valor.setInputType(InputType.TYPE_CLASS_NUMBER);

        //txtItem.getResources().getColor(R.color.LetraTitulo);
        txtItem.setTextColor(Color.RED);
        txtItem.setText("Item");
        txtValor.setTextColor(Color.RED);
        txtValor.setText("Valor");

        //  checkBox.setId(100+ R.ind)

       // checkBox.setLayoutParams(layoutParams); // talvez tire essa linha
       // layoutPrincipal.setLayoutParams(layoutParamsPrincipal);
        layoutItem.setLayoutParams(layoutParams);
        layoutValor.setLayoutParams(layoutParams);
        txtItem.setLayoutParams(layoutParams);
        item.setLayoutParams(layoutParams);
        txtValor.setLayoutParams(layoutParams);
        valor.setLayoutParams(layoutParams);


        layoutItem.addView(txtItem);
        layoutItem.addView(item);

        layoutValor.addView(txtValor);
        layoutValor.addView(valor);

        layoutPrincipal.addView(checkBox);
        layoutPrincipal.addView(layoutItem);
        layoutPrincipal.addView(layoutValor);

    }
    public LinearLayout getLayoutPrincipal(){
        return layoutPrincipal;
    }

    public boolean isChecked(){
        return checkBox.isChecked();
    }
    public String getItemNome(){
        return item.getText().toString();
    }
    public void setItemNome(String itemNome){
        item.setText(itemNome);
    }
    public Double getItemValor(){
        return Double.parseDouble(valor.getText().toString());
    }
    public void setItemValor(Double valor){
        this.valor.setText(valor+"");
    }



    /*
    Context context;
    LinearLayout linearLayout;

    private CheckBox checkBox;
    private TextView txtLinha1;
    private EditText item;
    private TextView txtLinha2;
    private EditText valor;

    int match_parente = LinearLayout.LayoutParams.MATCH_PARENT;
    int wrap_content = LinearLayout.LayoutParams.WRAP_CONTENT;

    LinearLayout.LayoutParams layoutParams;

    public Item(Context context, int ind){
        this.context = context;
        linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);

        layoutParams = new LinearLayout.LayoutParams(match_parente,wrap_content,1);


        checkBox = new CheckBox(linearLayout.getContext());
        txtLinha1 = new TextView(linearLayout.getContext());
        item = new EditText(linearLayout.getContext());
        txtLinha2 = new TextView(linearLayout.getContext());
        valor = new EditText(linearLayout.getContext());

        item.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        valor.setInputType(InputType.TYPE_CLASS_NUMBER);

     //   txtLinha1.setId(i++);
        txtLinha1.setText("Item: ");
      //  txtLinha2.setId(i++);
        txtLinha2.setText("Valor: ");

        //  checkBox.setId(100+ R.ind)

        checkBox.setLayoutParams(layoutParams);
        txtLinha1.setLayoutParams(layoutParams);
        item.setLayoutParams(layoutParams);
        txtLinha2.setLayoutParams(layoutParams);
        valor.setLayoutParams(layoutParams);

        linearLayout.addView(checkBox);
        linearLayout.addView(txtLinha1);
        linearLayout.addView(item);
        linearLayout.addView(txtLinha2);
        linearLayout.addView(valor);
    }
    public LinearLayout getLinearLayout(){
        return linearLayout;
    }

    public boolean isChecked(){
        return checkBox.isChecked();
    }
    public String getItemNome(){
        return item.getText().toString();
    }
    public void setItemNome(String itemNome){
        item.setText(itemNome);
    }
    public Double getItemValor(){
        return Double.parseDouble(valor.getText().toString());
    }
    public void setItemValor(Double valor){
        this.valor.setText(valor+"");
    }
/*/
}
