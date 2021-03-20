package FundamentosAritmeticos;

import java.util.Scanner;

/*
* Desafio
Faça a leitura de um valor inteiro.
*   Em seguida, calcule o menor número de notas possíveis (cédulas) onde o valor pode ser decomposto.
*   As notas que você deve considerar são de 100, 50, 20, 10, 5, 2 e 1.
*   Na sequência mostre o valor lido e a relação de notas necessárias.

Entrada
Você receberá um valor inteiro N (0 < N < 1000000).

Saída
Exiba o valor lido e a quantidade mínima de notas de cada tipo necessárias, seguindo o exemplo de saída abaixo.
* Após cada linha deve ser imprimido o fim de linha.
*
* Exemplo de Saída
576

576
5 nota(s) de R$ 100,00
1 nota(s) de R$ 50,00
1 nota(s) de R$ 20,00
0 nota(s) de R$ 10,00
1 nota(s) de R$ 5,00
0 nota(s) de R$ 2,00
1 nota(s) de R$ 1,00
*
* */
public class Desafio4 {
    public static void main(String[] args) {
        Scanner leitor = new Scanner(System.in);

        int cem = 0, cinq = 0, vinte = 0, dez = 0, cin = 0, dois = 0, um = 0;


        int valor =  Integer.parseInt(leitor.nextLine());
        System.out.println(valor);
        cem = valor/100;
        valor = valor%100;
        cinq = valor/50;
        valor = valor%50;
        vinte = valor/20;
        valor = valor%20;
        dez = valor/10;
        valor = valor%10;
        cin = valor/5;
        valor = valor%5;
        dois = valor/2;
        um = valor%2;
        System.out.printf(
        "%d nota(s) de R$ 100,00\n"+
                "%d nota(s) de R$ 50,00\n"+
                "%d nota(s) de R$ 20,00\n"+
                "%d nota(s) de R$ 10,00\n"+
                "%d nota(s) de R$ 5,00\n"+
                "%d nota(s) de R$ 2,00\n"+
                "%d nota(s) de R$ 1,00\n", cem, cinq, vinte, dez, cin, dois, um);

    }
}
