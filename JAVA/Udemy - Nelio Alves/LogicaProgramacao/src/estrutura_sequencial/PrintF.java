package estrutura_sequencial;
import java.util.Locale;

public class PrintF {
	public PrintF() {
		//Locale.setDefault(Locale.US);
		int idade = 30;
		String nome = "Mario";
		double salario = 3500.4;
		System.out.printf("%s tem %d anos e recebe R$ %.2f.", nome, idade, salario );
	}
}
