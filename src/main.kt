val expressaoRegular = Regex("[0-5]")
val validarNome = Regex("[A-Za-z]")
//Variavel Globar
// Instância de uma lista vazia
var listaConvidados : MutableList<Convidado> = mutableListOf<Convidado>()

fun main(){
     menu()
}


 private fun menu() {
     do {
         println("--- MENU ----")
         println("1 - CADASTRAR")
         println("2 - LISTAR")
         println("3 - EDITAR")
         println("4 - EXCLUIR")
         println("5 - BUSCA")
         println("0 - SAIR")
         val opcao = readln()


         if(expressaoRegular.matches(opcao)){
             when (opcao.toInt()) {//Opcões do MENU!
                 1 -> {
                     println("Cadastrando...")
                     cadastrar()
                 }

                 2 -> {
                     println("Listando...")
                     listar()
                 }

                 3 -> {
                     println("Editando...")
                     editar()
                 }

                 4 -> {
                     println("Excluindo...")
                     excluir()
                 }

                 5 ->{
                     println("Buscando")
                     busca()
                 }

                 0 -> {
                     println("Saindo")
                 }
             }
         }else {
             println("\n\n\nOpcão Invalidada")
         }

     } while (opcao != "0")
 }

// valide para que o usuario digite letras para escrever o nome
 private fun cadastrar(){
     //Instancia da classe
     var convidado = Convidado()

     print("Qual o seu nome? ")
    // val nome = readln()
     convidado.nome = readln()
    if(!convidado.nome.contains(validarNome)){
        println("Nome Invalido...")
        return
    }

     print("Qual vai ser o presente? ")
    // val presente = readln()
     convidado.presente = readln()

     print("Qual sua restrição alimentar? ")
     //val alimento  = readln()
     convidado.alimentar = readln()

     listaConvidados.add(convidado)

 }
/* fun algumacoisa(): Tipo esperando que a funcão receba esse tipo no final dela*/

private fun listar() {
    var i = 0
    if (validar()) {

        listaConvidados.forEach { convidado ->
            print(
                "Possição: ${i++}" +
                        "Nome: ${convidado.nome} ;" +
                        "Presente: ${convidado.presente} ; " +
                        " Restrição: ${convidado.alimentar} ; " +
                        "Vai ir a festa: ${convidado.presenca} ;\n"
            )
        }
    }
}

private fun editar(): Boolean {
    if (validar()) {
        listar()

        println("Digite a posição a ser editada:")
        val posicao = readln().toInt()

        println("O convidado vai? S/N")
        val resposta = readln()
        when (resposta) {
            "S" -> listaConvidados[0].presenca = true

            "N" -> listaConvidados[0].presenca = false
        }
    }
    return true
}



private fun excluir(): Boolean {
    if (validar()) {
        listar()

        println("Digite a posição a ser removida:")
        val posicao = readln().toInt()

        listaConvidados.removeAt(posicao)

        println("Convidado excluido")
    }
    return true
}

private fun busca() {
    var i = 0
    print("Digite o nome da pessoa que você busca: ")
    val busca = readln()
    listaConvidados.forEach { convidado ->
        //Busca uma String dentro de uma outra String
        if (convidado.nome.contains(busca)) {
            println("Posição: $i, Nome: ${convidado.nome}")
        }
        i++
    }
}

private fun validar(): Boolean {
    if (listaConvidados.isEmpty()) {
        println("Lista vazia! Finalizando ...")
        return false
    }
    return true
}
