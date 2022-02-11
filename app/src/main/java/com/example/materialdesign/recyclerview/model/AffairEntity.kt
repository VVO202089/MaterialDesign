package com.example.materialdesign.recyclerview.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "affair_table")
data class AffairEntity(
    @PrimaryKey
    val id: String = "",
    val name: String = "",
    val description: String = "",
    val priority: Int = 0
) : SampleListItem