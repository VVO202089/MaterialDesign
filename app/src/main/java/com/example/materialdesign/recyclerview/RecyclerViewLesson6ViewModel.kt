package com.example.materialdesign.recyclerview

import DialogFragmentLesson6
import Event
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.recyclerview.adapter.Adapter_Lesson6
import com.example.materialdesign.recyclerview.model.*
import java.util.*

// планеты
private const val earthPictureUrl = "https://findicons.com/files/icons/144/web/256/earth.png"
private const val venusPictureUrl = "https://ichef.bbci.co.uk/news/800/cpsprodpb/114B6/production/_118783807_d46ff476-316c-4dc6-ac82-42ef03ae0bf2.jpg.webp"
private const val marsPictureUrl = "https://findicons.com/files/icons/475/solar_system/256/mars.png"

//звезды
private const val sunStar = "https://st.depositphotos.com/1007168/1249/i/600/depositphotos_12492703-stock-photo-summer-hot-sun.jpg"
private const val siriusStar = "https://upload.wikimedia.org/wikipedia/commons/thumb/f/f3/Sirius_A_and_B_Hubble_photo.jpg/300px-Sirius_A_and_B_Hubble_photo.jpg"
private const val alfaCentauri = "https://i0.wp.com/prokocmoc.ru/wp-content/uploads/2019/09/Alfa-TSentavra.jpg?resize=768%2C433&ssl=1"

class RecyclerViewLesson6ViewModel(
    private val notesRepository: NotesRepository
) : ViewModel(),OnRecyclerViewItemClickListener {

    private val itemsLiveData = MutableLiveData<List<SampleListItem>>(emptyList())
    private val messagesLiveData = MutableLiveData<String>()

    private lateinit var tempNote: NoteEntity

    private val _openNotes = MutableLiveData<Event<String>>()
    val openNotes: LiveData<Event<String>> = _openNotes

    private val _noteDelete = MutableLiveData<DialogFragment>()
    val noteDelete: LiveData<DialogFragment> = _noteDelete

    fun getItems(): LiveData<List<SampleListItem>> {
        return itemsLiveData
    }

    fun getMessages(): LiveData<String> {
        return messagesLiveData
    }

    fun setAdapter(adapterLesson6: Adapter_Lesson6){

        adapterLesson6.setListener(object : OnRecyclerViewItemClickListener{

            override fun openElement(note: NoteEntity) {
                this@RecyclerViewLesson6ViewModel.openElement(note)
            }

            override fun delElement(note: NoteEntity) {
               this@RecyclerViewLesson6ViewModel.delElement(note)
            }

        })
    }
    // слушатели

    fun onClickNote(note: NoteEntity) {
        messagesLiveData.value = note.name
    }

    fun onClickAffair(affair: AffairEntity) {
        messagesLiveData.value = affair.description
    }

    private fun requireCurrentList(): List<SampleListItem> {
        return itemsLiveData.value ?: throw IllegalStateException("список пустой")
    }

    override fun openElement(note: NoteEntity) {
        _openNotes.value = Event(note.id)
    }

    override fun delElement(note: NoteEntity) {
        val confirmation = DialogFragmentLesson6()
        tempNote = note
        _noteDelete.postValue(confirmation)
    }

    fun deleteNoteInRepos(result: Bundle, key: String) {

        val isConfirmed = result.getBoolean(key)

        if (isConfirmed){
            notesRepository.removeNote(tempNote)
        }
    }
}