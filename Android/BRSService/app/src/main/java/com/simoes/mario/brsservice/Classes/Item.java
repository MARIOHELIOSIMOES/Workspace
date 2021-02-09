package com.simoes.mario.brsservice.Classes;

import android.content.Context;
import android.graphics.Color;
import android.text.InputType;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by MARIO on 09/05/2017.
 */

public class Item {

    public CheckBox checkBox;
    public TextView txtValor;
    Context context;
    LinearLayout layoutPrincipal, layoutItem, layoutValor;
    int match_parente = LinearLayout.LayoutParams.MATCH_PARENT;
    int wrap_content = LinearLayout.LayoutParams.WRAP_CONTENT;
    LinearLayout.LayoutParams layoutParams, layoutParamsPrincipal;
    private TextView txtItem;
    private EditText item;
    private EditText valor;

    public Item(Context context, int ind) {
        this.context = context;
        layoutPrincipal = new LinearLayout(context);
        layoutItem = new LinearLayout(context);
        layoutValor = new LinearLayout(context);

        layoutPrincipal.setOrientation(LinearLayout.HORIZONTAL);
        layoutItem.setOrientation(LinearLayout.VERTICAL);
        layoutValor.setOrientation(LinearLayout.VERTICAL);

        layoutParams = new LinearLayout.LayoutParams(match_parente, wrap_content, 1);
        layoutParamsPrincipal = new LinearLayout.LayoutParams(match_parente, match_parente); ///adiciinei essa linha


        checkBox = new CheckBox(layoutPrincipal.getContext());
        txtItem = new TextView(layoutItem.getContext());
        item = new EditText(layoutItem.getContext());
        txtValor = new TextView(layoutValor.getContext());
        valor = new EditText(layoutValor.getContext());

        item.setInputType(InputType.TYPE_TEXT_VARIATION_PERSON_NAME);
        valor.setInputType(InputType.TYPE_CLASS_NUMBER);

        //txtItem.getResources().getColor(R.color.LetraTitulo);
        txtItem.setTextColor(Color.RED);
        txtItem.setText(ind + "º - Item");
        txtValor.setTextColor(Color.RED);
        txtValor.setText("Valor");
        item.setTextColor(Color.BLACK);
        item.setBackgroundColor(Color.WHITE);
        item.setHint("Descreva o Item..");
        valor.setTextColor(Color.BLACK);
        valor.setBackgroundColor(Color.WHITE);
        valor.setHint("R$ $$.$$");
        layoutPrincipal.setBackgroundColor(Color.WHITE);

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

    public LinearLayout getLayoutPrincipal() {
        return layoutPrincipal;
    }

    public boolean isChecked() {
        return checkBox.isChecked();
    }

    public String getItemNome() {
        return item.getText().toString();
    }

    public void setItemNome(String itemNome) {
        item.setText(itemNome);
    }

    public Double getItemValor() {
        return Double.parseDouble(valor.getText().toString());
    }

    public void setItemValor(Double valor) {
        this.valor.setText(valor + "");
    }

    //TextWatcher, AdapterView.OnItemSelectedListener

    public void setOnTextChange(TextWatcher textWatcher) {
        valor.addTextChangedListener(textWatcher);
    }






/*
* 
*  <LinearLayout 
                    android:orientation="horizontal"
                    android:layout_width="match_parent"
                    android:layout_height="match_parent">
                    <CheckBox
                        android:id="@+id/chkOleoMotor"
                        android:layout_width="wrap_content"
                        android:layout_height="match_parent" />

                    <LinearLayout
                        android:layout_weight="1"
                        android:layout_width="match_parent"
                        android:orientation="vertical"
                        android:padding="5dp"
                        android:layout_height="match_parent">
                        <TextView
                            android:text="Óleo Motor"
                            android:layout_weight="1"
                          
                            android:textColor="@color/Vermelho" />
                        <EditText
                            android:id="@+id/spnOleoMotor"
                           />
                    </LinearLayout>
                    <LinearLayout
                        android:layout_weight="1"
                        android:layout_width="match_parent"
                        android:orientation="vertical"
                        android:padding="5dp"
                        android:layout_height="match_parent">
                        <TextView
                            android:id="@+id/txtModeloOleoMotor"
                            android:text="SW40"
                            android:layout_weight="1"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"
                            android:textColor="@color/Vermelho" />
                        <EditText
                            android:inputType="numberDecimal"
                            android:enabled="false"
                            android:id="@+id/txtPrecoOleoMotor"
                            android:layout_weight="1"
                            android:text="R$ 20,00"
                            android:layout_width="match_parent"
                            android:layout_height="wrap_content"/>
                    </LinearLayout>
                  
                </LinearLayout>
* 
* 
* 
* 
* */
}
