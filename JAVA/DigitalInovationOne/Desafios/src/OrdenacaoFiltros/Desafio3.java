package OrdenacaoFiltros;


import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Desafio3 {
    public static void main(String[] args) {
        List<Aluno> branco = new ArrayList<>();
        List<Aluno> vermelho = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        int qtde = Integer.parseInt(scanner.nextLine()); // Lê a quantidade de uniformes
        String nome = "", tamanhoECor = "";
        Aluno aluno;
        while (qtde!=0){
            branco.clear();
            vermelho.clear();
            for(int i = 0; i< qtde; i++){
                nome = scanner.nextLine();
                tamanhoECor = scanner.nextLine();
                Character c = tamanhoECor.charAt(tamanhoECor.length()-1);

                aluno = new Aluno();
                aluno.nome = nome;
                aluno.tamanho = c;

                if(tamanhoECor.length()==10){
                    vermelho.add(aluno);
                }else {
                    branco.add(aluno);
                }
            }
            branco.stream()
                    .sorted(Comparator.comparing(Aluno::getTamanho).thenComparing(Aluno::getNome))
                    //.sorted(Aluno::compareTo)
                    //.collect(Collectors.toList())
                    //.(Comparator.comparing(Aluno::getNome))
                    .forEach(aluno1 -> {System.out.println("branco "+aluno1.tamanho+" "+aluno1.nome);});

            vermelho.stream()
                    .sorted(Comparator.comparing(Aluno::getTamanho).thenComparing(Aluno::getNome))
                    .forEach(aluno1 -> {System.out.println("vermelho "+aluno1.tamanho+" "+aluno1.nome);});
            qtde = Integer.parseInt(scanner.nextLine()); // Lê a quantidade de uniformes
        }

    }


}
class Aluno implements Comparable{
    char tamanho;
    String nome;

    public char getTamanho() {
        if(tamanho == 'P'){
            return 'A';
        }
        if(tamanho == 'M'){
            return 'B';
        }
        if(tamanho == 'G'){
            return 'C';
        }
        return tamanho;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public int compareTo(Object o) {
        Aluno a = (Aluno) o;
        switch (this.tamanho){
            case 'P':{
                if(a.tamanho == 'P'){
                    return 0;
                }else {
                    return -1;
                }
            }
            case 'M':
            {
                if(a.tamanho == 'P'){
                    return 1;
                }else if(a.tamanho == 'M'){
                    return 0;
                }else {
                    return -1;
                }
            }
            default:
            {
                if(a.tamanho == 'G'){
                    return 0;
                }else {
                    return 1;
                }
            }
        }
    }
}

/*
* Desafio
O professor Girafales organizou a confecção de um uniforme para as turmas da escola para comemorar
* o final do ano.
* Após algumas conversas, ficou decidido com os alunos que eles poderiam escolher a cor do uniforme
*  entre branco ou vermelho.
* Assim sendo, Girafales precisa de sua ajuda para organizar as listas de quem quer o uniforme em cada uma das turmas,
* relacionando estas camisetas pela cor, tamanho (P, M ou G) e por último pelo nome.

Entrada
Cada caso de teste inicia com um valor N, (1 ≤ N ≤ 60) inteiro e positivo,
* que indica a quantidade de uniformes a serem feitas para aquela turma.
* As próximas N*2 linhas contém informações de cada um dos uniformes
* (serão duas linhas de informação para cada uniforme).
* A primeira linha irá conter o nome do estudante e a segunda linha irá conter a cor do uniforme
* ("branco" ou "vermelho") seguido por um espaço e pelo tamanho do uniforme "P" "M" ou "G".
* A entrada termina quando o valor de N for igual a zero (0) e esta valor não deverá ser processado.

Saída
Para cada caso de entrada deverão ser impressas as informações ordenadas pela cor em ordem ascendente,
*  seguido pelos tamanhos em ordem descendente e
* por último por ordem ascendente de nome, conforme o exemplo abaixo.

* Exemplo de Entrada
9
Maria Jose
branco P
Mangojata Mancuda
vermelho P
Cezar Torres Mo
branco P
Baka Lhau
vermelho P
JuJu Mentina
branco M
Amaro Dinha
vermelho P
Adabi Finho
branco G
Severina Rigudinha
branco G
Carlos Chade Losna
vermelho P
0
*
Exemplo de Saída

* branco P Cezar Torres Mo
branco P Maria Jose
branco M JuJu Mentina
branco G Adabi Finho
branco G Severina Rigudinha
vermelho P Amaro Dinha
vermelho P Baka Lhau
vermelho P Carlos Chade Losna
vermelho P Mangojata Mancuda
* */