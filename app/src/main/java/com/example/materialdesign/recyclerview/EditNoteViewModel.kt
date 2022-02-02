package com.example.materialdesign.recyclerview

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.recyclerview.model.NoteDraft
import com.example.materialdesign.recyclerview.model.NoteEntity
import com.example.materialdesign.recyclerview.model.Notes
import com.example.materialdesign.recyclerview.model.SampleListItem
import java.util.*

class EditNoteViewModel(
    private val noteId:String?,
): ViewModel() {

    private val itemsLiveData = MutableLiveData<List<SampleListItem>>(emptyList())
    private val messagesLiveData = MutableLiveData<String>()

    private lateinit var tempNote: NoteEntity
    private lateinit var uuid: UUID

    fun saveNote(note: NoteDraft){

        if (!note.name.isNullOrEmpty() || !note.description.isNullOrEmpty()){

            if (note == null){
                // создаем заметку
                val note = createNote()
            }else{
                // сохраняем существующую
            }

        }
    }

    private fun createNote(): Any {

    }

}