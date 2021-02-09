package simoes.mario.controlesalarial.negocio;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import simoes.mario.controlesalarial.dao.SalarioDAO;
import simoes.mario.controlesalarial.modelos.Despesas;
import simoes.mario.controlesalarial.modelos.Salario;

public class SalarioBusiness {
	
	Context c;
	private SalarioDAO salarioDAO;

	public SalarioBusiness(Context context) {
		salarioDAO = new SalarioDAO(context);
		c = context;
	}
	/*
	public double totalDespesas(Salario s){
		double valor = 0;
		try{
			ArrayList<Despesas> listaDespesas = new ArrayList<Despesas>();
			if(s.getId()>0) {
				DespesasBusiness db = new DespesasBusiness(c);//aquui
				listaDespesas = db.retornaTodasAsDespesas(s.getId());//aqui

				for (int i = 0; i < listaDespesas.size(); i++) {
					valor += listaDespesas.get(i).getValor();
				}
			}
		}catch(Exception e){
			Log.e("SalarioBusiness", "ERRO NO MÉTODO TOTAL DESPESAS.." + e.getMessage());
		}
		return valor;
	}


	public void adiciona(Salario salario) {
		if(salarioDAO.adiciona(salario) > 0){
			Toast.makeText(c, "Salario adicionado/atualizado com sucesso!", Toast.LENGTH_SHORT).show();
			Log.i("SalarioDao", "Salario adicionado/atualizado com sucesso!");
		}else{
			Toast.makeText(c, "Nao adicionou/atualizou o salario!", Toast.LENGTH_SHORT).show();
			Log.w("SalarioDao", "Nao adicionou/atualizou o salario!");
		}
	}

	public void fechaBanco() {
		salarioDAO.fechaBanco();
	}
	
	public Salario ProcuraraSalario(int mes, int ano){
		Log.i("SalarioDao", "procuraSalario entra..");
		
		Salario salario = new Salario();
		salario = salarioDAO.retonarSalarioMes(mes, ano);
		
		if(salario.getId()>0){
			Log.i("SalarioDao", "procurarSalario Achou.." + salario.getDescricaoSalario());
		}
		else{
			Log.i("SalarioDao", "procura Salario em branco..." + salario.getDescricaoSalario());
		}
		Log.i("SalarioDao", "procuraSalario sai");
		return salario;
	}

	public ArrayList<Salario> retornaTodosSalarios() {
		return salarioDAO.retornaTodosOsSalarios();
	}
*/
}
