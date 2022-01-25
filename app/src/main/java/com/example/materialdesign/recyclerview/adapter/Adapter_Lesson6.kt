package com.example.materialdesign.recyclerview.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.materialdesign.R
import com.example.materialdesign.recyclerview.model.PlanetModel
import com.example.materialdesign.recyclerview.model.SampleListItem
import com.example.materialdesign.recyclerview.model.StarModel
import java.lang.IllegalStateException
import com.bumptech.glide.Glide

private const val viewTypePlanet = 0
private const val viewTypeStar = 1

class Adapter_Lesson6(
    private var onPlanetClickListener: ((item: PlanetModel) -> Unit),
    private var onStarClickListener: ((item: StarModel) -> Unit)
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var items = emptyList<SampleListItem>() // пустой список

    // имплементированные методы
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            viewTypePlanet -> {
                PlanetViewHolder(
                    view = inflater.inflate(R.layout.item_planet_view, parent, false) as View,
                    onPlanetClickListener = onPlanetClickListener
                )
            }
            viewTypeStar -> {
                StarViewHolder(
                    view = inflater.inflate(R.layout.item_star_view, parent, false) as View,
                    onStarClickListener = onStarClickListener
                )

            }
            else -> throw IllegalArgumentException("Неизвестный тип")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == viewTypePlanet) {
            holder as PlanetViewHolder
            val planetModel = items[position] as PlanetModel
            holder.bind(planetModel)
        } else {
            holder as StarViewHolder
            val starModel = items[position] as StarModel
            holder.bind(starModel)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    @Suppress("MoveVariableDeclarationIntoWhen")
    override fun getItemViewType(position: Int): Int {
        val item = items[position]
        return when (item){
            is PlanetModel -> viewTypePlanet
            is StarModel -> viewTypeStar
            else -> throw IllegalStateException("Неизвестный тип View")
        }
    }

    // холдеры
    class PlanetViewHolder(
        view: View,
        private val onPlanetClickListener: ((item: PlanetModel) -> Unit)
    ) : RecyclerView.ViewHolder(view) {

        private var planetNameTW: TextView = view.findViewById(R.id.text_view_planet_name)
        private var planetImageView: ImageView = view.findViewById(R.id.image_view_planet_image)

        fun bind(planetModel: PlanetModel) {
            planetNameTW.text = planetModel.name
            Glide.with(planetImageView.context).load(planetModel.url).into(planetImageView)
            itemView.setOnClickListener { onPlanetClickListener(planetModel) }
        }
    }

    class StarViewHolder(
        view: View,
        private val onStarClickListener: ((item: StarModel) -> Unit)
    ) : RecyclerView.ViewHolder(view) {

        // пока текст и описание
        private var starNameTW: TextView = view.findViewById(R.id.text_view_star_title)
        private var starDescTW: TextView = view.findViewById(R.id.text_view_star_description)

        fun bind(starModel: StarModel) {
            starNameTW.text = starModel.name
            starDescTW.text = starModel.description
            itemView.setOnClickListener { onStarClickListener(starModel) }
        }
    }

}