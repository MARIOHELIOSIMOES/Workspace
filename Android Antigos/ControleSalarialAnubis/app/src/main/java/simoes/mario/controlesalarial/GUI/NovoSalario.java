package simoes.mario.controlesalarial.GUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import simoes.mario.controlesalarial.Firebase.FireSalario;
import simoes.mario.controlesalarial.R;
import simoes.mario.controlesalarial.modelos.Salario;
import simoes.mario.controlesalarial.negocio.SalarioBusiness;

public class NovoSalario extends AppCompatActivity {

	
	DatePicker dtpDataSalario;
	EditText txtSalarioValor;
	Button btnEnviarSalario;
	
	Salario salario;
	FireSalario fireSalario;

	SalarioBusiness Bsalario;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.novo_salario);
		
		dtpDataSalario = (DatePicker)findViewById(R.id.dtpDataSalario);
		txtSalarioValor = (EditText)findViewById(R.id.txtValorSalario);
		btnEnviarSalario = (Button)findViewById(R.id.btnEnviarSalario);

		fireSalario = new FireSalario();

		btnEnviarSalario.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try{
					salario = fireSalario.Procurar(dtpDataSalario.getMonth(), dtpDataSalario.getYear());
					salario.setValor(Double.parseDouble(txtSalarioValor.getText().toString()));
					salario.setAno(dtpDataSalario.getYear());
					salario.setMes(dtpDataSalario.getMonth());

					if(salario.getId().equals("")){
						fireSalario.SalvarNovo(salario);
					}else {
						fireSalario.Atualizar(salario);
					}
					Intent it = new Intent(); it.putExtra("Teste", "teste ok!!"); setResult(RESULT_OK, it);
					showToast("Salvo!");
					finish();
					
				}catch(Exception e){
					showToast(e.getMessage().toString());
					
				}
			}
		});
		
		
		
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
