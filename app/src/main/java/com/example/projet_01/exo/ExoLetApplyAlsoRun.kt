package com.example.projet_01.exo

import java.util.Calendar

// version classique
fun createPairTime(): Pair<Long, Long> {
    val startOfDay = Calendar.getInstance()
    startOfDay.set(Calendar.MINUTE, 0)
    startOfDay.set(Calendar.HOUR_OF_DAY, 0)
    val time = startOfDay.timeInMillis / 1000

    val endOfDay = Calendar.getInstance()
    endOfDay.set(Calendar.MINUTE, 59)
    endOfDay.set(Calendar.HOUR_OF_DAY, 23)
    val timeEnd = endOfDay.timeInMillis / 1000

    return Pair(time, timeEnd)
}

// version avec apply
fun createPairTimeWithApply() = Pair(
    Calendar.getInstance().apply {
        set(Calendar.MINUTE, 0)
        set(Calendar.HOUR_OF_DAY, 0)
    }.timeInMillis / 1000,
    Calendar.getInstance().apply {
        set(Calendar.MINUTE, 59)
        set(Calendar.HOUR_OF_DAY, 23)
    }.timeInMillis / 1000
)

// version avec also
fun createPairTimeWithAlso() = Pair(
    Calendar.getInstance().also {
        it.set(Calendar.MINUTE, 0)
        it.set(Calendar.HOUR_OF_DAY, 0)
    }.timeInMillis / 1000,
    Calendar.getInstance().also {
        it.set(Calendar.MINUTE, 59)
        it.set(Calendar.HOUR_OF_DAY, 23)
    }.timeInMillis / 1000
)

// version avec let
fun createPairTimeWithLet() = Pair(
    Calendar.getInstance().let {
        it.set(Calendar.MINUTE, 0)
        it.set(Calendar.HOUR_OF_DAY, 0)
        it.timeInMillis / 1000
    },
    Calendar.getInstance().let {
        it.set(Calendar.MINUTE, 59)
        it.set(Calendar.HOUR_OF_DAY, 23)
        it.timeInMillis / 1000
    }
)

// version avec run
fun createPairTimeWithRun() = Pair(
    Calendar.getInstance().run {
        set(Calendar.MINUTE, 0)
        set(Calendar.HOUR_OF_DAY, 0)
        timeInMillis / 1000
    },
    Calendar.getInstance().run {
        set(Calendar.MINUTE, 59)
        set(Calendar.HOUR_OF_DAY, 23)
        timeInMillis / 1000
    }
)

fun main() {
    println("dayTime=${createPairTime()}")
    println("dayTime2=${createPairTimeWithApply()}")
    println("dayTime3=${createPairTimeWithAlso()}")
    println("dayTime4=${createPairTimeWithLet()}")
    println("dayTime5=${createPairTimeWithRun()}")
}