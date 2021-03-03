fun main(){
    val salarios = doubleArrayOf(1000.0, 2343.3, 1754.34)

    println("\nArray double")
    for(valor in salarios){
        print(valor.toString()+", ")
    }

    println("\nAjustando os valores com forEachIndexed")
    salarios.forEachIndexed{index, salario ->
        println("Index: "+ index+ " Salario: "+  salario)
        salarios[index] = salario * 1.3
    }

    //println("\nArray double")
    for(valor in salarios){
        print(valor.toString()+", ")
    }

}