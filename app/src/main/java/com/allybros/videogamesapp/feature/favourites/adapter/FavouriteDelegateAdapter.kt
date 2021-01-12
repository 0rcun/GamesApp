package com.allybros.videogamesapp.feature.games.adapter

import android.content.Intent
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.allybros.videogamesapp.PropertiesActivity
import com.allybros.videogamesapp.R
import com.allybros.videogamesapp.commons.GameItem
import com.allybros.videogamesapp.commons.adapter.ViewType
import com.allybros.videogamesapp.commons.adapter.ViewTypeDelegateAdapter
import com.allybros.videogamesapp.commons.extensions.inflate
import com.allybros.videogamesapp.commons.extensions.loadImg
import kotlinx.android.synthetic.main.games_item.view.*

class FavouriteDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return TurnsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as TurnsViewHolder
        holder.bind(item as GameItem)
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        parent.inflate(R.layout.games_item)) {

        fun bind(item: GameItem) = with(itemView) {
            img_thumbnail.loadImg(item.background_image)
            game_name.text = item.game_name
            games_rating.text = "Rating: "+item.rating.toString()
            games_released.text = "Released: "+item.released.toString()
            this.setOnClickListener({
                val id = item.id
                Log.d("Clicked: ",id)

                val intent = Intent(context, PropertiesActivity::class.java).apply{
                    putExtra("ID",id)
                }
                context.startActivity(intent)


            })
        }
    }
}