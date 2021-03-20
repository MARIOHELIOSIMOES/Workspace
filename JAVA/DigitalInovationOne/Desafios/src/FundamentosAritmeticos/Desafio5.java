package FundamentosAritmeticos;

import java.util.Scanner;

/*
* Desafio
Você deve calcular o consumo médio de um automóvel onde será informada a distância total percorrida (em Km) e o total de combustível consumido (em litros).

Entrada
Você receberá dois valores: um valor inteiro X com a distância total percorrida (em Km), e um valor real Y que representa o total de combustível consumido, com um dígito após o ponto decimal.

Saída
Exiba o valor que representa o consumo médio do automóvel (3 casas após a vírgula), incluindo no final a mensagem "km/l".
*
* */
public class Desafio5 {

    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);
        //int km = Integer.parseInt(leitor.nextLine());
        //int km = leitor.nextInt();
        float km = leitor.nextFloat();
        //int litros = Integer.parseInt(leitor.nextLine());
        //int litros = leitor.nextInt();
        float litros = leitor.nextFloat();
        float consumo =  km / litros;

        System.out.printf("%.3f km/l",consumo);
    }

}
