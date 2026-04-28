package com.example.projet_01.exo

import java.util.Random
import kotlin.text.iterator


class CarEntity (var marque: String, var modele: String) {
    var couleur = "rouge"
    fun print() = println("C'est une $marque $modele de couleur $couleur")
}

class HouseEntity (var couleur: String, largeur: Int, longueur: Int) {
    var surface: Int = largeur * longueur

    init {
        println("La maison $couleur fait $surface m2")
    }
}

class PrintRandomIntEntity(var max : Int) {
    private var random: Random = Random()

    init {
        println("init")
        println( random.nextInt( max ) )
        println( random.nextInt( max ) )
        println( random.nextInt( max ) )
    }

    constructor() :this(100) {
        println( random.nextInt( max ) )
        println("2nd constructor")
    }
}

class ThermometerEntity(var min: Int, var max: Int, value: Int) {
    var value = value
        set(newValue) {
            field = if (newValue > max){
                max
            } else if(newValue < min){
                min
            } else {
                newValue
            }
        }

    //sert pour le dernier cas, mais peut être remplacé par "var value = value.coerceIn(min, max)" en ligne 35
    init {
        this.value = value
    }

    companion object {
        fun getCelsiusThermometer():ThermometerEntity = ThermometerEntity(-30,50,0)
        fun getFahrenheitThermometer():ThermometerEntity = ThermometerEntity(20,120,32)
    }
}

class RandomName(
)
{
    private val prenoms: ArrayList<String> = arrayListOf("Cyril", "Fabien", "Mathieu")
    private var lastNextCall: String = ""

    fun add(prenom:String) = if(prenom.isNotBlank() && prenom !in prenoms) prenoms.add(prenom) else false

    fun next(): String = prenoms.random()

    fun addAll(vararg prenoms: String){
        for (prenom in prenoms) {
            add(prenom)
        }
    }

    fun nextDiff(): String {
        var name:String = next()
        while (name == lastNextCall){
            name = next()
        }
        lastNextCall = name
        return lastNextCall
    }

    //version avec lambda expression filter
    fun nextDiff2():String {
        lastNextCall = prenoms.filter { it != lastNextCall }.random()
        return lastNextCall
    }

    //version function expression
    fun nextDiff3() = prenoms.filter { it != lastNextCall }.random().also { lastNextCall = it }

    fun next2(): Pair<String, String>{
        val p1 = nextDiff()
        val p2 = nextDiff()
        return Pair(p1, p2)
    }
}

enum class Appreciation(val texte: String?) {
    PAS_DE_NOTE(null),
    MAUVAIS("Mauvais"),
    MOYENNE("La moyenne"),
    BIEN("Bien"),
    TRES_BIEN("Tres biens")
}

fun appreciation(note:Int?): Appreciation = when(note) {
    null -> Appreciation.PAS_DE_NOTE
    in 0..9-> Appreciation.MAUVAIS
    10 -> Appreciation.MOYENNE
    in 11..15-> Appreciation.BIEN
    in 16..20-> Appreciation.TRES_BIEN
    else -> throw Exception("Bad number")
}

fun main1() {
    val seatLeonGrise = CarEntity("Seat", "Leon")
    seatLeonGrise.couleur = "grise"
    seatLeonGrise.print()
    HouseEntity("bleue", 3, 10)
    PrintRandomIntEntity(10)
    PrintRandomIntEntity()

    var t1 = ThermometerEntity(min=-20, max= 50, value =0)
    println("Température de ${t1.value}") // 0

    //Cas qui marche
    t1.value = 10
    println("Température de ${t1.value}") // 10 attendu

    //Borne minimum
    t1.value = -30
    println("Température de ${t1.value}") // -20 attendu

    //Borne maximum
    t1.value = 100
    println("Température de ${t1.value}") // 50 attendu

    //Pour les plus rapides : Cas de départ (ne passe pas par le setter
    // car le setter est uniquement appelé qd on modifie la valeur de value
    t1 = ThermometerEntity(min=-20, max= 50, value =-100)
    println("Température de ${t1.value}") // -20 attendu

    val tCelsius = ThermometerEntity.getCelsiusThermometer()
    tCelsius.value = 18
    println("La valeur est de ${tCelsius.value} (18 attentu)") //18
    tCelsius.value = -45
    println("La valeur est de ${tCelsius.value} (-30 attentu)") //-30

    val tFahrenheit = ThermometerEntity.getFahrenheitThermometer()
    tFahrenheit.value = 70
    println("La valeur est de ${tFahrenheit.value} (70 attentu)") //70
    tFahrenheit.value = 130
    println("La valeur est de ${tFahrenheit.value} (120 attentu)") //120
}

fun main2(){
    val randomName = RandomName()
    randomName.add("Anthony")
    //println(randomName.prenoms)
    randomName.addAll("Celine", "Esther")
    //println(randomName.prenoms)
    println(randomName.next())
    println("----nextDiff-----")
    println(randomName.nextDiff())
    println(randomName.nextDiff())
    println(randomName.nextDiff())
    println(randomName.nextDiff())
    println(randomName.nextDiff())
    println(randomName.nextDiff())
    println("----next2-----")
    println(randomName.next2())
}

fun main(){
    println(appreciation(48))
}