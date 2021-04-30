package solucionandoproblemas

import java.lang.Math.sqrt

fun main(args: Array<String>) {
    //continue a solução
    val n = readLine()!!.toInt()
        for ( i in 1 .. n ) {
            val teste = readLine()!!.toInt()
            if(isPrime(teste)){
                    println("Prime")
            }else{
                    println("Not Prime")
            }
        }
}

fun isPrime(n: Int):Boolean
{
    var i: Int
    var s: Int

    if (n <= 3)
        return false
     //   return ! ( n < 2 );

    // Mais um caso especial... Se for divisível por 2 ou 3, não é primo!
    if ( ( n % 2 ) == 0 || ( n % 3 ) == 0)
        return false;

    s = sqrt(n.toDouble()).toInt()

    i = 5
    while (i<=s){
        if ( ( n % i ) == 0)
            return false;
        i += 2
        if ( ( n % i ) == 0)
            return false;
        i+=4
    }
    return true;
}



/*
* Mariazinha sabe que um Número Primo é aquele que pode ser dividido somente por 1 (um) e por ele mesmo.
* Por exemplo, o número 7 é primo, pois pode ser dividido apenas pelo número 1 e pelo número 7 sem que haja resto.
*  Então ela pediu para você fazer um programa que aceite diversos valores e
*  diga se cada um destes valores é primo ou não.
* Acontece que a paciência não é uma das virtudes de Mariazinha,
* portanto ela quer que a execução de todos os casos de teste
* que ela selecionar (instâncias) aconteçam no tempo máximo de um segundo, pois ela odeia esperar.

Entrada
A primeira linha da entrada contém um inteiro N (1 ≤ N ≤ 200),
* correspondente ao número de casos de teste.
* Seguem N linhas, cada uma contendo um valor inteiro X (1 < X < 231)
* que pode ser ou não, um número primo.

Saída
Para cada caso de teste imprima a mensagem “Prime” (Primo) ou “Not Prime” (Não Primo),
* de acordo com o exemplo abaixo.
* */