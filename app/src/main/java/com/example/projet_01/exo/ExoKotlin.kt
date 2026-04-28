package com.example.projet_01.exo

import kotlin.random.Random

fun main() {
    println("Hello World")
    val v1: String = "toto 1"
    println(v1.uppercase())

    val v2: String? = "toto 2"
    println(v2?.uppercase())

    val v3: String? = null
    println(v3?.uppercase())

    var v4: Int? = null
    if(Random.nextBoolean()){
        v4 = Random.nextInt(10)
    }
    println(v4 ?: "Pas de valeur")

    val v5:String = v3+v3
    println(v5.uppercase())

    if(v3 === null || v3 === "" || v3.trim()==="" ){
        println("toto")
    }

    var prix: Double = boulangerie(nbCroissants=2)
    println(boulangerie(nbCroissants=2))
    println(boulangerie(nbBaguettes=2, nbSandwiches=3))

}

fun boulangerie(nbCroissants:Int = 0, nbBaguettes:Int=0, nbSandwiches:Int=0) = nbCroissants*PRIX_CROISSANT + nbBaguettes*PRIX_BAGUETTE + nbSandwiches*PRIX_SANDWICH