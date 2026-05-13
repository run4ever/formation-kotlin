package com.example.projet_01.exo

import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

//Classe garantissant un compte ThreadSafe
class BallotBoxEntity {
    private val number = AtomicInteger(0)

    fun add1VoiceAndWait() {
        Thread.sleep(2000)
        number.incrementAndGet()
    }

    fun getNumber() = number
}

fun main() {
    val bbe = BallotBoxEntity()
    val before = System.currentTimeMillis()
    println("init : " + bbe.getNumber())
    bbe.add1VoiceAndWait()
    println("après ajout : " + bbe.getNumber())

    val t = thread {
        bbe.add1VoiceAndWait()
    }

    println("thread lancé : " + bbe.getNumber())
    t.join()
    println("thread terminé : " + bbe.getNumber())

    val threadsList = ArrayList<Thread>()
    repeat(4000){
        val t = thread {
            bbe.add1VoiceAndWait()
        }
        threadsList.add(t)
    }

    threadsList.forEach {
        it.join()
    }

    println("10 thread finis : " + bbe.getNumber())
    val after = System.currentTimeMillis()
    println("Done in ${after - before} ms")
}