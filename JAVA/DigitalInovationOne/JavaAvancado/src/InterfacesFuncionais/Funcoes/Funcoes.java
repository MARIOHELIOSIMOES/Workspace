package InterfacesFuncionais.Funcoes;

import java.util.function.Function;

public class Funcoes {
    public static void main(String[] args) {
        Function<String, String> retornaNomeAoContrario = texto -> new StringBuilder(texto).reverse().toString();
        Function<String, Integer> retornaInteiro = texto -> texto.length()*3;
        System.out.println(retornaNomeAoContrario.apply("Mario"));
        System.out.println(retornaInteiro.apply("Mario"));
    }
}
