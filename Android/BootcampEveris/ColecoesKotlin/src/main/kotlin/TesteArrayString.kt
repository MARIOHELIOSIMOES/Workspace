fun main(){
    val nomes = Array(3){ " "}
    nomes[0] = "Mario"
    nomes[1] = "Helio"
    nomes[2] = "Simoes"

    for(nome in nomes){
        print(nome+" ")
    }

    println("\n\nSort")
    nomes.sort()

    nomes.forEach { print(it+" ") }

    val nomes2 = arrayOf("Miguel", "Rodrigues", "Simoes")
    println("\nArray nome 2")
    for(nome in nomes2){
        print(nome+" ")
    }

    println("\n\nSort")
    nomes2.sort()

    nomes2.forEach { print(it+" ") }
}