package com.example.materialdesign.recyclerview.adapter

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.recyclerview.model.Affairs
import com.example.materialdesign.recyclerview.model.Notes
import com.example.materialdesign.recyclerview.model.SampleListItem

private const val viewTypePlanet = 0
private const val viewTypeStar = 1

class Adapter_Lesson6(
    private var onPlanetClickListener: ((item: Notes) -> Unit),
    private var onStarClickListener: ((item: Affairs) -> Unit)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<SampleListItem>() // пустой список

    // имплементированные методы
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            viewTypePlanet -> {
                NotesViewHolder(
                    view = inflater.inflate(R.layout.item_notes_view, parent, false) as View,
                    onPlanetClickListener = onPlanetClickListener
                )
            }
            viewTypeStar -> {
                AffairViewHolder(
                    view = inflater.inflate(R.layout.item_affairs_view, parent, false) as View,
                    onStarClickListener = onStarClickListener
                )

            }
            else -> throw IllegalArgumentException("Неизвестный тип")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == viewTypePlanet) {
            holder as NotesViewHolder
            val planetModel = items[position] as Notes
            holder.bind(planetModel)
        } else {
            holder as AffairViewHolder
            val starModel = items[position] as Affairs
            holder.bind(starModel)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @Suppress("MoveVariableDeclarationIntoWhen")
    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when (item) {
            is Notes -> viewTypePlanet
            is Affairs -> viewTypeStar
            else -> throw IllegalStateException("Неизвестный тип View")
        }
    }

    // холдеры
    class NotesViewHolder(
        view: View,
        private val onPlanetClickListener: ((item: Notes) -> Unit)
    ) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        private var planetNameTW: TextView = view.findViewById(R.id.notes_textView)
        //private var planetImageView: ImageView = view.findViewById(R.id.image_view_planet_image)

        fun bind(notes: Notes) {
            //itemView.setOnCreateContextMenuListener(this)
            planetNameTW.text = notes.name
            // Glide.with(planetImageView.context).load(notes.url).into(planetImageView)
            itemView.setOnClickListener { onPlanetClickListener(notes) }

        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.add(Menu.NONE, R.id.menu_del_view, Menu.NONE, "Удалить")
            menu?.add(Menu.NONE, R.id.menu_open_view, Menu.NONE, "Открыть")
        }

    }

    class AffairViewHolder(
        view: View,
        private val onStarClickListener: ((item: Affairs) -> Unit)
    ) : RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {

        // пока текст и описание
        // private val starImageView: ImageView = view.findViewById(R.id.image_view_star_image)
        private var starNameTW: TextView = view.findViewById(R.id.affairs_textView)
        //private var starDescTW: TextView = view.findViewById(R.id.text_view_star_description)

        fun bind(affairs: Affairs) {
            //Glide.with(starImageView.context).load(affairs.url).into(starImageView)
            starNameTW.text = affairs.name
            //starDescTW.text = affairs.description
            itemView.setOnClickListener { onStarClickListener(affairs) }
        }

        override fun onCreateContextMenu(
            menu: ContextMenu?,
            v: View?,
            menuInfo: ContextMenu.ContextMenuInfo?
        ) {
            menu?.add(Menu.NONE, R.id.menu_del_view, Menu.NONE, "Удалить")
            menu?.add(Menu.NONE, R.id.menu_open_view, Menu.NONE, "Открыть")
        }

        //companion object{
        //    vie
        //}
    }

}