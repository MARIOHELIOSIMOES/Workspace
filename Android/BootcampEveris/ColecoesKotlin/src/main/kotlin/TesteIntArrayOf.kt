class TesteIntArrayOf {
    fun teste(){
        val values = intArrayOf(2,3,4,0, 15, 3,22)
        values.forEach { print(it.toString()+(" ")) }

    }

}
fun main(){
    val t = TesteIntArrayOf()
    t.teste()
}