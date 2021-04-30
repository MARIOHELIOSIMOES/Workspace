package solucionandoproblemas;

import java.util.Scanner;

public class Desafio5MMC {
    private int numero1;
    private int numero2;
    private int n = 2;
    private int resultado = 1;

    public static void main(String[] args) {
        Desafio5MMC desafio5Mmc = new Desafio5MMC();
        desafio5Mmc.obtemNumeros();
        System.out.println(desafio5Mmc.calculaMmc());
    }

    public int calculaMmc() {
        while (this.numero1 != 1 || this.numero2 != 1) {
            while (this.numero1 % this.n != 0 && this.numero2 % this.n != 0) {
                this.n++;
            }
            if (this.numero1 % n == 0) {
                this.numero1 = this.numero1 / n;
            }
            if (this.numero2 % n == 0) {
                this.numero2 = this.numero2 / n;
            }
            this.resultado *= this.n;
        }
        return this.resultado;
    }

    public void obtemNumeros() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o primeiro numero: ");
        this.numero1 = sc.nextInt();

        System.out.println("Insira o primeiro numero: ");
        this.numero2 = sc.nextInt();
    }
}