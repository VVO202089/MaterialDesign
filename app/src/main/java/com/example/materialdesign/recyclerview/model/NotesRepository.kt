package com.example.materialdesign.recyclerview.model

import androidx.lifecycle.LiveData
import ru.barinov.notes.domain.room.NoteDao

class NotesRepository(private val noteDao: NoteDao) {

    fun insertNote(note: NoteEntity) {
        noteDao.addNote(note)
    }

    fun removeAllNotes() {
        noteDao.clearDataBase()
    }

    fun removeNote(note: NoteEntity) {
        noteDao.delete(note)
    }

}