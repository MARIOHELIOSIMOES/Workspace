package estrutura_sequencial;

public class Casting {
	public Casting() {
		int a = 2;
		int b = 5;
		double resultado = (double) b / a;
		System.out.printf("A = %d\nB = %d\n Resultado da divisão: \nSem casting: %d\nCom Casting: %.2f", a, b, (b/a), resultado);
	}
}
