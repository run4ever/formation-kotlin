package com.example.projet_01.exo


fun sanslese(texte: String): String {
    var sortie = ""
    for (c in texte) {
        //V1
        if (c != 'e' && c != 'E') {
            sortie += c
        }
        //v2
        //if (c !in "eEèé") {
        //    sortie += c
        //}
    }
    return sortie
}

fun nba(texte: String): String {
    var nb = 0
    for (c in texte) {

        //v1
        if(c in "aAàäâ"){
            nb ++
        }

        //V2
//        if (c in arrayOf('a', 'A', 'à')) {
//            nb ++
//        }
    }
    return "$texte contient $nb A (sous toutes ses formes)"
}

fun reverse(text: String): String {
    var sortie = ""

    for (i in text.length - 1 downTo 0) {
        val char = text[i]
        sortie += char
    }

    return "$text a l'envers => $sortie"
}

fun nbMaj(texte: String): String {
    var nb = 0
    for (c in texte) {
        if(c in "ABCDEFGHIJKLMNOPQRSTUVWXZ"){
            nb ++
        }
    }
    return "$texte contient $nb majuscule(s)"
}

fun sansVoyelles(texte: String): String {
    var sortie = ""
    for (c in texte) {
        if(c !in "aeiouAEIOU"){
            sortie += c
        }
    }
    return "\"$texte\" sans voyelles => $sortie"
}

fun main() {
    println(sanslese("c'est la fete"))
    println(nba("aAàäâ"))
    println(reverse("fabien"))
    println(nbMaj("aAà"))
    println(sansVoyelles("le cours est fini"))

}