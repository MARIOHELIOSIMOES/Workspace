package simoes.mario.controlesalarial.GUI;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.List;

import simoes.mario.controlesalarial.Firebase.FireDespesas;
import simoes.mario.controlesalarial.Firebase.FireSalario;
import simoes.mario.controlesalarial.R;
import simoes.mario.controlesalarial.constantes.Constantes;
import simoes.mario.controlesalarial.modelos.Despesas;
import simoes.mario.controlesalarial.modelos.Salario;


public class MainActivity extends AppCompatActivity {


	TextView txtSalario, txtSaldo, txtGastoMes, txtGastos, txtData;
	ListView listViewdespesas;
	Handler handler = new Handler();
	List<Despesas> listaDespesas;
	ImageButton btnNovaDespesa,btnNovoSalario, btnAtualizar, btnGrafico;

	Salario salario;
	FireSalario fireSalario = new FireSalario();
	Despesas despesa;
	FireDespesas fireDespesas = new FireDespesas();

	public static final int requestCodebtnSalario = 1;
	public static final int requestCodebtnDespesa = 2;

	Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

		calendar = Calendar.getInstance();

        txtSalario = (TextView )findViewById(R.id.txtSalario);
        txtSaldo = (TextView)findViewById(R.id.txtSaldoAtual);
		txtGastoMes = (TextView)findViewById(R.id.txtgastoMes);
		txtGastos = (TextView)findViewById(R.id.txtGastos);
		txtData = (TextView)findViewById(R.id.txtData);
        listViewdespesas = (ListView)findViewById(R.id.listaDespesas);
        btnNovaDespesa = (ImageButton)findViewById(R.id.btnNovaDespesa);
        btnNovoSalario = (ImageButton)findViewById(R.id.btnNovoSalario);
        btnAtualizar = (ImageButton)findViewById(R.id.btnAtualizar);
        btnGrafico = (ImageButton)findViewById(R.id.btnGraficos);


        fireSalario.getReference().addValueEventListener(listenerFireSalario);
        fireDespesas.getReference().addValueEventListener(listenerFireDespesa);

