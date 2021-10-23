package estrutura_sequencial;
import java.util.Locale;

public class MathEx {
	public MathEx() {
		Locale.setDefault(Locale.US);
		double x = 3.0;
		double y = 4.0;
		System.out.printf("Potencia de %.2f: %.2f", x, quadrado(x) );
		System.out.printf("\nRaiz quadrada de %.2f: %.2f", y, raizQuadrada(y));
	}
	protected double quadrado(double valor) {
		return Math.pow(valor, 2);
	}
	protected double raizQuadrada(double valor) {
		return Math.sqrt(valor);
	}
}
