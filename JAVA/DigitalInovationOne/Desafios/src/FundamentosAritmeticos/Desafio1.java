package FundamentosAritmeticos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
* Desafio
Crie um programa que leia 6 valores. Você poderá receber valores negativos e/ou positivos como entrada, devendo desconsiderar os valores nulos. Em seguida, apresente a quantidade de valores positivos digitados.

Entrada
Você receberá seis valores, negativos e/ou positivos.

Saída
Exiba uma mensagem dizendo quantos valores positivos foram lidos assim como é exibido abaixo no exemplo de saída. Não esqueça da mensagem "valores positivos" ao final.*/
public class Desafio1 {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            double[] valores = new double[6];
            for(int i= 0; i<6; i++){
                st = new StringTokenizer(br.readLine());
                valores[i] = Double.parseDouble(st.nextToken());
            }
            int positivos = 0;
            for(double i: valores){
                if(i>0){
                    positivos++;
                }
            }
            System.out.println(positivos+ " valores positivos");
        }
}
