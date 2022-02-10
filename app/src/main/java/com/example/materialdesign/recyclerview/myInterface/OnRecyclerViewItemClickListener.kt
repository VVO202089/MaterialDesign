package com.example.materialdesign.recyclerview.myInterface

import com.example.materialdesign.recyclerview.model.NoteEntity

interface OnRecyclerViewItemClickListener {
    fun openElement(note: NoteEntity)
    fun delElement(note: NoteEntity)
}