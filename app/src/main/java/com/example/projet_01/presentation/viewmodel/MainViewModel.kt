package com.example.projet_01.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projet_01.data.remote.KtorWeatherApi
import com.example.projet_01.data.remote.TempEntity
import com.example.projet_01.data.remote.Weather
import com.example.projet_01.data.remote.WeatherEntity
import com.example.projet_01.data.remote.WindEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

suspend fun main(){
    val viewModel = MainViewModel()
    viewModel.loadWeathers("Pantin")
    //Affichage de la liste (qui doit être remplie) contenue dans la donnée observable

    // Attendre que runInProgress repasse à false
    // ceci pour montrer qu'on ne bloque pas le thread principal. Dans la vraie vie, on ne fera pas de while
    while (viewModel.runInProgress.value){
        println("attente...")
        delay(500)
    }

    println("""
        List : ${viewModel.dataList.value} 
        Erreur : ${viewModel.errorMessage.value}
        """.trimIndent()
    )

    //Pour que le programme s'arrête, inutile sur Android
    KtorWeatherApi.close()

    //attente V2
    //viewModel.runInProgress.collect {
    //    //Affichage de la liste ou du message d'erreur
    //    println("List : ${viewModel.dataList.value}")
    //}
}

class MainViewModel : ViewModel() {
    //MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<WeatherEntity>())
    val runInProgress = MutableStateFlow(false)
    val errorMessage = MutableStateFlow("")

    init {//Création d'un jeu de donnée au démarrage
        println("Instanciation de MainViewModel")
        loadFakeData()
    }

    fun loadFakeData(runInProgress :Boolean = false, errorMessage:String = "" ) {
        this.runInProgress.value = runInProgress
        this.errorMessage.value = errorMessage
        dataList.value = listOf(
            WeatherEntity(
                id = 1,
                name = "Paris",
                main = TempEntity(temp = 18.5),
                weather = listOf(
                    Weather(description = "ciel dégagé", icon = "https://picsum.photos/200")
                ),
                wind = WindEntity(speed = 5.0)
            ),
            WeatherEntity(
                id = 2,
                name = "Toulouse",
                main = TempEntity(temp = 22.3),
                weather = listOf(
                    Weather(description = "partiellement nuageux", icon = "https://picsum.photos/201")
                ),
                wind = WindEntity(speed = 3.2)
            ),
            WeatherEntity(
                id = 3,
                name = "Toulon",
                main = TempEntity(temp = 25.1),
                weather = listOf(
                    Weather(description = "ensoleillé", icon = "https://picsum.photos/202")
                ),
                wind = WindEntity(speed = 6.7)
            ),
            WeatherEntity(
                id = 4,
                name = "Lyon",
                main = TempEntity(temp = 19.8),
                weather = listOf(
                    Weather(description = "pluie légère", icon = "https://picsum.photos/203")
                ),
                wind = WindEntity(speed = 4.5)
            )
        ).shuffled() //shuffled() pour avoir un ordre différent à chaque appel
    }

    fun loadWeathers(cityName:String){
        // au choix : ici ou dans KtorWeatherApi.loadWeathers
        //if(cityName.length < 3 ){
        //    throw Exception("Veuillez renseigner une ville")
        //}
        runInProgress.value = true
        //récupérer des données et les mettre dans dataList
        // viewModelScope.launch : on liste les actions à lancer mais qui ne doivent pas être bloquantes
        viewModelScope.launch(Dispatchers.IO){
            //Dispatchers.IO = thread secondaire
            try {
                dataList.value = KtorWeatherApi.loadWeathers(cityName)
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.value = e.message ?: "An error occurred"
            } finally {
                //intéret du finally : s'execute meme si on met un return dans le try
                runInProgress.value = false
            }

        }
    }
}