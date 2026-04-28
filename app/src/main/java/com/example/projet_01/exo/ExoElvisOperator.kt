package com.example.projet_01.exo


fun main() {
    println(scanText("ca va ?"))
    println(scanNumber("combien ?"))
    println(whatDoYouWant().toString() + " euros svp")
}

fun scanText(question:String): String{
    println(question);
    return readlnOrNull() ?: "No answer"
}

fun scanNumber(question:String): Int {
    println(question)
    var answer: String? = readlnOrNull()
    return answer?.toIntOrNull() ?: 0
}

fun whatDoYouWant():Double{
    println("Combien voulez-vous de croissants ?")
    val a1: String? = readlnOrNull()
    val nbCroissants = a1?.toIntOrNull() ?: 0

    println("Combien voulez-vous de baguettes ?")
    val a2: String? = readlnOrNull()
    val nbBaguettes = a2?.toIntOrNull() ?: 0

    println("Combien voulez-vous de sandwiches ?")
    val nbSandwiches: Int = readlnOrNull()?.toIntOrNull() ?: 0

    return boulangerie(nbCroissants, nbBaguettes, nbSandwiches)
}