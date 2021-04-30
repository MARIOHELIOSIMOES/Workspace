package solucionandoproblemas

fun main() {
    val n = readLine()!!.toInt()

    for(i in 1..n){
        val numeros = readLine()!!.split(" ")
        println(numeros.get(0).toBigInteger().gcd(numeros.get(1).toBigInteger()))
    }

}