package com.example.materialdesign.recyclerview

import com.example.materialdesign.recyclerview.model.Notes

interface OnRecyclerViewItemClickListener {
    fun openElement(isNote: Boolean,note: Notes)
    fun delElement(note: Notes)
}