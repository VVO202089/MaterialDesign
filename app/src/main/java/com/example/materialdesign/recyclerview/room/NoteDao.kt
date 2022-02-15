package com.example.materialdesign

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.materialdesign.recyclerview.model.NoteEntity
import com.example.materialdesign.recyclerview.model.SampleListItem

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note: NoteEntity)

    @Update
    fun update(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Query("SELECT * FROM note_table")
    fun getAllItems(): LiveData<List<NoteEntity>>

    @androidx.room.Query("DELETE FROM note_table")
    fun clearDataBase()

    @androidx.room.Query("SELECT * FROM note_table WHERE id == :id")
    fun getNoteById(id: String): NoteEntity

    @androidx.room.Query("SELECT * FROM note_table WHERE id == :id")
    fun findNoteById(id: String): Boolean

}