package com.example.projet_01.exo

fun main() {
    exo1("COUCOU", 239, Pair(5, 19) )
    val maman = UserEntity("AGabrielle", 46)
    val papa = UserEntity("Fabien", 42)
    exo2(papa, maman)
}

fun exo1(text:String, minutes:Int, pair:Pair<Int, Int>){
    println("--- Exo 1 avec params $text, $minutes, $pair---")

    //lower
    val lower: (String) -> String = { it.lowercase() }
    println(lower(text))

    val hour: (Int) -> Int = { it / 60 }
    println( "$minutes minutes donnent " + hour(minutes) + " heures")

    val max: (Pair<Int, Int>) -> Int = { it.first.coerceAtLeast(it.second) }
    println("Le max de $pair est " + max(pair))

    val reverse: (String) -> String = { it.reversed() }
    println(reverse(text))

    var minToMinHour: ((Int?) -> Pair<Int, Int>?)? = {
        println("--- Exo 2 : minToMinHour $it minutes ---")
        if (it != null)
            Pair(it / 60, it%60)
        else
            null
    }
    println(minToMinHour?.invoke(190))
    minToMinHour = null

    var minToMinHour2: ((Int?) -> Pair<Int, Int>?)? = {
        println("--- Exo 2 : minToMinHour $it minutes ---")
        it?.let { Pair(it / 60, it%60) }
    }
    println(minToMinHour2?.invoke(239))
    minToMinHour2 = null
}

fun exo2(papa: UserEntity, maman: UserEntity) {

    val compareUsersByName :(UserEntity, UserEntity)->UserEntity =
        { u1, u2 ->  if( u1.name.lowercase() <= u2.name.lowercase()) u1 else u2 }

    val compareUsersByOld :(UserEntity, UserEntity)->UserEntity =
        {u1, u2 -> if( u1.old >= u2.old ) u1 else u2}

    println("ordre alpha : " + compareUsersByName(papa, maman))
    println("le + vieux est : " + compareUsersByOld(papa, maman))
}

data class UserEntity(
    val name: String,
    val old: Int,
)