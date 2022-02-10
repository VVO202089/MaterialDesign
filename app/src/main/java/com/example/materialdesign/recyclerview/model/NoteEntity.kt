package com.example.materialdesign.recyclerview.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note_table")
data class NoteEntity(
    @PrimaryKey
    val id: String = "",
    val name: String = "",
    val description: String = "",
) : SampleListItem
