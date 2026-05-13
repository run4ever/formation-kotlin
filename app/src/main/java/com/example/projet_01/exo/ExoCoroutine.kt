package com.example.projet_01.exo

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.atomic.AtomicInteger

class BallotBoxEntityRoutine {
    private val number = AtomicInteger(0)

    suspend fun add1VoiceAndWaitWithDelay() {
        delay(2000)
        number.incrementAndGet()
    }

    fun add1VoiceAndWait() {
        Thread.sleep(2000)
        // Thread.sleep bloque le gestionnaire de threads,
        // chaque thread attend le précédent
        // Donc ne pas utiliser avec Coroutine
        number.incrementAndGet()
    }

    fun getNumber() = number
}

fun testBallotBox(callNumber:Int) {
    val start = System.currentTimeMillis()
    val box = BallotBoxEntityRoutine()

    runBlocking {
        repeat(callNumber){
            launch {
                box.add1VoiceAndWaitWithDelay()
                //box.add1VoiceAndWait()
            }
        }
    }

    println("Number : " + box.getNumber())
    println("Done in ${(System.currentTimeMillis() - start)/1000} seconds")
}

fun main() {
    testBallotBox(5)
}