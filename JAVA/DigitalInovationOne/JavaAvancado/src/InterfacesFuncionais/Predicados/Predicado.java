package InterfacesFuncionais.Predicados;

import java.util.function.Predicate;

public class Predicado {
    public static void main(String[] args) {
        Predicate<String> estaVazio = valor->valor.isEmpty();
        Predicate<String> tamanhoMaior5 = valor ->{
            return valor.length()>5;
        };
        System.out.println(estaVazio.test(""));
        System.out.println(estaVazio.test("Mario"));
        System.out.println("valor maior que 5: "+tamanhoMaior5.test("mariohelio"));
        System.out.println("valor maior que 5: "+tamanhoMaior5.test("mario"));
    }


}
