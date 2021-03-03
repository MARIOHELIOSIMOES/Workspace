class TesteIntArray {

    fun teste(){
        val values = IntArray(5)
        values[0] = 1
        values[1] = 22
        values[2] = 3
        values[3] = 44
        values[4] = 5


        println("\n-------- for(valor in values )---------")
        for (valor in values){
            println(valor)
        }
        println("\nvalues.forEach { println(it) }")
        values.forEach { println(it) }

        println("\nfor(index in values.indices)")
        for(index in values.indices){
            println(values[index])
        }
        println("\n---------------sort--------------")
        values.sort()
        for(index in values.indices){
            println(values[index])
        }


    }



}
fun main(){
    val t = TesteIntArray()
    t.teste()

}