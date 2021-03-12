fun main(){
    val f1 = funcionario("Mario", 1650.0f)
    val f2 = funcionario("helio", 2000f)
    val f3 = funcionario("simoes", salario = 1300f)


    var lista = mutableListOf<funcionario>()
    lista.add(f1)
    lista.add(f2)
    lista.add(f3)
    lista.forEach{ println(it.nome +" - " +it.salario)}
    lista.removeAt(0)
    lista.forEach{ println(it.nome +" - " +it.salario)}

}

class funcionario(
    var nome:String = "Funcionario",
    var salario:Float = 1000.0f
)