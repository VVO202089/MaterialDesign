package com.example.materialdesign.recyclerview

import Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.recyclerview.adapter.Adapter_Lesson6
import com.example.materialdesign.recyclerview.model.Affairs
import com.example.materialdesign.recyclerview.model.Notes
import com.example.materialdesign.recyclerview.model.SampleListItem
import ru.barinov.notes.core.Event
import java.util.*

// планеты
private const val earthPictureUrl = "https://findicons.com/files/icons/144/web/256/earth.png"
private const val venusPictureUrl = "https://ichef.bbci.co.uk/news/800/cpsprodpb/114B6/production/_118783807_d46ff476-316c-4dc6-ac82-42ef03ae0bf2.jpg.webp"
private const val marsPictureUrl = "https://findicons.com/files/icons/475/solar_system/256/mars.png"

//звезды
private const val sunStar = "https://st.depositphotos.com/1007168/1249/i/600/depositphotos_12492703-stock-photo-summer-hot-sun.jpg"
private const val siriusStar = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Sirius_A_and_B_Hubble_photo.jpg/300px-Sirius_A_and_B_Hubble_photo.jpg"
private const val alfaCentauri = "https://i0.wp.com/prokocmoc.ru/wp-content/uploads/2019/09/Alfa-TSentavra.jpg?resize=768%2C433&ssl=1"

class RecyclerViewLesson6ViewModel : ViewModel(),OnRecyclerViewItemClickListener {

    private val itemsLiveData = MutableLiveData<List<SampleListItem>>(emptyList())
    private val messagesLiveData = MutableLiveData<String>()

    private val _openNotes = MutableLiveData<Event<String>>()
    private val openNotes: LiveData<Event<String>> = _openNotes

    fun getItems(): LiveData<List<SampleListItem>> {
        return itemsLiveData
    }

    fun getMessages(): LiveData<String> {
        return messagesLiveData
    }

    fun loadData() {

        // можно было бы затянуть из БД, но у нас проверка знаний по recyclerView
        // опишем заметки
        val note1 = Notes(
            id = UUID.randomUUID().toString(),
            "Заметка 1",
            "Описание заметки 1"
        )
        val note2 = Notes(
            id = UUID.randomUUID().toString(),
            "Заметка 2",
            "Описание заметки 2"
        )
        val note3 = Notes(
            id = UUID.randomUUID().toString(),
            "Заметка 3",
            "Описание заметки 3"
        )

        // опишем дела
        val affair1 = Affairs(
            id = UUID.randomUUID().toString(),
            "Дело 1",
            sunStar,
            1
        )
        val affair2 = Affairs(
            id = UUID.randomUUID().toString(),
            "Дело 2",
            siriusStar,
            2
        )
        val affair3 = Affairs(
            id = UUID.randomUUID().toString(),
            "Дело 3",
            alfaCentauri,
            3
        )

        // соберем все в список
        val items = listOf(
            note1,
            note2,
            note3,
            affair1,
            affair2,
            affair3
        )

        itemsLiveData.value = items
    }

    fun setAdapter(adapterLesson6: Adapter_Lesson6){

        adapterLesson6.setListener(object : OnRecyclerViewItemClickListener{

            override fun openElement(isNote: Boolean, note: Notes) {
                this@RecyclerViewLesson6ViewModel.openElement(isNote,note)
            }

            override fun delElement(note: Notes) {
               this@RecyclerViewLesson6ViewModel.delElement(note)
            }

        })
    }
    // слушатели

    fun onClickNote(note: Notes) {
        messagesLiveData.value = note.name
    }

    fun onClickAffair(affair: Affairs) {
        messagesLiveData.value = affair.description
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

    override fun openElement(isNote: Boolean, note: Notes) {

    }

    override fun delElement(note: Notes) {
        TODO("Not yet implemented")
    }
}