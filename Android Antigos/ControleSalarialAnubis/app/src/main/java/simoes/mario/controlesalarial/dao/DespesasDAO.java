package simoes.mario.controlesalarial.dao;

import android.content.Context;


public class DespesasDAO extends AbstractDAO{
	Context t;
	public DespesasDAO(Context context) {
		super(context);
		t = context;
	}
/*
	// metodo ok
	public Long adiciona(Despesas despesa) {
		if(existe(despesa.getId())){
			Log.i("DespesasDao", "atualiza");
			return atualiza(despesa);
		}else{			
			Log.i("DespesasDao", "insere entra");
			long retorno = -1;
			retorno = insere(despesa);
				
			if(retorno!=-1){
				Log.i("DespesasDao", "insere resultado OK");
				return (long) 1;
			}
			else{				
				Log.i("DespesasDao", "insere Problema");
				return (long) -1;
			}
		}
	}

	
	private Long atualiza(Despesas despesas) {
		Log.i("DespesasDAO", "Entrou no método atualiza..");
		ContentValues valores = montaValues(despesas);
		long retorno = -1;
		retorno = retornaBancoParaLeitura().update(
				Constantes.TABELA_DESPESAS, //tabela
				valores, //valores
				Constantes.ID+ " = " + despesas.getId(), //where 
				null);
			Log.i("DespesasDAO", "Update.. linhas atualizadas:"+retorno);
		return retorno;
	}

	private long insere(Despesas despesas) {
		
		Log.i("DespesasDAO", "Entrou no método insere..");
		ContentValues valores = montaValues(despesas);
		long retorno = -1;
		retorno = retornaBancoParaEscrita().insert(Constantes.TABELA_DESPESAS, null, valores);
		if(retorno==-1){
			Log.w("DespesasDAO", "INSERT.. NÃO INSERIU RETORNOU:"+retorno);
		}
		return retorno;
	
	}

	private ContentValues montaValues(Despesas despesas) {
		Log.i("DespesasDAO", "Entrou no m�todo montavalores.." + despesas.getDescricao());
		ContentValues valores = new ContentValues();
		if(despesas.getId()>0){
			valores.put(Constantes.ID, despesas.getId());
		}
		valores.put(Constantes.NOME, despesas.getNome());
		valores.put(Constantes.ID_MES, despesas.getId_mes());
		valores.put(Constantes.VALOR, despesas.getValor());
		return valores;
	}

	private boolean existe(int id) {

		boolean retorno=false;
		try{
			Cursor cursor = retornaBancoParaLeitura().query(
					Constantes.TABELA_DESPESAS, //tabela
					null, 
					Constantes.ID + " = " + id, 
					null, null, null, null);
			if(cursor.getCount()>0){
				retorno = true;
			}
			fechaCursor(cursor);
			
		}catch(Exception e){
			Log.e("DespesasDAO", "M�todo Existe.. Erro.. message: "+ e.getMessage() + ".. causa:" + e.getCause());
		}
		return retorno;
	}

	public ArrayList<Despesas> retornaTodosAsDespesas(int id_mes) {
		Log.i("DespesasDAO", "Entra no metodo retornaDespesas mes.. id_mes = "+id_mes);
		//Cursor cursorComTodosAsdespesas = retornaBancoParaLeitura().query(Constantes.TABELA_DESPESAS, null, Constantes.ID_MES+"=", new String[] {String.valueOf(id_mes)}, 
		//		null, null, null);
		Cursor cursor = null;
		ArrayList<Despesas> despesas = new ArrayList<Despesas>();
		try{
			cursor = retornaBancoParaLeitura().query(
				Constantes.TABELA_DESPESAS, 
				null,
				Constantes.ID_MES +" = "+ id_mes, 
				null, null, null, null);
			if(cursor.getCount()>0){	
				cursor.moveToFirst();
				do{
					despesas.add(montaDespesaCursor(cursor));
				}while(cursor.moveToNext());
				Log.i("DespesasDAO", "Metodo retornaDespesas cursor.getcount() retornou: " + cursor.getCount());
			}else{
				Log.w("DespesasDAO", "N�o h� registro de despesas no id="+id_mes);
			}
			
			fechaCursor(cursor);
			
		}catch(Exception e){
			Log.e("DespesasDAO", "Erro no metodo RetornaTodasAsDespesas.. Message:" + e.getMessage() + "..Causa: "+ e.getCause());
		
		}
		return despesas;
	}

	private Despesas montaDespesaCursor(Cursor cursorDespesas) {
		Log.i("DespesasDAO", "Entra no metodo montadespesacursor cursor.getPosition() retornou:" + cursorDespesas.getPosition());
		Despesas despesas = new Despesas();
		despesas.setId(cursorDespesas.getInt(cursorDespesas.getColumnIndex(Constantes.ID)));
		despesas.setNome(cursorDespesas.getString(cursorDespesas.getColumnIndex(Constantes.NOME)));
		despesas.setValor(cursorDespesas.getDouble(cursorDespesas.getColumnIndex(Constantes.VALOR)));
		despesas.setId_mes(cursorDespesas.getInt(cursorDespesas.getColumnIndex(Constantes.ID_MES)));
		return despesas;
	}
/*/
}
