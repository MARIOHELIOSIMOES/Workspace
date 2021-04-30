package solucionandoproblemas

fun main(){
    while(true){
        try {
            val n = readLine()!!.toInt()
            val velocidades = readLine()!!.split(' ')

            var n1 = 0
            var n2 = 0
            var n3 = 0
            for (v in velocidades){
                if(v.toInt()>=20){
                    n3++
                }else if( v.toInt()<10){
                    n1++
                }else{
                    n2++
                }
            }
            if(n3>0){
                println(3)
            }else if (n2>2){
                println(2)
            }else{
                println(1)
            }
        }catch (e :Exception){
            break;
        }

    }

}
/*
* Desafio
A corrida de tartarugas é um esporte que cresceu muito nos últimos anos,
* fazendo com que vários competidores se dediquem a capturar tartarugas rápidas,
* e treina-las para faturar milhões em corridas pelo mundo.
* Porém a tarefa de capturar tartarugas não é uma tarefa muito fácil,
* pois quase todos esses répteis são bem lentos.
* Cada tartaruga é classificada em um nível dependendo de sua velocidade:


Nível 1: Se a velocidade é menor que 10 cm/h .
Nível 2: Se a velocidade é maior ou igual a 10 cm/h e menor que 20 cm/h .
Nível 3: Se a velocidade é maior ou igual a 20 cm/h .

Sua tarefa é identificar qual o nível de velocidade da tartaruga mais veloz de um grupo.

Entrada
A entrada consiste de múltiplos casos de teste,
* e cada um consiste em duas linhas:
* A primeira linha contém um inteiro L (1 ≤ L ≤ 500) representando o número de tartarugas do grupo,
* e a segunda linha contém L inteiros Vi (1 ≤ Vi ≤ 50) representando as velocidades de cada tartaruga do grupo.

Saída
Para cada caso de teste,
* imprima uma única linha indicando o nível de velocidade da tartaruga mais veloz do grupo.
*
* */