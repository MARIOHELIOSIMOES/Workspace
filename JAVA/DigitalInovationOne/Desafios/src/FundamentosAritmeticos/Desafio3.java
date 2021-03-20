package FundamentosAritmeticos;

import java.io.IOException;
import java.util.Scanner;

/*
* Desafio
Você deve fazer a leitura de 5 valores inteiros.
*   Em seguida mostre
*   quantos valores informados são pares,
*   quantos valores informados são ímpares,
*   quantos valores informados são positivos e
*   quantos valores informados são negativos.

Entrada
Você receberá 5 valores inteiros.

Saída
Exiba a mensagem conforme o exemplo de saída abaixo,
*   sendo uma mensagem por linha e não esquecendo o final de linha após cada uma.*/
public class Desafio3 {
        public static void main(String[] args) throws IOException {
            Scanner leitor = new Scanner(System.in);

            int [] valores = new int[5];
            for(int i = 0; i< 5; i++){
                valores[i] = Integer.parseInt(leitor.nextLine());
            }
            int par = 0, impar = 0, positivo = 0, negativo = 0;
            for (int i :valores){
                if(i%2==0) {
                    par++;
                }else{
                    impar ++;
                }
                if(i==0){
                    continue;
                }
                if(i<0){
                    negativo++;
                }else {
                    positivo++;
                }
            }
            System.out.printf("%d valor(es) par(es)\n" +
                    "%d valor(es) impar(es)\n"+
                    "%d valor(es) positivo(s)\n"+
                    "%d valor(es) negativo(s)\n", par, impar, positivo, negativo);

        }
}