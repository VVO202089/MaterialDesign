package com.example.materialdesign.recyclerview.fragment.editNotes

import Event
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.materialdesign.recyclerview.model.NoteDraft
import com.example.materialdesign.recyclerview.model.NoteEntity
import com.example.materialdesign.recyclerview.model.NotesRepository
import com.example.materialdesign.recyclerview.model.SampleListItem
import java.util.*

class EditNoteViewModel(
    private val noteId: String,
    private val repository: NotesRepository
) : ViewModel() {

    private val _editionModeMessage = MutableLiveData<Event<Unit>>()
    val editionModeMessage: LiveData<Event<Unit>> = _editionModeMessage
    private val itemsLiveData = MutableLiveData<List<SampleListItem>>(emptyList())
    private val messagesLiveData = MutableLiveData<String>()

    private val _closeScreen = MutableLiveData<Unit>()
    val closeScreenViewModel: LiveData<Unit> = _closeScreen

    private val _viewContentLiveData = MutableLiveData<NoteDraft>()
    val fillViewWithData: LiveData<NoteDraft> = _viewContentLiveData

    private lateinit var tempNote: NoteEntity
    private lateinit var uuid: UUID

    fun saveNote(draft: NoteDraft) {

        if (!draft.name.isNullOrEmpty() || !draft.description.isNullOrEmpty()) {

            val note = createEditNote(draft)
            repository.insertNote(note)
            _closeScreen.postValue(Unit)

        }
    }

    private fun createEditNote(draft: NoteDraft): NoteEntity {

        return if (checkOnEditionMode()) {
            NoteEntity(
                id = tempNote.id,
                name = draft.name,
                description = draft.description
            )
        } else {
            uuid = UUID.randomUUID()
            NoteEntity(uuid.toString(), draft.name, draft.description)
        }

    }

    private fun checkOnEditionMode(): Boolean {

        if (noteId != "") {
            tempNote = repository.getById(noteId)

            if (tempNote != null) {
                _editionModeMessage.value = Event(Unit)
                return true
            }
        }

        return false
    }

    fun fillTheViews() {
        if (checkOnEditionMode()) {
            _viewContentLiveData.postValue(
                NoteDraft(
                    tempNote.name, tempNote.description
                )
            )
        }
    }

}