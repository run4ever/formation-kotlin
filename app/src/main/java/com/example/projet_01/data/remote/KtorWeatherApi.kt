package com.example.projet_01.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

suspend fun main() {
    val city = "Lens"
    val weathers : List<WeatherEntity> = KtorWeatherApi.loadWeathers(city)

    println("Stations Météo de $city : ")
    for (station in weathers){
        println("""
            -----------------
            Id: ${station.id}
            Nom : ${station.name}
            Resume : ${station.getResume()}
        """.trimIndent())
    }

    KtorWeatherApi.close()
}

object KtorWeatherApi {
    private const val API_URL_BASE =
        "https://api.openweathermap.org/data/2.5/find?q=";

    private const val API_URL_PARAMS =
        "&appid=b80967f0a6bd10d23e44848547b26550&units=metric&lang=fr"


    private val client  = HttpClient {
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.INFO  // TRACE, HEADERS, BODY, etc.
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
        }
        install(HttpTimeout) {
            requestTimeoutMillis = 5000
        }
    }

    suspend fun loadWeathers(city:String): List<WeatherEntity> {
        delay(2000)
        val response = client.get(API_URL_BASE + city + API_URL_PARAMS){
        }
        if (!response.status.isSuccess()) {
            throw Exception("Erreur API: ${response.status} - ${response.bodyAsText()}")
        }

        //onEach est un forEach qui retourne la collection, alors que forEach ne retourne rien
        return response.body<WeatherInMyCity>().list.onEach { it -> it.weather.forEach { it -> it.icon = "https://openweathermap.org/img/wn/${it.icon}@4x.png" } }
    }


    fun close() = client.close()

}


@Serializable
data class WeatherInMyCity(
    val message: String,
    val cod: String,
    val count: Int,
    val list: List<WeatherEntity>,
)

@Serializable
data class WeatherEntity(
    val id: Int,
    val name: String,
    val weather: List<Weather>,
    val wind: Wind,
    val main: Main,
){
    fun getResume():String = """
        Il fait ${main.temp}°C à ${name} (id=${id}) avec un vent de ${wind.speed} m/s
        - Description : ${weather.firstOrNull()?.description}
        - Icone : ${weather.firstOrNull()?.icon}
        """.trimIndent()
}

@Serializable
data class Weather(
    val id: Int,
    val main: String,
    val description: String,
    var icon: String,
)

@Serializable
data class Main(
    val temp: Double,
)

@Serializable
data class Wind(
    val speed: Double,
)