package InterfacesFuncionais.Recursividade;

public class Fatorial {
    public static void main(String[] args) {
        System.out.println(calcularFatorial(5));
    }
    private static int calcularFatorial(int valor){
        if(valor==1){
            return valor;
        }
        return valor * (calcularFatorial(valor - 1));
    }
}
