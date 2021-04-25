package OrdenacaoFiltros;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Desafio5 {
    List<Integer> integerList = new ArrayList<>();
  /*  public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
       // System.out.print("Informe a quantidade de alunos: ");
        int nAlunos = Integer.parseInt(scanner.nextLine());
        while (nAlunos>0){
            List<Integer> circuloList = new ArrayList<>();
            List<String> circuloNome = new ArrayList<>();
            for(int i = 0; i< nAlunos;i++) {
            //   System.out.print("Informe nome e numero da ficha: ");
                String[] nomeNumero = scanner.nextLine().split(" ");
                circuloList.add(Integer.parseInt(nomeNumero[1]));
                circuloNome.add(nomeNumero[0]);
            }
            int posicao = 0;
            int ficha = circuloList.get(1);

            while (circuloList.size()>1){
                int passos = (ficha) % circuloList.size(); //Ignora voltas completas

                if(ficha%2==0){//Par //Decrescente
                    posicao = circuloList.size() - passos-1;
              //      System.out.println("Posicao Atual: "+posicao);

                    ficha = circuloList.get(posicao);
                    circuloList.remove(posicao);
                //    System.out.print("removendo: "+circuloNome.get(posicao));
                    circuloNome.remove(posicao);
                }else {//Impar //Crescente
                    ficha = circuloList.get(passos);
                    posicao = passos;
               //     System.out.println("Posicao Atual: "+posicao);
                    circuloList.remove(posicao);
               //     System.out.print("removendo: "+circuloNome.get(posicao));
                    circuloNome.remove(posicao);
                }
            }
            System.out.println("Vencedor(a): "+circuloNome.get(0));
           // System.out.print("Informe a quantidade de alunos: ");

            nAlunos = Integer.parseInt(scanner.nextLine());
        }
    }
*/

    public Desafio5(){
        integerList.add(7);
        integerList.add(9);
        integerList.add(5);
        integerList.add(12);
        integerList.add(8);

    }
    public static void main(String[] args) {
        Desafio5 desafio5 = new Desafio5();
        desafio5.fazerContagem(0, desafio5.integerList.get(1));
    }

    public void fazerContagem(int posicao, int ficha){
        if(this.integerList.size()>0){

            if(ficha%2 == 0){ //Decrescente
                int proximo = (this.integerList.size()+ posicao) % ficha;
                ficha = this.integerList.get(proximo);
                this.integerList.remove(proximo);
                fazerContagem(posicao, ficha);
            }else { //crescente
                int proximo = (this.integerList.size()- posicao-1) % ficha;
                ficha = this.integerList.get(proximo);
                this.integerList.remove(proximo);
                fazerContagem(posicao, ficha);
            }

            //fazerContagem(posicao, ficha);
        }else {
            System.out.println("Vencerdor(a): "+this.integerList.get(0));
        }
    }
}
/*
* Desafio
Nas férias de Dezembro, várias escolas se organizam e levam seus alunos para um acampamento de férias por uma semana. Nestes acampamentos os alunos são divididos em cabanas coletivos por gênero e idade, sempre com um adulto que, além de dormir com o grupo no cabana, também são responsáveis por criar e executar várias atividades, como por exemplo jogos, excursões, Gincanas Noturnas, etc.

No primeiro dia foi realizada uma gincana em que a atividade constituia em agrupar os alunos em um círculo (organizado no sentido anti-horário) do qual seriam retiradas uma a uma até que sobrasse apenas um aluno, que seria o vencedor.

No momento em que entra no círculo, cada aluno recebe uma pequena ficha que contém um valor de 1 a 500. Depois que o círculo é formado, conta-se, iniciando no aluno que está ao lado da primeira que entrou no círculo, o número correspondente à ficha que o primeiro detém. O aluno onde o número contado cair, deve ser retirado do grupo, e a contagem inicia novamente segundo a ficha do aluno que acabou de ser eliminado. Para ficar mais interessante, quando o valor que consta na ficha é par, a contagem é feita no sentido horário e quando o valor que consta na ficha é ímpar, a contagem é feita no sentido anti-horário.

Desenvolva um programa para que no próximo evento o responsável pela brincadeira saiba previamente qual criança irá ser a vencedora de cada grupo, com base nas informações fornecidas.

Entrada
A entrada contém vários casos de teste. A primeira linha de cada caso de teste contém um inteiro N (1 ≤ N ≤ 100), indicando a quantidade de alunos que farão parte de cada círculo. Em seguida, as N linhas de cada caso de teste conterão duas informações, o Nome e o Valor (1 ≤ Valor ≤ 500) que consta na ficha de cada aluno, separados por um espaço, na ordem de entrada na formação do círculo inicial.

OBS: O Nome de cada aluno não deverá ultrapassar 30 caracteres e contém apenas letras maiúsculas e minúsculas, sem acentos, e o caractere “_”. O final da entrada é indicado pelo número zero.

Saída
Para cada caso de teste, deve-se apresentar a mensagem Vencedor(a): xxxxxx, com um espaço após o sinal ":" indicando qual é o aluno do grupo que venceu a brincadeira.


Exemplo de Entrada
3
Fernanda 7
Fernando 9
Gustavo 11
5
Maria 7
Pedro 9
Joao_Vitor 5
Isabel 12
Laura 8
3
Maria 4
Pedro 3
Joao 2
0
*
Exemplo de Saída
Vencedor(a): Fernanda
Vencedor(a): Pedro
Vencedor(a): Pedro
*
* */