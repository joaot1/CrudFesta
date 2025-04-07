val expressaoRegular = Regex("[0-5]")
val validarNome = Regex("^[a-zA-ZÀ-ÿ ]+$")
val validarNumero = Regex("^[0-9]+$")
val validarResposta = Regex("^[SN]+$")
val validarBusca = Regex("^[a-zA-Z]+$", RegexOption.IGNORE_CASE)

//Variavel Globar
// Instância de uma lista mutável vazia
var listaConvidados : MutableList<Convidado> = mutableListOf<Convidado>()
val lista = mutableListOf("b", "a", "c", "e", "d")

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
         val opcao = readln()//Aqui precisar ser String. Por causa do REGEX


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

     } while (opcao != "0")//Precisa ser String
 }


// QUESTÃO 1 - Valida para que o usuario somente digite LETRAS para escrever o nome
 private fun cadastrar(){
     //Instancia da classe
     var convidado = Convidado()
    //Inicio do Regex de validação de NOME
do{
     print("Qual o seu nome? ")
     val nome = readln()
    if(validarNome.matches(nome)){
        convidado.nome = nome
        break
    } else{
        println("Nome Inválido! Use apenas Letras.")
    }
}while (true)

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
            )//Fim do PRINT
        }//Fim do For Each
    }//Fim do IF
    return  // retorno da função
}//Fim da função listar

//Questão 2 - Validar para que a variavel posição seja sempre numérica e a variavel resposta seja sempre "S" ou "N".
private fun editar(): Boolean {
    println("Digite a Posição a ser editada: ")
    val posicaoNumeros = readln()
    if(!validarNumero.matches(posicaoNumeros)){
        println("Posição Inválida! Use apenas Numeros.")
        return false
    }
    val posicao = posicaoNumeros.toInt()
    if (posicao >= listaConvidados.size){
      println("Posição inexistente na lista de convidados.")
      return false
    }

    println("O convidado vai? S/N")
    val resposta = readln()
    if(!validarResposta.matches(resposta)){
        println("Respota Inválida! Digite apenas S ou N.")
        return false
    }
    when(resposta.uppercase()){
        "S" -> listaConvidados[posicao].presenca = true
        "N" -> listaConvidados[posicao].presenca = false
    }
    return true
}


//Questão 3 - Validar para que a variavel posição seja sempre numérica
private fun excluir(): Boolean {
    if(!listaConvidados.isEmpty()){
        listar()
        println("Qual Posição você deseja remover: ")
        val posicaoStr = readln()
        if(!validarNumero.matches(posicaoStr)){
            println("Posição Inválida!")
            return false
        }
        val posicao = posicaoStr.toInt()
        if(posicao >= listaConvidados.size){
            println("Essa posição não existe. ")
            return false
        }
        listaConvidados.removeAt(posicao)
        println("Convidado excluído!")
    }
    return true
}

//Questão 4 - validar para que a variavel busca seja sempre alfabética, ignora letras maiusculas e minusculas
private fun busca() {

   print("Digite o nome da pessoa que você deseja buscar: ")
    val busca = readln()
    if(!validarBusca.matches(busca)){
        println("Busca Inválida! Use apenas Letras.")
        return
    }

    var i = 0
    listaConvidados.forEach { convidado ->
        if(convidado.nome.contains(busca, ignoreCase = true)){
            println("Posição: $i, Nome: ${convidado.nome} ;")
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
