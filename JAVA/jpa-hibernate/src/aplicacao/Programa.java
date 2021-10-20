package aplicacao;

import dominio.Pessoa;

public class Programa {
    public static void main(String[] args) {
        Pessoa p1 = new Pessoa(1,"mario", "mariohelio@hotmail.com");
        Pessoa p2 = new Pessoa(2,"viviane", "viviane@hotmail.com");
        System.out.println(p1);
        System.out.println(p2);
    }
}
