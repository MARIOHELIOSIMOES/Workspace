package InterfacesFuncionais.FuncaoAltaOrdem;

public class FuncaoAltaOrdem {
    public static void main(String[] args) {
        Calculo somar = (a, b)-> a + b;
        Calculo subtrair = (a, b)-> a - b;
        Calculo multiplicao = (a, b)-> a * b;
        Calculo dividir = (a, b)-> a / b;
        System.out.println(calcularOperacao(somar, 10, 15));
        System.out.println(calcularOperacao(subtrair, 10, 15));
        System.out.println(calcularOperacao(multiplicao, 10, 15));
        System.out.println(calcularOperacao(dividir, 10, 15));
    }
    public static int calcularOperacao(Calculo calculo, int a, int b){
        return calculo.somar(a, b);
    }
}
@FunctionalInterface
interface Calculo{
    public int somar(int a, int b);
}