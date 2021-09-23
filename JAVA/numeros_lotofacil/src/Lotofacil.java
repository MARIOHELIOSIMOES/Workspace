import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Lotofacil {

    int jogos[][];

    public Lotofacil(int nJogos){
        this.jogos = new int[nJogos][15];
        for (int i = 0; i< nJogos; i++){
            jogos[i]= gerarNumeros();
                imprimirJogo(jogos[i]);
        }
    }
    public void imprimirJogo(int [] jogo){
        for (int i: jogo) {
            System.out.print(((i<=9)?("0"+i): i) +" | ");
        }
        System.out.println("\n-------------------------------------------------------------------------------------");
    }
    private boolean identico(int[] jogo){
        for (int[] j : jogos){
            if (j.equals(jogo)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Informe a quantidade de jogos que deseja: ");
        int n = Integer.parseInt(scanner.nextLine());
        Lotofacil lf = new Lotofacil(n);



    }
    public int[] gerarNumeros(){
        Random random = new Random();
        int[] jogoAtual = new int[15];
        for(int i = 0; i< 15; i++){
            //while (Arrays.stream(jogoAtual).noneMatch(random.nextInt(24)+1))){
            int numero = random.nextInt(25) + 1;
            boolean contem = false;
            for (int n :  jogoAtual) {
                if(numero==n){
                    contem = true;
                }
            }
            if(contem){
                i--;
            }else {
                jogoAtual[i]=numero;
            }

        }
        return Arrays.stream(jogoAtual).sorted().toArray();
    }
}
