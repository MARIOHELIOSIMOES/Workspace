package OrdenacaoFiltros;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Desafio2 {

    public static void main (String[] ars){

        Scanner scanner = new Scanner(System.in);
       // System.out.print("informe o tamanho da lista: ");
        int tamanho = Integer.parseInt(scanner.nextLine()); //quantidade listas que serão inputadas

        String[] listas = new String[tamanho];

        for(int i = 0; i < tamanho; i++){
       //     System.out.printf("informe o valor %d: ", i);
            listas[i] = scanner.nextLine();
        }
        for (String lista: listas){
            String [] l = lista.split(" ");
        //    System.out.println("Ordenando Lista");
            Arrays.sort(l);
            ArrayList<String> impressos = new ArrayList<>();
            for (String s: l){
                if(impressos.contains(s)){
                    continue;
                }
                System.out.print(s+" ");
                impressos.add(s);
            }
            System.out.println("");
        }

    }

}
/**
 * Desafio
 * Pedro trabalha sempre até tarde todos os dias, com isso tem pouco tempo tempo para as tarefas domésticas.
 * Para economizar tempo ele faz a lista de compras do supermercado em um aplicativo e costuma anotar cada item na mesma hora que percebe a falta dele em casa.
 *
 * O problema é que o aplicativo não exclui itens duplicados,
 * como Pedro anota o mesmo item mais de uma vez e a lista acaba ficando extensa.
 * Sua tarefa é melhorar o aplicativo de notas desenvolvendo um código que exclua os itens duplicados da lista de compras
 * e que os ordene alfabeticamente.
 *
 * Entrada
 * A primeira linha de entrada contém um inteiro N (N < 100) com a quantidade de casos de teste que vem a seguir,
 * ou melhor, a quantidade de listas de compras para organizar.
 * Cada lista de compra consiste de uma única linha que contém de 1 a 1000 itens ou
 * palavras compostas apenas de letras minúsculas (de 1 a 20 letras), sem acentos e separadas por um espaço.
 *
 * Saída
 * A saída contém N linhas,
 * cada uma representando uma lista de compra, sem os itens repetidos e em ordem alfabética.
 *
 *
 * Exemplo de Entrada
 *
 * 2
 * carne laranja suco picles laranja picles
 * laranja pera laranja pera pera
 *
 * Exemplo de Saída
 *
 * carne laranja picles suco
 * laranja pera
 *
 * */