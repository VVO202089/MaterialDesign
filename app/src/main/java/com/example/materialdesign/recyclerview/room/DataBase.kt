package ru.barinov.notes.domain.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.materialdesign.recyclerview.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class DataBase: RoomDatabase() {
    abstract fun noteDao(): NoteDao
}