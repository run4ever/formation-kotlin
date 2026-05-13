package com.example.projet_01.exo

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import kotlin.random.Random.Default.nextInt

class ResultEntity {
    val nbVoteGandalf = nextInt(10000)
    val nbVoteDumbledore = nextInt(10000)
    val nbVoteMerlin = nextInt(10000)
}

fun electionResultInDept(deptNumber: Int): ResultEntity {
    println("Le département : $deptNumber a répondu")
    return ResultEntity()
}

fun electionResult() {
    val start = System.currentTimeMillis()
    val results = ArrayList<Deferred<ResultEntity>>()
    var gandalf = 0
    var dumbledore = 0
    var merlin = 0

    runBlocking {
        repeat(100) {
            results.add(async { electionResultInDept(it) })
        }
        results.awaitAll().forEach {
            gandalf += it.nbVoteGandalf
            dumbledore += it.nbVoteDumbledore
            merlin += it.nbVoteMerlin
        }
    }

    val total = (gandalf + dumbledore + merlin).toFloat()

    println("""
        -------- TOTAL
        Gandalf : ${"%.2f".format(gandalf * 100 / total)} %
        Dumbledore : ${"%.2f".format(dumbledore * 100 / total)} %
        Merlin : ${"%.2f".format(merlin * 100 / total)} %
        """.trimIndent())

    println("Done in ${(System.currentTimeMillis() - start)}ms")
}

fun main() {
    electionResult()
}