package com.example.materialdesign.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.recyclerview.model.Notes
import com.example.materialdesign.recyclerview.model.SampleListItem
import com.example.materialdesign.recyclerview.model.Affairs
import java.util.*

// планеты
private const val earthPictureUrl = "https://findicons.com/files/icons/144/web/256/earth.png"
private const val venusPictureUrl = "https://ichef.bbci.co.uk/news/800/cpsprodpb/114B6/production/_118783807_d46ff476-316c-4dc6-ac82-42ef03ae0bf2.jpg.webp"
private const val marsPictureUrl = "https://findicons.com/files/icons/475/solar_system/256/mars.png"

//звезды
private const val sunStar = "https://st.depositphotos.com/1007168/1249/i/600/depositphotos_12492703-stock-photo-summer-hot-sun.jpg"
private const val siriusStar = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Sirius_A_and_B_Hubble_photo.jpg/300px-Sirius_A_and_B_Hubble_photo.jpg"
private const val alfaCentauri = "https://i0.wp.com/prokocmoc.ru/wp-content/uploads/2019/09/Alfa-TSentavra.jpg?resize=768%2C433&ssl=1"

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
        val planet1 = Notes(
            id = UUID.randomUUID().toString(),
            "Земля",
            "Наша планета"
        )
        val planet2 = Notes(
            id = UUID.randomUUID().toString(),
            "Венера",
            "Злая планета"
        )
        val planet3 = Notes(
            id = UUID.randomUUID().toString(),
            "Марс",
            "Наш будущий дом"
        )

        // опишем звезды
        val star1 = Affairs(
            id = UUID.randomUUID().toString(),
            "Солнце",
            sunStar,
            1
        )
        val star2 = Affairs(
            id = UUID.randomUUID().toString(),
            "Сириус",
            siriusStar,
            2
        )
        val star3 = Affairs(
            id = UUID.randomUUID().toString(),
            "Альфа Центавра",
            alfaCentauri,
            3
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

    fun onClickPlanet(notes: Notes) {
        messagesLiveData.value = notes.name
    }

    fun onClickStar(affairs: Affairs) {
        messagesLiveData.value = affairs.description
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