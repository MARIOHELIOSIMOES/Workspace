
package simoes.mario.controlesalarial.dao;

import android.content.Context;


public class SalarioDAO extends AbstractDAO{

	Context t;
	public SalarioDAO(Context context) {
		super(context);
		t = context;
	}
/*
	public Long adiciona(Salario salario) {
		
		Log.i("SalarioDAO", "Entrou no metodo adiciona..");
		Long retorno = (long) -1;
		Salario s = new Salario();
		s=retonarSalarioMes(salario.getMes(), salario.getAno());
		
		if(s.getId()>0){
			Log.i("SalarioDao", "atualiza..." + s.getDescricaoSalario());
			s.setValor(salario.getValor());
			retorno = atualiza(s);
		}
		else{
			Log.i("SalarioDao", "insere..." + s.getDescricaoSalario());
			retorno = insere(salario);
		}
		Log.i("SalarioDAO", "Metodo adiciona.. Sai.. ok.. retornou :" + retorno);
		return retorno;
		
		/*
		if(existe(salario.getId())){
			Log.i("SalarioDao", "atualiza");
			return atualiza(salario);
		}else{
			Log.i("SalarioDao", "insere");
			return insere(salario);
		}
	}

	private Long atualiza(Salario salario) {
		Log.i("SalarioDao", "atualiza entra: " + salario.getDescricaoSalario());
		Long retorno = (long) -1;
		
		ContentValues valores = montaValues(salario);//checar essa linha
		//return (long) retornaBancoParaLeitura().update(Constantes.TABELA_SALARIO, valores, Constantes.ID+ " = ?", new String[] {String.valueOf(salario.getId())});
		retorno = (long) retornaBancoParaLeitura().update(
				Constantes.TABELA_SALARIO,
				valores, 
				Constantes.ID+ " = " +salario.getId(),
				null);
		if(retorno > 0){
			Log.i("SalarioDAO", "linhas atualizadas :" + retorno);
		}else{
			Log.w("SalarioDAO", "Nenhuma linha atualizada..");
		}
		
		return retorno;
		//return (long) retornaBancoParaLeitura().update(Constantes.TABELA_SALARIO,
			//	valores, Constantes.ID+ " = ?", new String[] {String.valueOf(salario.getId())});
			//	Constantes.ID+ " = "+salario.getId(),null);
//		Constantes.ID+ " = ?", new String[] {String.valueOf(salario.getId())});
	}

	private long insere(Salario salario) {
		Log.i("SalarioDao", "insere entra: "+salario.getDescricaoSalario());
		long retorno = -1;
		ContentValues valores = montaValues(salario);
		retorno = (long) retornaBancoParaEscrita().insert(Constantes.TABELA_SALARIO, null, valores);
		Log.i("SalarioDao", "insere sai.. retorno: "+ retorno);
		return retorno;
		//return retornaBancoParaEscrita().insert(Constantes.TABELA_SALARIO, null, valores);
		//retornaBancoParaEscrita().execSQL(montaStringSqlInsert(salario.getMes(), salario.getAno(),salario.getValor()));
		//return 1;
	}

	private ContentValues montaValues(Salario salario) {
		Log.i("SalarioDao", "Monta valores entra.." + salario.getDescricaoSalario());
		ContentValues valores = new ContentValues();
		if(salario.getId()>0){
			valores.put(Constantes.ID, salario.getId());
		}
		valores.put(Constantes.ANO, salario.getAno());
		valores.put(Constantes.MES, salario.getMes());
		valores.put(Constantes.VALOR, salario.getValor());
		Log.i("SalarioDao", "monta valores sai");
		return valores;
	}

	private boolean existe(int id) {
		Log.i("SalarioDao", "Existe entra");
		try{
			Cursor cursorComIdSalario = retornaBancoParaLeitura().query(Constantes.TABELA_SALARIO, new String[] 
					//{Constantes.ID}, Constantes.ID + " = ?", new String[] {String.valueOf(id)}, 
					{Constantes.ID}, Constantes.ID + " = ",
					new String[] {String.valueOf(id)}, 
					null, null, null);
			boolean existeSalario = cursorComIdSalario.getCount() > 0;
			fechaCursor(cursorComIdSalario);
			Log.i("SalarioDao", "Existe ok sai");
			return existeSalario;
		}catch(Exception e){
			Log.i("SalarioDao", "Existe erro sai");
			return false;
		}
	}
	
	public Salario retonarSalarioMes(int mes, int ano){
		Log.i("SalarioDao", "RetornaSalarioMes entra");
		Salario salario = new Salario();
		Cursor cursorS = null;
		try{
			//Cursor s = retornaBancoParaLeitura().query(Constantes.TABELA_SALARIO, new String[] {Constantes.MES +" ," +Constantes.ANO},Constantes.MES+" = ?"+mes+" and "+Constantes.ANO+" = ? "+ano,null,null,null,null);
			cursorS = retornaBancoParaLeitura().query(
					Constantes.TABELA_SALARIO, //tabela
					null, //colunas
					Constantes.MES+" = "+mes+" and "+Constantes.ANO+" =  "+ano, //where
					null,null,null,null);
			if(cursorS.getCount()>0){
				Log.i("SalarioDAO", "Cursor.getCount() retornou: "+ cursorS.getCount());
				cursorS.moveToFirst();
				salario = montaSalarioCursor(cursorS);	
			}
			else{
				Log.w("SalarioDAO", "Cursor.getCount() retornou: "+ cursorS.getCount());
			}
		}catch(Exception e){
			Log.e("SalarioDao", "RetornaSalarioMes Erro:"+ e.getMessage() + " ..Causa:" + e.getCause());
		}
		finally {
			fechaCursor(cursorS);
		}
		Log.i("SalarioDao", "RetornaSalarioMes sai..:" + salario.getDescricaoSalario());
		return salario;
	}

	
	
	// conferir aqui.. comparar com o retornarsalariomes
	public ArrayList<Salario> retornaTodosOsSalarios() {
		Log.i("SalarioDao", "retorna todos os salarios entra");

		Cursor cursorComTodosOsSalarios = retornaBancoParaLeitura().query(Constantes.TABELA_SALARIO, null, null, null, 
				null, null, null);
		cursorComTodosOsSalarios.moveToFirst();

		ArrayList<Salario> salarios = new ArrayList<Salario>();
		
		try{
			do{
				salarios.add(montaCursor(cursorComTodosOsSalarios));
			}while(cursorComTodosOsSalarios.moveToNext());
		}catch(Exception e){
			salarios = new ArrayList<Salario>();
			Log.i("SalarioDao", "retorna todos os salario erro em branco");
		}
		finally {
			fechaCursor(cursorComTodosOsSalarios);
		}
		
		Log.i("SalarioDao", "retorna todos os salario sai");
		return salarios;
	}

	private Salario montaCursor(Cursor cursorSalarios) { //Cursor s� l� a primeira linha..
		Log.i("SalarioDao", "montar salario cursor entra");

		Salario salario = new Salario();
		try{
			salario.setId(cursorSalarios.getInt(cursorSalarios.getColumnIndex(Constantes.ID)));
			salario.setAno(cursorSalarios.getInt(cursorSalarios.getColumnIndex(Constantes.ANO)));
			salario.setMes(cursorSalarios.getInt(cursorSalarios.getColumnIndex(Constantes.MES)));
			salario.setValor(cursorSalarios.getDouble(cursorSalarios.getColumnIndex(Constantes.VALOR)));
		}catch(Exception e){
			Log.e("SalarioDao", "Ocorreu um erro ao ler o cursor.. Message: "+ e.getMessage()
					+"..causa: " + e.getCause());
			Toast.makeText(t, "Ocorreu um erro ao ler o cursor.. Message: "+ e.getMessage()
					+"..causa: " + e.getCause(), Toast.LENGTH_LONG).show();
		}
		Log.i("SalarioDao", "montar salario cursor sai.. retornou: "+ salario.getDescricaoSalario());
		return salario;
	}


	private Salario montaSalarioCursor(Cursor cursorSalarios) { //Cursor s� l� a primeira linha..
		Log.i("SalarioDao", "montar salario cursor entra");

		Salario salario = new Salario();
		try{
			cursorSalarios.moveToFirst();
			salario.setId(cursorSalarios.getInt(cursorSalarios.getColumnIndex(Constantes.ID)));
			salario.setAno(cursorSalarios.getInt(cursorSalarios.getColumnIndex(Constantes.ANO)));
			salario.setMes(cursorSalarios.getInt(cursorSalarios.getColumnIndex(Constantes.MES)));
			salario.setValor(cursorSalarios.getDouble(cursorSalarios.getColumnIndex(Constantes.VALOR)));
		}catch(Exception e){
			Log.e("SalarioDao", "Ocorreu um erro ao ler o cursor.. Message: "+ e.getMessage()
			+"..causa: " + e.getCause());
			Toast.makeText(t, "Ocorreu um erro ao ler o cursor.. Message: "+ e.getMessage()
			+"..causa: " + e.getCause(), Toast.LENGTH_LONG).show();
		}
		Log.i("SalarioDao", "montar salario cursor sai.. retornou: "+ salario.getDescricaoSalario());
		return salario;
	}
	
	private String montaStringSqlInsert(int mes, int ano, double valor){
		String sql = null;
		sql = "INSERT INTO SALARIO ("+Constantes.MES+" , " + Constantes.ANO +" , "+ Constantes.VALOR +") "
				+"VALUES ("+mes+", "+ ano + ", "+valor+");";
		
		return sql;
	}
*/
}
