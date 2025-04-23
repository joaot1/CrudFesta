val expressaoRegular = Regex("[0-5]")

// instância de uma lista mutável vazia.
var listaConvidados : MutableList<Convidado> = mutableListOf<Convidado>()
fun main() {

    menu()


}

private fun menu() {
    do {
        println("---MENU---")
        println("1- CADASTRAR")
        println("2- LISTAR")
        println("3- EDITAR")
        println("4- EXCLUIR")
        println("5- BUSCAR")
        println("0- SAIR")

        val opcao = readln()// precisa ser string por causa do REGEX
        if(expressaoRegular.matches(opcao)) {
            when (opcao.toInt()) {

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
                5 -> {
                    println("Buscando")
                    busca()
                }
                0 -> print("SAINDO...")
            }
        }else {
            println("\n\n\nOpção Invalida!\n")
        }
    } while (opcao != "0")
}

private fun cadastrar() {
    val regex = Regex("^[A-ZÀ-ÖØ-Ÿ][a-zà-ÿ ]+$")
    var nome: String
    // instancia
    val convidado = Convidado()
    do {
        println("Qual o seu nome? ")
        nome = readln()

        if (regex.matches(nome)) {
            convidado.nome = nome
        } else {
            println("Nome Invalido!")
            println("O nome deve conter a primeira letra MAIUSCULA \n" +
                    "e somente letras.")
        }
    }while (!regex.matches(nome))

    println("Qual é seu presente?")
    convidado.presente = readln()

    println("Qual sua restrição alimentar? ")
    convidado.alimentar = readln()
    listaConvidados.add(convidado)
}

private fun listar(){
    var i = 0

    if (validar()) {
        listaConvidados.forEach { convidado ->
            println(
                "-------------------------------------------\n" +
                        "Posição: ${i++} \n"+
                        "Nome: ${convidado.nome}; \n" +
                        "Presente: ${convidado.presente}; \n"  +
                        "Restrição: ${convidado.alimentar}; \n" +
                        "Vai ir a festa? ${convidado.presenca}\n" +
                        "-------------------------------------------\n"
            )
        }
    }
}

// questao 2 - validar que a posicao seja sempre numerica
// e a variavel resposta, seja sempre s/n ( string s/n )

private fun editar() : Boolean {
    if (validar()) {
        val regexNum = Regex("[0-9]")
        val regexResposta = Regex("^[sSnN]$")
        var posicao : String

        do {
            println("Digite a posição a ser editada")
            posicao = readln()

            if (regexNum.matches(posicao)) {

                if (posicao.toInt() in listaConvidados.indices) {
                    println("O convidado vai? S/N")
                    val resposta = readln()

                    if (regexResposta.matches(resposta)) {
                        when (resposta.uppercase()) {
                            "S" -> listaConvidados[posicao.toInt()].presenca = true
                            "N" -> listaConvidados[posicao.toInt()].presenca = false

                        }
                        return true
                    } else {
                        println("Coloque uma resposta válida")
                    }
                } else {
                    println("Posição inválidade, ela não existe na lista!")
                }
            }else {
                println("Posição inválida, não é um número")
            }
        }while (true)

    }
    return false
}

// questao 3 - só pode digitar numeros.

private fun excluir(): Boolean {
    if (validar()) {
        listar()
        var posicao: String
        val regexNum = Regex("^[0-9]+$")
        var excluiu = false

        do {
            println("Quem deseja remover? (Digite a posição, ou 'N' para cancelar)")
            posicao = readln().uppercase()

            if (posicao == "N") {
                println("Exclusão cancelada.")
                break
            }

            if (regexNum.matches(posicao)) {
                try {
                    val posicao = posicao.toInt()
                    if (posicao in listaConvidados.indices) {
                        listaConvidados.removeAt(posicao)
                        println("Convidado excluído")
                        excluiu = true
                        break
                    } else {
                        println("Posição inválida, não existe na lista!")
                    }
                } catch (e: NumberFormatException) {
                    println("Posição inválida, digite um número!")
                }
            } else {
                println("Entrada inválida, digite um número ou 'N'.")
            }
        } while (!excluiu)

        return excluiu
    }
    return false
}

// questao 4 -  só pode entrar letras na busca
// e ignorar letrar maiusculas // minusculas

private fun busca(): Boolean {
    println("Digite o nome para buscar:")
    var busca: String
    val regex = Regex("^[a-zA-ZÀ-ÖØ-ÿ ]+$")
    var encontradoAlgum = false

    do {
        busca = readln()
        if (regex.matches(busca) || busca.isEmpty()) {
            var encontradoNestaBusca = false
            listaConvidados.forEachIndexed { index, convidado ->
                if (convidado.nome.lowercase().contains(busca.lowercase())) {
                    encontradoNestaBusca = true
                    encontradoAlgum = true
                    if (convidado.presenca) {
                        println("Posição $index, Nome: ${convidado.nome} (Confirmado) vai para a festa")
                    } else {
                        println("Posição $index, Nome: ${convidado.nome} (Não Confirmado)")
                    }
                }
            }
            if (!encontradoNestaBusca && busca.isNotEmpty()) {
                println("Nenhum convidado com o nome \"$busca\" encontrado.")
            }
            break
        } else {
            println("Entrada inválida. Digite apenas letras para a busca.")
            println("Tentar novamente? (S/N)")
            val tentarNovamente = readln().uppercase()
            if (tentarNovamente != "S") {
                break
            }
        }
    } while (true)
    return encontradoAlgum
}

private fun validar() : Boolean {
    if(listaConvidados.isEmpty()) {
        println("A lista esta vazia! Finalizando...")
        return false
    }
    return true
}