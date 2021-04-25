package InterfacesFuncionais.Supridores;

import java.util.function.Supplier;

public class Suplies {
    public static void main(String[] args) {
        Supplier<Pessoa> instaciaPessoa = () -> new Pessoa();// Não recebe parâmetro, mas retorna a classe desejada
        Supplier<Pessoa> instaciaPessoa2 = Pessoa::new;// Não recebe parâmetro, mas retorna a classe desejada

        System.out.println(instaciaPessoa.get());
        System.out.println(instaciaPessoa2.get());
    }
}
class Pessoa{
    private String nome;
    private Integer idade;
    public Pessoa(){
        nome = "mario";
        idade = 28;
    }

    @Override
    public String toString() {
        return  "nome='" + nome +", idade=" + idade ;
    }
}
