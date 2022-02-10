package com.example.materialdesign.recyclerview.adapter

import com.example.materialdesign.recyclerview.model.SampleListItem
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.recyclerview.model.AffairEntity
import com.example.materialdesign.recyclerview.model.NoteEntity
import com.example.materialdesign.recyclerview.myInterface.OnRecyclerViewItemClickListener


private const val viewTypeNotes = 0
private const val viewTypeAffairs = 1

class Adapter_Lesson6(
    private var onNoteClickListener: (item: NoteEntity) -> Unit,
    private var onAffairClickListener: (item: AffairEntity) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<SampleListItem>()

    private lateinit var listener: OnRecyclerViewItemClickListener

    // имплементированные методы
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            viewTypeNotes -> {
                NotesViewHolder(
                    view = inflater.inflate(R.layout.item_notes_view, parent, false) as View,
                    onNoteClickListener = onNoteClickListener,
                    listener
                )
            }
            viewTypeAffairs -> {
                AffairViewHolder(
                    view = inflater.inflate(R.layout.item_affairs_view, parent, false) as View,
                    onAffairClickListener = onAffairClickListener,
                    listener
                )

            }
            else -> throw IllegalArgumentException("Неизвестный тип")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == viewTypeNotes) {
            holder as NotesViewHolder
            val noteModel = items[position] as NoteEntity
            holder.bind(noteModel)
        } else {
            holder as AffairViewHolder
            val affairModel = items[position] as AffairEntity
            holder.bind(affairModel)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @Suppress("MoveVariableDeclarationIntoWhen")
    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when (item) {
            is NoteEntity -> viewTypeNotes
            is AffairEntity -> viewTypeAffairs
            else -> throw IllegalStateException("Неизвестный тип View")
        }
    }

    fun setListener(listener: OnRecyclerViewItemClickListener) {
        this.listener = listener
    }
    // холдеры

    // в классах NotesViewHolder и AffairViewHolder хотел реализовать ContextMenu, но у меня не получилось вызвать setOnCreateContextMenuListener у View
    // и вообще, нужно ли это делать в адаптере или в другом месте? Тут вопрос..
    class NotesViewHolder(
        view: View,
        private val onNoteClickListener: (item: NoteEntity) -> Unit,
        private val listener: OnRecyclerViewItemClickListener
    ) : RecyclerView.ViewHolder(view) {

        //private val mClickListener by lazy {
        //    OnRecyclerViewItemClickListenerImpl(view.context)
        //}

        private var notesNameTW: TextView = view.findViewById(R.id.notes_textView)
        private var notesOpenImageView: ImageView = view.findViewById(R.id.open_notes_view)
        private var notesDelImageView: ImageView = view.findViewById(R.id.del_notes_view)
        //private var planetImageView: ImageView = view.findViewById(R.id.image_view_planet_image)

        fun bind(notes: NoteEntity) {
            //itemView.setOnCreateContextMenuListener(this)
            notesNameTW.text = notes.name
            // Glide.with(planetImageView.context).load(notes.url).into(planetImageView)
            itemView.setOnClickListener { onNoteClickListener(notes) }
            notesOpenImageView.setOnClickListener {
                listener.openElement(notes)
            }
            notesDelImageView.setOnClickListener {
                listener.delElement(notes)
            }

        }

    }

    class AffairViewHolder(
        view: View,
        private val onAffairClickListener: (item: AffairEntity) -> Unit,
        private val listener: OnRecyclerViewItemClickListener
    ) : RecyclerView.ViewHolder(view) {

        // пока текст и описание
        // private val starImageView: ImageView = view.findViewById(R.id.image_view_star_image)
        private var affairNameTW: TextView = view.findViewById(R.id.affairs_textView)
        //private var starDescTW: TextView = view.findViewById(R.id.text_view_star_description)
        private var affairOpenImageView: ImageView = view.findViewById(R.id.open_affair_view)
        private var affairDelImageView: ImageView = view.findViewById(R.id.del_affair_view)

        fun bind(affairs: AffairEntity) {
            //Glide.with(starImageView.context).load(affairs.url).into(starImageView)
            affairNameTW.text = affairs.name
            //starDescTW.text = affairs.description
            itemView.setOnClickListener { onAffairClickListener(affairs) }
            affairOpenImageView.setOnClickListener {
                listener.openElement(affairs)
            }
            affairDelImageView.setOnClickListener {
                listener.delElement(affairs)
            }
        }

    }

}