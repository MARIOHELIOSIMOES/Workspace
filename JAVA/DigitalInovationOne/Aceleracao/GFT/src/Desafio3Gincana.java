import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Desafio3Gincana {

    List<Aluno> arraylis;
    Scanner scanner;

    public Desafio3Gincana(){
        arraylis = new ArrayList<Aluno>();
        scanner = new Scanner(System.in);
    }


    public static void main(String[] args) {
        Desafio3Gincana desafio = new Desafio3Gincana();

        // System.out.print("Informe a quantidade de alunos: ");
        int nAlunos = Integer.parseInt(desafio.scanner.nextLine());

        while (nAlunos>0){
            desafio.preencherAlunos(nAlunos);
            System.out.println(desafio.vencedor(1, desafio.arraylis.get(0).getFicha()));
            nAlunos = Integer.parseInt(desafio.scanner.nextLine());
        }
    }
    private void preencherAlunos(int nAlunos){
        arraylis = new ArrayList<Aluno>();

        for(int i = 0; i< nAlunos;i++) {
            //   System.out.print("Informe nome e numero da ficha: ");
            String[] nomeNumero = scanner.nextLine().split(" ");
            Aluno aluno = new Aluno();
            aluno.setNome(nomeNumero[0]);
            aluno.setFicha(Integer.parseInt(nomeNumero[1]));
            arraylis.add(aluno);
        }
    }
    public String vencedor(int posicao, int ficha ){
        if(arraylis.size()==1){
            return "Vencedor(a)=" + arraylis.get(0).getNome();
        }
        posicao = 0; //testar continuar começar da posição inicial;
        int passos = ficha%arraylis.size(); // quantidade de passos sem contar voltas repetidas.
        posicao = posicao + ficha;

        return vencedor(posicao, removerAlunoEObterFicha(posicao));

    }
    private int removerAlunoEObterFicha(int posicao){ // remove o aluno e retorna a ficha dele
        if(posicao>=arraylis.size()){
            posicao = posicao - arraylis.size();
        }
        int ficha = arraylis.get(posicao).getFicha();
        arraylis.remove(posicao);
        return ficha;
    }
}
class Aluno{
    private int ficha;
    private String nome;

    public int getFicha() {
        return ficha;
    }

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}