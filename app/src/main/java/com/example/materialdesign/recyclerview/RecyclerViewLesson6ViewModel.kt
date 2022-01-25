package com.example.materialdesign.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.recyclerview.model.PlanetModel
import com.example.materialdesign.recyclerview.model.SampleListItem
import com.example.materialdesign.recyclerview.model.StarModel
import java.util.*

private const val earthPictureUrl = "https://findicons.com/files/icons/144/web/256/earth.png"
private const val venusPictureUrl = "https://ichef.bbci.co.uk/news/800/cpsprodpb/114B6/production/_118783807_d46ff476-316c-4dc6-ac82-42ef03ae0bf2.jpg.webp"
private const val marsPictureUrl = "https://findicons.com/files/icons/475/solar_system/256/mars.png"

class RecyclerViewLesson6ViewModel : ViewModel() {

    private val itemsLiveData = MutableLiveData<List<SampleListItem>>(emptyList())
    private val messagesLiveData = MutableLiveData<String>()

    fun getItems(): LiveData<List<SampleListItem>> {
        return itemsLiveData
    }

    fun getMessages(): LiveData<String> {
        return messagesLiveData
    }

    fun loadData() {

        // можно было бы затянуть из БД, но у нас проверка знаний по recyclerView
        // опишем планеты
        val planet1 = PlanetModel(
            id = UUID.randomUUID().toString(),
            "Земля",
            earthPictureUrl,
            "Наша планета"
        )
        val planet2 = PlanetModel(
            id = UUID.randomUUID().toString(),
            "Венера",
            venusPictureUrl,
            "Злая планета"
        )
        val planet3 = PlanetModel(
            id = UUID.randomUUID().toString(),
            "Марс",
            marsPictureUrl,
            "Наш будущий дом"
        )

        // опишем звезды
        val star1 = StarModel(
            id = UUID.randomUUID().toString(),
            "Солнце",
            "Млечный путь",
            "Наш спаситель"
        )
        val star2 = StarModel(
            id = UUID.randomUUID().toString(),
            "Сириус",
            "Большой Пес",
            "ярче солнца"
        )
        val star3 = StarModel(
            id = UUID.randomUUID().toString(),
            "Большая медведица",
            "хз",
            "очень далеко"
        )

        // соберем все в список
        val items = listOf(
            planet1,
            planet2,
            planet3,
            star1,
            star2,
            star3
        )

        itemsLiveData.value = items
    }

    // слушатели

    fun onClickPlanet(planetModel: PlanetModel) {
        messagesLiveData.value = planetModel.name
    }

    fun onClickStar(starModel: StarModel) {
        messagesLiveData.value = starModel.description
    }

    fun onItemMoved(from: Int, to: Int) {
        val newMutableList = requireCurrentList().toMutableList()
        Collections.swap(newMutableList, from, to)
        itemsLiveData.value = newMutableList
    }

    fun onItemRemoved(position: Int) {
        val newMutableList = requireCurrentList().toMutableList()
        newMutableList.removeAt(position)
        itemsLiveData.value = newMutableList
    }

    private fun requireCurrentList(): List<SampleListItem> {
        return itemsLiveData.value ?: throw IllegalStateException("список пустой")
    }
}