package simoes.mario.controlesalarial.db;

import simoes.mario.controlesalarial.constantes.Constantes;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper{

	private static String database = "controlesalarial.db";
	private static int version = 1;

	public DBHelper(Context context) {
		super(context, database , null, version );
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try{
			db.execSQL(Constantes.CREATE_TABLE_SALARIO);
			db.execSQL(Constantes.CREATE_TABLE_DESPESA);
		}catch(Exception e){
			Log.i("DBHelper", "Erro no OnCreate: " + e.getMessage());
			
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
