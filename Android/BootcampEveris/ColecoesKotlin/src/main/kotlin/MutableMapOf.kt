fun main(){
    var mm: MutableMapOf
    var f1 = funcionarioModel("mario", 1)
    var f2 = funcionarioModel("helio", 2)
    var f3= funcionarioModel("simoes", 3)
    //mm.imprimir(f1)
}

class MutableMapOf{
    var lista = mutableMapOf<String, funcionarioModel> ()
    fun create(chave: String, valor:funcionarioModel){
        lista[chave] = valor
    }
    fun getByChave(it: String) {
        lista[it]
    }
    fun imprimir(it: funcionarioModel){
      //  printf("ID: %d Nome: %s\n", it.id, it.nome)
    }

}
class funcionarioModel(
    var nome: String,
    var id: Int
)