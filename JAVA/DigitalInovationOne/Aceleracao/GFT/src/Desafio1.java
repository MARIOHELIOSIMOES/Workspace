import java.util.Scanner;

public class Desafio1 {
    private static final float PI = 3.14159f;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //float raio = Float.parseFloat(scanner.nextLine());
        //float area = raio * raio * PI;
        double raio = Double.parseDouble(scanner.nextLine());
        double area = 3.14159 * (Math.pow(raio,2));
        System.out.printf("A=%.4f\n", area);
    }
}
/*
* A fórmula para calcular a área de uma circunferência é: area = π . raio2.
* Considerando para este problema que π = 3.14159:

- Efetue o cálculo da área, elevando o valor de raio ao quadrado e multiplicando por π.

Entrada
A entrada contém um valor de ponto flutuante (dupla precisão), no caso, a variável raio.

Saída
Apresentar a mensagem "A=" seguido pelo valor da variável area,
* conforme exemplo abaixo, com 4 casas após o ponto decimal.
* Utilize variáveis de dupla precisão (double).
* Como todos os problemas, não esqueça de imprimir o fim de linha após o resultado,
* caso contrário, você receberá "Presentation Error".
* */