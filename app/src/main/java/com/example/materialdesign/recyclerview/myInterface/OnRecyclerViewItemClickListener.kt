package com.example.materialdesign.recyclerview.myInterface

import com.example.materialdesign.recyclerview.model.NoteEntity

interface OnRecyclerViewItemClickListener {
    fun openElement(element: Any)
    fun delElement(element: Any)
}