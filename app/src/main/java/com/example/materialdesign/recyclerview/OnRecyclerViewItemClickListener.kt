package com.example.materialdesign.recyclerview

import com.example.materialdesign.recyclerview.model.Notes

interface OnRecyclerViewItemClickListener {
    fun openElement(note: Notes)
    fun delElement(note: Notes)
}