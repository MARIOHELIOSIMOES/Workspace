package simoes.mario.controlesalarial.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import simoes.mario.controlesalarial.Firebase.FireDespesas;
import simoes.mario.controlesalarial.Firebase.FireSalario;
import simoes.mario.controlesalarial.R;
import simoes.mario.controlesalarial.constantes.Constantes;
import simoes.mario.controlesalarial.modelos.Despesas;
import simoes.mario.controlesalarial.modelos.Salario;

public class NovaDespesa extends AppCompatActivity {
	
	EditText txtDescricao,txtValor;
	Button btnEnviar;
	DatePicker dtpData;
	Despesas despesa;
	Salario salario;

    FireDespesas fireDespesas;
    FireSalario fireSalario;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nova_despesa);
		
		txtDescricao = (EditText)findViewById(R.id.txtND_descricao);
		txtValor = (EditText)findViewById(R.id.txtND_valor);
		btnEnviar = (Button)findViewById(R.id.btn_ND_enviar);
		dtpData = (DatePicker)findViewById(R.id.dtpDataDespesa);

        fireDespesas = new FireDespesas();
        fireSalario = new FireSalario();

		btnEnviar.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.i("NOVA DESPESA","METODO ONCLICK BTNENVIAR");
				try{
					double valor=0;
					String descricao = "";
					valor = Double.parseDouble(txtValor.getText().toString()+"");
					descricao = txtDescricao.getText().toString();
					
					if((valor!=0) && (!descricao.equals(""))){

							salario = fireSalario.Procurar(dtpData.getMonth(), dtpData.getYear());

							if(!salario.getId().equals("")){
								despesa = new Despesas(descricao,valor, salario.getId());
                                fireDespesas.SalvarNovo(despesa);
                                Log.i("NOVA DESPESA","cadastrando despesa no salario com id: "+ salario.getId());
                            }else{
								Log.i("NOVA DESPESA", "Entrou no else..Não há registro de salario.." + salario.getDescricaoSalario());
								Toast.makeText(getApplicationContext(), "Não há registro do mês de "+Constantes.MESES[salario.getMes()]+". Faça o registro do salário primeiro.", Toast.LENGTH_LONG).show();
							}
                                Intent it = new Intent(); it.putExtra("Teste", "teste ok!!");	setResult(RESULT_OK, it); finish();
					}else{
						Toast.makeText(getApplicationContext(), "Complete os campos com valores válidos!", Toast.LENGTH_LONG).show();
					}
				}catch(Exception e){
					Log.e("NOVA DESPESA", "ERRO NO ONCLIK BTNeNVIAR...Message"+ e.getMessage() + "..causa: "+e.getCause());
					showToast(e.getMessage().toString());
				}
			}//fim try
			});//fim setOnclicklistener
	}
	private void showToast(String mensagem){
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_LONG).show();
			}
		});
	}

}