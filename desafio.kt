enum class Nivel {
    BASICO, INTERMEDIARIO, DIFICIL
}


 class Usuario(
    val nome: String
) {
    private val formacoes = mutableListOf<Formacao>()

    fun inscrever(formacao: Formacao) : Boolean {
        if (formacoes.contains(formacao)) return false
        return formacoes.add(formacao)
    }

    fun getFormacoes() : List<Formacao> = formacoes
}


 class Formacao(
    val nome: String,
    val conteudos: List<ConteudoEducacional>
) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        if (!usuario.inscrever(this)) return
        inscritos.add(usuario)
    }

    fun getInscritos() : List<Usuario> = inscritos
}

data class ConteudoEducacional(
    val nome: String,
    private val atividades: List<Atividade>
)

data class DesafioProjeto(
    override val nome: String,
    override val nivel: Nivel,
    val duracaoHrs: Int
) : Desafio()

 data class DesafioCodigo(
    override val nome: String,
    override val nivel: Nivel
) : Desafio()   
    
abstract class Desafio : Atividade() {
    abstract val nivel: Nivel
}

data class Curso(
    override val nome: String,
    val nivel: Nivel = Nivel.BASICO,
    val duracaoHrs: Int = 1
) : Atividade()

abstract class Atividade {
    abstract val nome: String
}

fun geradorFomacao() : Formacao {
    return Formacao("BootCamp", listOf(ConteudoEducacional("Dominando a Logica de programação", listOf(Curso("Introdução ao pensamento Computacional", Nivel.BASICO,1), 
           DesafioProjeto("Abstraindo Formações da DIO Usando Orientação a Objetos", Nivel.BASICO,2))), 
                                       ConteudoEducacional("Refinando Sua Técnica Com Desafios de Código em Kotlin", 
           listOf( DesafioCodigo("Explorando Mapas, Loops e Regras com Kotlin", Nivel.INTERMEDIARIO), DesafioCodigo("Utilizando a Keywokrd Object com Orientação a Objetos (OO)", Nivel.DIFICIL)))))
}

fun main() {
    val formacao = geradorFomacao()
    val usuario1 = Usuario("Jhonny")
    val usuario2 = Usuario("Walker")

    formacao.apply {
        matricular(usuario1)
        matricular(usuario2)
    
    }
    println(formacao.inscritos)
    println(usuario1.getFormacoes())
    println(usuario2.getFormacoes())
}