        btnGrafico.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
              try {
				  Intent i = new Intent(getBaseContext(), Graficos.class);
				  startActivity(i);
			  }catch (Exception e){
              	Log.e("Erro", e.getMessage());
              	showToast(e.getMessage().toString());
			  }
            }
        });


        btnNovaDespesa.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Intent i = new Intent(getBaseContext(), NovaDespesa.class);
					startActivityForResult(i, requestCodebtnDespesa);
				}catch (Exception e){
					Log.e("Erro",e.getMessage());
					showToast(e.getMessage().toString());
				}
			}
		});
        btnNovoSalario.setOnClickListener(new OnClickListener() {
			
        	@Override
     		public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					Intent i = new Intent(getBaseContext(), NovoSalario.class);
					startActivityForResult(i, requestCodebtnSalario);
				}catch (Exception e){
					Log.e("Erro", e.getMessage());
					showToast(e.getMessage());
				}

     		}
     	});
        btnAtualizar.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				procuraMesAtual();
			}
		});
        txtData.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				try{
				Log.i("MainActivity","txtData entrou..");
					new DatePickerDialog(MainActivity.this,
							listenerDatePicker,
							calendar.get(Calendar.YEAR),
							calendar.get(Calendar.MONTH),
							calendar.get(Calendar.DAY_OF_MONTH)).show();
				}catch (Exception e){
					Log.e("Erro", e.getMessage());
					showToast(e.getMessage().toString());
				}
			}
		});

        procuraMesAtual();

    }
	DatePickerDialog.OnDateSetListener listenerDatePicker = new DatePickerDialog.OnDateSetListener() {
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			calendar.set(year,monthOfYear,dayOfMonth);
			procuraMesAtual();
		}
	};

    private void showToast(String mensagem){
    	runOnUiThread(new Runnable() {
			@Override
			public void run() {
				Toast.makeText(getBaseContext(), mensagem, Toast.LENGTH_LONG).show();
			}
		});
	}

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//    	Log.i("MAIN ACTIVITY", "OnActivity Result" + data.getExtras().getString("Teste"));
    	// TODO Auto-generated method stub
    	super.onActivityResult(requestCode, resultCode, data);
    	try{
			if(requestCode == requestCodebtnDespesa){
				if(resultCode==RESULT_OK){
					procuraMesAtual();
				}

			}
			if(requestCode == requestCodebtnSalario){
				if(resultCode==RESULT_OK){
					procuraMesAtual();
				}

			}
    	}catch (Exception e){
    		Log.e("Erro", e.getMessage());
    		showToast(e.getMessage().toString());
		}
    }



    ValueEventListener listenerFireDespesa = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                fireDespesas.ClearListDespesas();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    fireDespesas.setDespesa(child.getValue(Despesas.class));
                    fireDespesas.AddDespesaLista(fireDespesas.getDespesa());
                }
                Log.d("FireDespesa", "Tamanho da lista de despesas: " + fireDespesas.getListDespesas().size());
                procuraMesAtual();
                //   progressDialog.dismiss();
            } catch (Exception e) {
                Log.d("FireDespesa!", "Erro no DespesasListener , message: " + e.getMessage());
                showToast(e.getMessage().toString());
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };
    ValueEventListener listenerFireSalario = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            try {
                fireSalario.ClearListSalario();
                for(DataSnapshot child: dataSnapshot.getChildren()){
                    Log.d(" FireSalario ", "onDataChange :"+ child.getValue().toString());
                    fireSalario.setSalario(salario = child.getValue(Salario.class));
                    fireSalario.AddSalarioLista(fireSalario.getSalario());
                }
                Log.d("FireSalario", "Tamanho da lista de salários: "+fireSalario.getListSalario().size());
                procuraMesAtual();
                //   progressDialog.dismiss();
            }catch(Exception e){
                Log.d("FireSalario!","Erro no Salariolistener , message: " + e.getMessage());
                showToast(e.getMessage().toString());
            }
        }
        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };


    private void procuraMesAtual(){
    	try{
	    	Log.i("MAIN ACTIVITY", "MÉTODO PROCURA MES ATUAL.." );

			salario = fireSalario.Procurar(calendar.get(Calendar.MONTH),calendar.get(Calendar.YEAR));

			if(!salario.getId().equals("")){
	    		listaDespesas = fireDespesas.getListDespesasPorID(salario.getId());
	    		double saldo = 0;
	    		for(int i = 0; i < listaDespesas.size(); i++){
	    			saldo -= listaDespesas.get(i).getValor();
	    		}
	    		atualizaCampos(saldo, salario.getValor());
	    	}else{
	    		Log.i("MAIN ACTIVITY", "MÉTODO PROCURAMESATUAL.. salario.getId>0 falso" + salario.getDescricaoSalario());
	    		Toast.makeText(getApplicationContext(), "Não há registro de salário em '"+
	    					Constantes.MESES[calendar.get(Calendar.MONTH)] +" de " + calendar.get(Calendar.YEAR)+"'",
	    					Toast.LENGTH_SHORT).show();
	    	}
    	}catch(Exception e){
    		Log.e("MAIN ACTIVITY", "ERRO NO MÉTODO PROCURAMESATUAL.." + e.getMessage());
    		showToast(e.getMessage().toString());
    	}
    }
    private void atualizaCampos(double _saldo, double _salario){

		try {

			final String gastos = "R$ " + String.format("%.2f",_saldo);
			final String saldo ="R$ " + String.format("%.2f", (_saldo + _salario) );
			final String salario ="R$ " + String.format("%.2f", _salario);
			
			Thread t = new Thread(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub.
					
					handler.post( new Runnable() {
						
						@Override
						public void run() {
							
							listViewdespesas.setAdapter(new Adaptador(getApplicationContext(),listaDespesas));
							
							// TODO Auto-generated method stub
							txtSalario.setText(salario);
							txtSaldo.setText(saldo);
							txtGastos.setText(gastos);
							txtGastoMes.setText(" Gastos do Mês ");
							txtData.setText(Constantes.MESES[calendar.get(Calendar.MONTH)]+" de "+ calendar.get(Calendar.YEAR));

						}
					});
				}
			});
			t.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e("MAIN ACTIVITY", "ERRO NO MÉTODO ATUALIZA CAMPOS.." + e.getMessage());
			showToast(e.getMessage().toString());
		}
    	Log.i("MAIN ACTIVITY", "METODO ATUALIZA CAMPOS .... OK!!!");
    }
    

	class Adaptador extends BaseAdapter{
		
		List<Despesas> listaD;
		Context t;
		private LayoutInflater mInflater;

		public Adaptador(Context context, List<Despesas> lista) {

			try {
				mInflater = LayoutInflater.from(context);
				t = context;
				listaD = lista;

				Log.i("ADAPTADOR", "Entrou no construtor.. ");
			}catch (Exception e){
				Log.e("Erro", e.getMessage());
				showToast(e.getMessage().toString());
			}
			// TODO Auto-generated constructor stub
		}
		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			try {
				Log.i("ADAPTADOR", "Entrou no getView.. ");

				ItemSuporte itemHolder;
				//se a view estiver nula (nunca criada), inflamos o layout nela.
				if (view == null) {
					//infla o layout para podermos pegar as views
					view = mInflater.inflate(R.layout.item_despesa, null);

					//cria um item de suporte para n�o precisarmos sempre
					//inflar as mesmas informacoes
					itemHolder = new ItemSuporte();
					itemHolder.txtItemDesc = ((TextView) view.findViewById(R.id.txt_ItemDescricao));
					itemHolder.txtItemValor = ((TextView) view.findViewById(R.id.txt_ItemValor));

					//define os itens na view;
					view.setTag(itemHolder);
				} else {
					//se a view j� existe pega os itens.
					itemHolder = (ItemSuporte) view.getTag();
				}

				//pega os dados da lista
				//e define os valores nos itens.
				ItemListView item = new ItemListView(listaD.get(position));
				itemHolder.txtItemDesc.setText(item.getDescricao());
				itemHolder.txtItemValor.setText(item.getValor());

				//retorna a view com as informa��es
			}catch (Exception e){
				Log.e("Erro", e.getMessage());
				showToast(e.getMessage().toString());
			}
			return view;
		}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return listaD.size();
		}
		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return listaD.get(arg0);
		}
		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}
	
	}
	private class ItemSuporte {
	
	    TextView txtItemDesc;
	    TextView txtItemValor;
	}
	private class ItemListView {

	    private String Descricao;
	    private String Valor;

	    public ItemListView() {
		this.Descricao = "";
		this.Valor = "";
	    }

	    public ItemListView(Despesas despesa) {
	        this.Descricao = despesa.getNome();
	        this.Valor = "R$ "+ String.format("%.2f",despesa.getValor());
	    }

	    public String getValor() {
	        return Valor;
	    }

	    public void setValor(String _valor) {
	        this.Valor = _valor;
	    }

	    public String getDescricao() {
	        return Descricao;
	    }

	    public void setDescricao(String _descricao) {
	        this.Descricao = _descricao;
	    }
	}


	
}


