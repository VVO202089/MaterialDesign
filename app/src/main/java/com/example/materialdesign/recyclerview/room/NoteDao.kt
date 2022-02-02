package ru.barinov.notes.domain.room

import androidx.room.*
import com.example.materialdesign.recyclerview.model.NoteEntity
import retrofit2.http.Query
import ru.barinov.notes.domain.models.NoteEntity

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note: NoteEntity)

    @Update
    fun update(note: NoteEntity)

    @Delete
    fun delete(note: NoteEntity)

    @Query("DELETE FROM note_table")
    fun clearDataBase()

}