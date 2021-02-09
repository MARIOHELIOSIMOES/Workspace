package simoes.mario.controlesalarial.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import simoes.mario.controlesalarial.db.DBHelper;


public class AbstractDAO {

	private SQLiteOpenHelper dbHelper;

	public AbstractDAO(Context context) {
		dbHelper = new DBHelper(context);
	}

	public void fechaCursor(Cursor cursor) {
		Log.i("AbstractDao", "fecha cursor entra");
		if (!cursor.isClosed()) {
			cursor.close();
			Log.i("AbstractDao", "fecha cursor ok ");
		}
		Log.i("AbstractDao", "fecha cursor sai");
	}

	public SQLiteDatabase retornaBancoParaEscrita() {
		Log.i("AbstractDao", "retorna banco escrita entra");
		return dbHelper.getWritableDatabase();
	}

	public SQLiteDatabase retornaBancoParaLeitura() {
		Log.i("AbstractDao", "retorna banco leitura");
		return dbHelper.getReadableDatabase();
	}

	public void fechaBanco() {
		Log.i("AbstractDao", "fechar banco entra");
		if (dbHelper.getWritableDatabase().isOpen()) {
			dbHelper.close();
			Log.i("AbstractDao", "fechar banco ok");
		}
		Log.i("AbstractDao", "fechar banco sai");
	}

}
