package simoes.mario.controlesalarial.constantes;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Constantes {
	public static final String TABELA_DESPESAS = "DESPESAS";
	public static final String TABELA_SALARIO = "SALARIO";
	public static final String MESES[]={"Janeiro","Fevereiro","Março","Abril","Maio",
			"Junho","Julho","Agosto", "Setembro","Outubro","Novembro","Dezembro"};
	public static final String MESES_MENOR[]={"Jan","Fev","Mar","Abr","Mai",
			"Jun","Jul","Ago", "Set","Out","Nov","Dez"};

	public static final String ID = "id";
	public static final String MES = "mes";
	public static final String ANO = "ano";
	public static final String VALOR = "valor";
	public static final String NOME = "nome";
	public static final String ID_MES = "id_mes";
	
	public static final String CREATE_TABLE_SALARIO = "CREATE TABLE `"+TABELA_SALARIO+"` ("
	+"`"+ ID +"`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
	+"`"+MES+"`	TEXT NOT NULL,"
	+"`"+ANO+"`	TEXT NOT NULL,"
	+"`"+VALOR+"`	REAL NOT NULL);";
	
	public static final String CREATE_TABLE_DESPESA ="CREATE TABLE `"+TABELA_DESPESAS+"` ("
	+"`"+ID+"`	INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
	+"`"+NOME+"`	TEXT NOT NULL,"
	+"`"+VALOR+"`	REAL NOT NULL,"
	+"`"+ID_MES+"`	INTEGER NOT NULL,"
	//+"PRIMARY KEY("+ID+"));,";
	+"FOREIGN KEY(`"+ID_MES+"`) REFERENCES "+TABELA_SALARIO+" ( "+ID+" ));";
	
	//****************************** FIREBASE *******************************************

    public static String TB_SALARIO ="TB_SALARIO";
    public static String TB_DESPESA ="TB_DESPESA";

	public static final FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
	public static final DatabaseReference referenceSalario = firebaseDatabase.getReference(TB_SALARIO);
	public static final DatabaseReference referenceDespesa = firebaseDatabase.getReference(TB_DESPESA);

}
