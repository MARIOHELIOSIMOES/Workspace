package estrutura_sequencial;
import java.util.Scanner;

public class ScannerEx {
	public ScannerEx() {
		Scanner sc = new Scanner(System.in);
		System.out.print("Informe um inteiro: ");
		int x = sc.nextInt();
		System.out.printf("Número informado: %d", x);
		
		sc.close();
	}

}
