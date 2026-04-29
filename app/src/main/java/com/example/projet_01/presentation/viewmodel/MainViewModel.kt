package com.example.projet_01.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.projet_01.data.remote.KtorWeatherApi
import com.example.projet_01.data.remote.WeatherEntity
import kotlinx.coroutines.flow.MutableStateFlow

suspend fun main(){
    val viewModel = MainViewModel()
    viewModel.loadWeathers("Nice")
    //Affichage de la liste (qui doit être remplie) contenue dans la donnée observable
    println("List : ${viewModel.dataList.value}" )

    //Pour que le programme s'arrête, inutile sur Android
    KtorWeatherApi.close()
}

class MainViewModel : ViewModel() {
    //MutableStateFlow est une donnée observable
    val dataList = MutableStateFlow(emptyList<WeatherEntity>())

    suspend fun loadWeathers(cityName:String){
        //récupérer des données et les mettre dans dataList
        dataList.value = KtorWeatherApi.loadWeathers(cityName)
    }
}