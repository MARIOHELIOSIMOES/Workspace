package ProcessamentoAssincronoEParalelo;

import java.util.Random;

public class ThreadExemplo {
    public static void main(String[] args) {
        BarradeCarregamento2 b1 = new BarradeCarregamento2();
        BarradeCarregamento2 b2 = new BarradeCarregamento2();
        BarradeCarregamento2 b3 = new BarradeCarregamento2();
        b1.start();
        b2.start();
        b3.start();
    }
}
class GerarPDF implements Runnable{
    @Override
    public void run() {
        System.out.println("Gerar PDF");
    }
}
class BarraDeCarregamento implements Runnable{
    @Override
    public void run() {
        System.out.println("Loading ...");
    }
}
class BarradeCarregamento2 extends Thread{


    @Override
    public void run() {
        super.run();
        try {
            Random random = new Random();
            Thread.sleep(random.nextInt(10000));
            System.out.println("Rodei: "+this.getName());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
