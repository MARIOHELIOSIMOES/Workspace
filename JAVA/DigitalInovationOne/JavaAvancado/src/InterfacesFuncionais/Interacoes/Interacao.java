package InterfacesFuncionais.Interacoes;

import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Interacao {
    public static void main(String[] args) {
        String [] nomes = {"Mario", "Helio", "Simoes", "Mario", "Mario"};
        Integer [] numeros = {0,1,2,3,4,5,6,7,8,9};
        imprimirNomesFiltrados(nomes);
        imprimirTodos(nomes);
        imprimirDobroNumeros(numeros);

    }
    public static void imprimirNomesFiltrados(String... nomes){
      String nomesParaImprimir =   Stream.of(nomes)
                .filter(nome -> nome.equals("Mario"))
                .collect(Collectors.joining());

      System.out.println(nomesParaImprimir);
    }
    public static void imprimirTodos(String ... nomes){
        Stream.of(nomes)
                .forEach(nome -> System.out.println(nome));
    }
    public static void imprimirDobroNumeros(Integer ... numeros){
        Stream.of(numeros).map(numero -> numero * 2)
                .forEach(numero -> System.out.println(numero));
    };
}
