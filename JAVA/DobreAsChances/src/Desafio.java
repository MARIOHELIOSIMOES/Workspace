public class Desafio {


    public static void main(String[] args) {

        int[] chances = {0,1,2,5,7,6,8}; //criando um array para teste

        Desafio d1 = new Desafio(); //instanciando a classe que contem o método para dobrar o array
        d1.imprimirArray(chances); //imprimindo o array inicial
        int[] chances2 = d1.doubleTheChances(chances); // acessando o método
        d1.imprimirArray(chances2); //imprimindo array dobrado


    }
    // Método para dobrar os valores do array conforme solicitado no exercício
    public int[] doubleTheChances(int[] chances){
        int[] chances2 = new int[chances.length]; //cria novo array com tamanho fixo
        for(int i = 0; i< chances.length; i++){ //itera pelo array
            chances2[i]= chances[i] * 2; //multiplica por dois o valor do primeiro array e salva no segundo array
        }
        return chances2; // retorna o array
    }

    //Método de teste para imprimir os valores do array no console
    public void imprimirArray(int[] array){
        System.out.println("Imprimindo array..");
        for (int valor: array){
            System.out.print(" "+ valor);
        }
        System.out.println("\n...................................");

    }
}
