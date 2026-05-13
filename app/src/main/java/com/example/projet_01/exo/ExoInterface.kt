package com.example.projet_01.exo

interface ActionI {
    fun travail(tache: String)
    fun pause()
}

class Ouvrier: ActionI {
    override fun travail(tache: String) {
        println("je m'occupe de la tâche '$tache'")
    }

    override fun pause() {
        println("je suis en pause")
    }

}

class Responsable(val executant: ActionI): ActionI {

    override fun travail(tache: String) {
        println("j'ai transmis la tâche '$tache' à mon collègue")
        executant.travail(tache)
    }

    override fun pause() {
        if(executant !is Ouvrier){
            println("j'ai transmis la pause")
            executant.pause()
        }
    }

}

fun main() {
    val c = Responsable(Responsable(Ouvrier()))
    c.travail("Ranger")
    c.pause()
}