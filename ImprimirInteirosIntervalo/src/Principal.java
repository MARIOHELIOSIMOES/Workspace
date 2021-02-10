public class Principal {
    /*
        Desenvolvido por Mário Hélio Simões
    */
    
    public static void main(String[] args) { 
    //primeiro método a ser chamado - Padrão JAVA
        Principal principal = new Principal();// cria um objeto da classe principal
    }
    
    public Principal(){//construtor da classe, será chamado quando criar uma instância da classe
        imprimirIntervalo(2, 12); //chama o método passando os valores que quer imprimir
    }
    private void imprimirIntervalo(int inicio, int fim){ //método que irá receber o intervalo e imprimir
        System.out.printf("\nIntervalo: Inicio = %d,  Fim = %d\n", inicio, fim);
        for(int i = inicio; i<= fim ; i++){
            System.out.print(" " + i);//imprime os números
        }
    }
    
}
