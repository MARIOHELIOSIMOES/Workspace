fun main(){
    val salarios = doubleArrayOf(1000.0, 2343.3, 1754.34)
    //println("\nArray double")
    for(valor in salarios){
        print(valor.toString()+", ")
    }

    println("\nMaior Salario: " + salarios.maxOrNull())
    println("Menor Salario: " + salarios.minOrNull())
    println("Media Salario: " + salarios.average())

    println("Filtrando maior que 1500")
    val filtrador = salarios.filter { it > 1500 }
    filtrador.forEach{print(it.toString()+" ")}

    println("\nQuantidade dentro de um range:")
    print(salarios.count{
        it in 1500.0 .. 4000.0

    })
    println("\nFind:")
    print(salarios.find{
        it==2280.642

    })
    println("\nAny:")
    print(salarios.any{
        it==2280.63

    })
}