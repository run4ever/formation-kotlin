package com.example.projet_01.exo

class MyPair<U, T>(var first: U, var second: T) {
    fun print(): Unit = println("first = $first,  second = $second")
}

class MyPair2<U: Number, T>(var first: U, var second: T) {
    fun print(): Unit = println("first = $first,  second = $second")
}

class MyPair3<out U, T>(val first: U, var second: T) {
    fun print(): Unit = println("first = $first,  second = $second")
}

fun main() {
    val myPair = MyPair(3, "Toto")
    val myPair2 = MyPair2(4, "Toto") // si on essaie de passer "4" au lieu de 4, erreur
    val myPair3 = MyPair3(4, "Toto") // si on essaie de passer "4" au lieu de 4, erreur
    myPair.first = 5 //Écriture output
    //myPair3.first = 5 //Ne fonctionne pas car out impose lecture seule
    myPair.print() //Lecture input
    myPair2.print() //Lecture input
}
