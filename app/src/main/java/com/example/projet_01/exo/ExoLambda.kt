package com.example.projet_01.exo

import kotlin.math.abs

fun main3() {
    exo1("COUCOU", 239, Pair(5, 19) )
    val user1 = UserEntity("Fabien", 42)
    val user2 = UserEntity("Gabrielle", 46)
    val user3 = UserEntity("Axel", 23)
    exo2(user1, user2, user3)
}

fun main() {
    exo3()
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

data class PersonEntity(var name:String, var note:Int)

fun exo3(){
    val list = arrayListOf(
        PersonEntity ("toto", 16),
        PersonEntity ("Tata", 20),
        PersonEntity ("Toto", 8),
        PersonEntity ("Charles", 14)
    )

    //Créer une variable isToto contenant une lambda qui teste si un PersonEntity s'appelle Toto
    val isToto: (PersonEntity) -> Boolean = { it.name.equals("toto", true) }

    println("Afficher la sous liste de personne ayant 10 et +")
    //println(list.filter { it.note >=10 })
    //Pour un affichage de notre choix
    println(list.filter { it.note >=10 }.joinToString("\n") { "-${it.name} : ${it.note}"})

    println("\n\nAfficher combien il y a de Toto dans la classe ?")
    println(list.filter { it.name.equals("toto", ignoreCase= true) }.size)
    println(list.filter { isToto(it) }.size)

    println("\n\nAfficher combien de Toto ayant la moyenne (10 et +)")
    println(list.filter { it.name.equals("toto", ignoreCase= true) && it.note >= 10 }.size)
    println(list.filter { isToto(it) && it.note >= 10 }.size)

    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    val noteMoyenne = list.map{ it.note }.average()
    println("La moyenne est de $noteMoyenne")
    println(list.filter { it.name.equals("toto", ignoreCase= true) && it.note >= noteMoyenne }.size)
    println(list.filter { isToto(it) && it.note >= noteMoyenne }.size)
    //V2 : println(list.count { isToto(it) && it.note >= noteMoyenne })

    println("\n\nAfficher la list triée par nom sans doublon")
    println(list.sortedBy{it.name}.distinctBy { it.name.lowercase() })

    println("\n\nAjouter un point a ceux n’ayant pas la moyenne (<10)")
    println(list.filter { it.note < 10 }.onEach { it.note++ })

    println("\n\nAjouter un point à tous les Toto")
    println(list.filter { isToto(it) }.onEach { it.note++ })

    println("\n\nRetirer de la liste ceux ayant la note la plus petite. (Il peut y en avoir plusieurs)")
    var noteMin = list.map{ it.note }.min()
    //V2 : val noteMin = list.minOfOrNull { it.note }
    println("La note Min est de $noteMin")
    list.removeIf { it.note == noteMin }
    println(list)

    println("\n\nAfficher les noms de ceux ayant la moyenne(10et+) par ordre alphabétique")
    println(list.filter { it.note >=10 }.sortedBy{it.name}.map{it.name})

    println("\n\nDupliquer la liste ainsi que tous les utilisateurs (nouvelle instance) qu'elle contient")
    val copyList = list.map{it.copy()}

    println("\n\nAfficher par notes croissantes les élèves ayant eu cette note comme sur l'exemple")
    val text = list.groupBy { it.note } //On groupe par note
        .entries //la HashMap sous forme de List<Pair<Key , List<PersonEntity>>>
        .sortedBy { it.key } //On trie en fonction de la note
        .joinToString("\n") {//Pour chaque note
            //On met la note en préfixe et on affiche chaque nom
            it.value.joinToString(", ", "${it.key} : ") {it.name}
        }
    //on affiche
    println(text)

}