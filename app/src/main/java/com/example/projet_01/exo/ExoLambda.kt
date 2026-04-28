package com.example.projet_01.exo

import kotlin.math.abs

fun main() {
    exo1("COUCOU", 239, Pair(5, 19) )
    val user1 = UserEntity("Fabien", 42)
    val user2 = UserEntity("Gabrielle", 46)
    val user3 = UserEntity("Axel", 23)
    exo2(user1, user2, user3)
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

fun exo2(user1: UserEntity, user2: UserEntity, user3: UserEntity) {

    val compareUsersByName :(UserEntity, UserEntity)->UserEntity =
        { u1, u2 ->  if( u1.name.lowercase() <= u2.name.lowercase()) u1 else u2 }

    val compareUsersByOld :(UserEntity, UserEntity)->UserEntity =
        {u1, u2 -> if( u1.old >= u2.old ) u1 else u2}

    val near30 :(UserEntity, UserEntity)->UserEntity =
        {u1, u2 -> if( abs(u1.old-30) <= abs(u2.old-30) ) u1 else u2}

    println("ordre alpha : " + compareUsersByName(user1, user2))
    println("le + vieux est : " + compareUsersByOld(user1, user2))
    println("compareUsers par age : " + compareUsers(user1, user2, user3, compareUsersByOld))
    println("compareUsers par nom : " + compareUsers(user1, user2, user3, compareUsersByName))
    println("Le + proche de 30 ans : " + compareUsers(user1, user2, user3, near30))
}

fun compareUsers(u1 : UserEntity, u2  :UserEntity, u3 : UserEntity, comparator : (UserEntity, UserEntity)->UserEntity ) : UserEntity {
   return comparator(u3, comparator(u1, u2))
}


data class UserEntity(
    val name: String,
    val old: Int,
)