fun main(args: Array<String>) {
    println("Hello World!")
    val p = Pessoa("Nome 1", "Teste 2")
    p.imprimirNomeCompleto()

    p.setNomes("Mario ", "Simoes")
    p.imprimirNomeCompleto()
}
class Pessoa{
    var nome: String = "nome"
    var sobrenome: String = "sobrenome"

    constructor(nome: String, sobreNome: String){
        this.nome = nome
        this.sobrenome = sobrenome
    }



    fun setNomes(nome: String, sobreNome: String){
        this.nome = nome
        this.sobrenome = sobrenome
    }
    fun imprimirNomeCompleto(){
        println(nome.plus(sobrenome))
    }
}

