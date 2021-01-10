package com.allybros.videogamesapp.feature.games.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allybros.videogamesapp.R
import com.allybros.videogamesapp.commons.GameItem
import com.allybros.videogamesapp.commons.adapter.ViewType
import com.allybros.videogamesapp.commons.adapter.ViewTypeDelegateAdapter
import com.allybros.videogamesapp.commons.extensions.inflate
import com.allybros.videogamesapp.commons.extensions.loadImg
import kotlinx.android.synthetic.main.games_item.view.*

class GameDelegateAdapter : ViewTypeDelegateAdapter {

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
            //Picasso.with(itemView.context).load(item.thumbnail).into(img_thumbnail)
            img_thumbnail.loadImg(item.thumbnail)
            game_name.text = item.game_name
            games_rating.text = "Rating: "+item.rating.toString()
            games_released.text = "Released: "+item.released.toString()
        }
    }
}