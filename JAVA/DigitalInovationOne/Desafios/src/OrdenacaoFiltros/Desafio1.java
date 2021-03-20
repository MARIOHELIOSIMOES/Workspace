package OrdenacaoFiltros;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

//Ordenando número pares e impares
public class Desafio1 {
    Scanner leitor = new Scanner(System.in);
    ArrayList<Integer> pares = new ArrayList<>();
    ArrayList<Integer> impares = new ArrayList<>();




    public static void main(String[] args) {
        Desafio1 desafio1 = new Desafio1();
        desafio1.lerValores();
        desafio1.ordenar();
        desafio1.imprimir(desafio1.pares);
        desafio1.imprimir(desafio1.impares);

    }
    private void lerValores(){
        int tamanho = leitor.nextInt();
        for(int i =0 ; i< tamanho; i++){
            int valor = leitor.nextInt();
            if(valor%2==0){
                pares.add(valor);
            }else {
                impares.add(valor);
            }
        }
     }
    private void ordenar(){
        pares.sort((o1, o2) -> {
            if(o1>o2){
                return 1;
            }else {
                return -1;
            }
        });
        impares.sort((o1, o2) -> {
            if(o1<o2){
                return 1;
            }else {
                return -1;
            }
        });
    }
    private void imprimir(ArrayList<Integer> arrayList){
        for (int i : arrayList){
            System.out.println(i);
        }
    }
}


/*
* Desafio
Crie um programa onde você receberá valores inteiros não negativos como entrada.

Ordene estes valores de acordo com o seguinte critério:

Primeiro os Pares
Depois os Ímpares
Você deve exibir os pares em ordem crescente e na sequência os ímpares em ordem decrescente.

Entrada
A primeira linha de entrada contém um único inteiro positivo N (1 < N < 10000)
* Este é o número de linhas de entrada que vem logo a seguir.
* As próximas N linhas terão, cada uma delas, um valor inteiro não negativo.

Saída
Exiba todos os valores lidos na entrada segundo a ordem apresentada acima.
* Cada número deve ser impresso em uma linha, conforme exemplo de saída abaixo.
*
* Exemplo de Entrada
10
4
32
34
543
3456
654
567
87
6789
98

* Exemplo de Saída
4
32
34
98
654
3456
6789
567
543
87
*
* */

