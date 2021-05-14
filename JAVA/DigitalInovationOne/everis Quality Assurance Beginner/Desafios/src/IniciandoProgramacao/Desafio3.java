package IniciandoProgramacao;

import java.util.Scanner;

public class Desafio3 {
    private void imprimir(String cidade){
        System.out.println(cidade);
    }
    public static void main(String[] args) {
        Desafio3 d3 = new Desafio3();
        Scanner scanner = new Scanner(System.in);
        int ddd = Integer.parseInt(scanner.nextLine());
        switch (ddd){
            case 61:
                d3.imprimir("Brasilia");
                break;
            case 71:
                d3.imprimir("Salvador");
                break;
            case 11:
                d3.imprimir("Sao Paulo");
                break;
            case 21:
                d3.imprimir("Rio de Janeiro");
                break;
            case 32:
                d3.imprimir("Juiz de Fora");
                break;
            case 19:
                d3.imprimir("Campinas");
                break;
            case 27:
                d3.imprimir("Vitoria");
                break;
            case 31:
                d3.imprimir("Belo Horizonte");
                break;
            default:
                d3.imprimir("DDD nao cadastrado");

        }
    }

}
/*
* Leia um número inteiro que representa um código de DDD para discagem interurbana.
* Em seguida, informe à qual cidade o DDD pertence, considerando a tabela abaixo:


Se a entrada for qualquer outro DDD que não esteja presente na tabela acima, o programa deverá informar:
DDD nao cadastrado

Entrada
A entrada consiste de um único valor inteiro.

Saída
Imprima o nome da cidade correspondente ao DDD existente na entrada.
* Imprima DDD nao cadastrado caso não existir DDD correspondente ao número digitado.
*
* */