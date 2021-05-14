package IniciandoProgramacao;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Desafio2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String CPF = scanner.nextLine();
        String n = "";
       for(int i = 0; i< CPF.length(); i++){
            String ch = CPF.charAt(i)+"";
            if(ch.equals(".") || ch.equals("-")){
                System.out.println(n);
                n = "";
            }else {
                n +=ch;
            }
       }
       System.out.println(n);
    }
}
/*
* Desafio
Elabore um programa que possuas as características abaixo:

Leia os dados de um CPF no formato XXX.YYY.ZZZ-DD;
Imprima os quatro grupos numéricos, sendo um valor por linha.

* Entrada
A entrada consiste vários arquivos de teste e cada um possuindo uma linha com formato XXX.YYY.ZZZ-DD,
* onde XXX, YYY, ZZZ, DD são números inteiros.

Saída
Para cada arquivo da entrada, tem que ter um arquivo de saída com quatro linhas,
* e em cada linha um número inteiro de acordo com procedimento 2 descrito no Desafio.
* Confira o exemplo abaixo:
*
* */