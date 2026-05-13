package com.example.projet_01.exo

import kotlinx.serialization.json.Json

fun String.println() = println(this)

fun CarEntity.println() = println("${this.marque} ${this.modele} ${this.couleur}")

fun CarEntity.toJson() = Json.encodeToString(this)

fun main() {
    "Coucou".println()
    CarEntity("Seat", "Leon").println()
    CarEntity("Seat", "Leon").toJson().println()
}