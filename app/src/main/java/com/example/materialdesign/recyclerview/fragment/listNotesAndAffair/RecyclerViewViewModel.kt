package com.example.materialdesign.recyclerview.fragment.listNotesAndAffair

import DialogFragmentLesson6
import Event
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.materialdesign.application
import com.example.materialdesign.recyclerview.adapter.Adapter_Lesson6
import com.example.materialdesign.recyclerview.model.AffairEntity
import com.example.materialdesign.recyclerview.model.NoteEntity
import com.example.materialdesign.recyclerview.model.NotesRepository
import com.example.materialdesign.recyclerview.model.SampleListItem
import com.example.materialdesign.recyclerview.myInterface.OnRecyclerViewItemClickListener
import com.example.materialdesign.recyclerview.room.NotesRoomSingleton


class RecyclerViewViewModel(
    private val notesRepository: NotesRepository,
    private val context: Context
) : AndroidViewModel(application), OnRecyclerViewItemClickListener {

    private val _items = MutableLiveData<List<NoteEntity>>()
    var items: LiveData<List<NoteEntity>> = _items

    private val db_notes: NotesRoomSingleton = NotesRoomSingleton.getInstance(context)

    //private val itemsLiveData = MutableLiveData<List<NoteEntity>>(emptyList())
    internal var itemsLiveData: LiveData<List<NoteEntity>> = db_notes.NotesDao().getAllItems()
    private val messagesLiveData = MutableLiveData<String>()

    private lateinit var tempNote: Any

    private val _openNotes = MutableLiveData<Event<String>>()
    val openNotes: LiveData<Event<String>> = _openNotes

    private val _noteDelete = MutableLiveData<DialogFragment>()
    val noteDelete: LiveData<DialogFragment> = _noteDelete

    private val _searchedNotes = MutableLiveData<String>("")
    private val searchedNotes: LiveData<String> = _searchedNotes

    fun getItems() {
        //notesRepository = NotesRepository(db_notes.NotesDao())
        notesRepository.getAllItems()

    }

    fun getMessages(): LiveData<String> {
        return messagesLiveData
    }

    fun setAdapter(adapterLesson6: Adapter_Lesson6) {

        adapterLesson6.setListener(object : OnRecyclerViewItemClickListener {

            override fun openElement(element: Any) {
                this@RecyclerViewViewModel.openElement(element)
            }

            override fun delElement(element: Any) {
                this@RecyclerViewViewModel.delElement(element)
            }

        })
    }

    fun onClickNote(note: NoteEntity) {
        messagesLiveData.value = note.name
    }

    fun onClickAffair(affair: AffairEntity) {
        messagesLiveData.value = affair.description
    }

    private fun requireCurrentList(): List<SampleListItem> {
        return itemsLiveData.value ?: throw IllegalStateException("список пустой")
    }

    override fun openElement(element: Any) {

        _openNotes.value = when (element) {
            is NoteEntity -> Event(element.id)
            is AffairEntity -> Event(element.id)
            else -> throw java.lang.IllegalArgumentException("Неизвестный тип данных")
        }

    }

    override fun delElement(element: Any) {
        val confirmation = DialogFragmentLesson6()
        tempNote = element
        _noteDelete.postValue(confirmation)
    }

    fun deleteNoteInRepos(result: Bundle, key: String) {

        val isConfirmed = result.getBoolean(key)

        if (isConfirmed) {

            if (tempNote is NoteEntity){
                notesRepository.removeNote(tempNote as NoteEntity)
            }else if(tempNote is AffairEntity){
                //notesRepository.removeNote(tempNote as )
            }


        }
    }
}