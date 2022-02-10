package com.example.materialdesign.recyclerview.diffUtil

import androidx.recyclerview.widget.DiffUtil
import com.example.materialdesign.recyclerview.model.AffairEntity
import com.example.materialdesign.recyclerview.model.NoteEntity
import com.example.materialdesign.recyclerview.model.SampleListItem

class DiffCallback(
    private val oldList: List<SampleListItem>,
    private val newList: List<SampleListItem>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]

        return when (oldItem) {
            is NoteEntity -> newItem is NoteEntity && oldItem.id == newItem.id
            is AffairEntity -> newItem is AffairEntity && oldItem.id == newItem.id
            else -> throw IllegalArgumentException("Неизвестный тип данных")
        }
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return when (oldItem) {
            // oldItem == newItem можно писать если у вас в списке - data классы,
            // тогда equals для них отработает хорошо, иначе сравнивайте руками поля
            is NoteEntity -> newItem is NoteEntity && oldItem == newItem
            is AffairEntity -> newItem is AffairEntity && oldItem == newItem
            else -> throw java.lang.IllegalArgumentException("Неизвестный тип данных")
        }
    }
}