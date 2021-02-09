package simoes.mario.controlesalarial.negocio;

import java.util.ArrayList;

import simoes.mario.controlesalarial.dao.DespesasDAO;
import simoes.mario.controlesalarial.modelos.Despesas;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class DespesasBusiness {
	
	private DespesasDAO despesasDAO;

	Context t;
	public DespesasBusiness(Context context) {
		despesasDAO = new DespesasDAO(context);
		t = context;
	}
/*
	public void adiciona(Despesas despesas) {
		if(despesasDAO.adiciona(despesas) > 0){
			Log.i("DespesasBusiness", "DespesasDAO Ok!! Metodo Adiciona DespesasBusiness");
			Toast.makeText(t, "Despesa inserida!", Toast.LENGTH_SHORT).show();
			
		}else{
			Log.e("DespesasBusiness", "DespesasDAO Nao adicionou! Metodo Adiciona DespesasBusiness");
			Toast.makeText(t, "Falha ao inserir!", Toast.LENGTH_SHORT).show();
		}
	}

	public void fechaBanco() {
		despesasDAO.fechaBanco();
	}

	public ArrayList<Despesas> retornaTodasAsDespesas(int id_mes) {
		Log.i("DespesasBusiness", "DespesasDAO RetornaTodasDespesas entra... id_mes: "+ id_mes);
		
		ArrayList<Despesas> lista = null;
		
		lista = despesasDAO.retornaTodosAsDespesas(id_mes);
		
		Log.i("DespesasBusiness", "Quantidade de itens da lista: " + lista.size());
		
		
		return lista;
	}
/*/
}
