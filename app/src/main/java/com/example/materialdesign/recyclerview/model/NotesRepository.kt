package com.example.materialdesign.recyclerview.model

import androidx.lifecycle.LiveData
import com.example.materialdesign.NoteDao

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

    fun getAllItems():LiveData<List<NoteEntity>>{
        return noteDao.getAllItems()
    }

    /*
    fun getNotesLiveData(): LiveData<List<NoteEntity>> {
        return noteDao.getAllNotesLiveData()
    }

    fun findById(id: String): Boolean {
        return noteDao.findNoteById(id)
    }

     */

    fun getById(id: String): NoteEntity {
        return noteDao.getNoteById(id)
    }

}