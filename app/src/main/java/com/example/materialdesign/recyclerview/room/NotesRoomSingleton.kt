package com.example.materialdesign.recyclerview.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.materialdesign.NoteDao
import com.example.materialdesign.recyclerview.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NotesRoomSingleton : RoomDatabase() {

    abstract fun NotesDao(): NoteDao

    companion object {

        private var INSTANCE: NotesRoomSingleton? = null

        fun getInstance(context: Context): NotesRoomSingleton {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    NotesRoomSingleton::class.java,
                    "note_table"
                ).allowMainThreadQueries().build() // из главного потока
            }

            return INSTANCE as NotesRoomSingleton
        }
    }
}