package com.example.materialdesign.recyclerview.room;

import androidx.room.TypeConverter;

class NoteTypeConverter {

    @TypeConverter
    fun toType(value: String) = enumValueOf<NoteTypes>(value)

    @TypeConverter
    fun fromType(value: NoteTypes) = value.name
}
