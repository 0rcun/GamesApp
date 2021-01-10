package com.allybros.videogamesapp.feature.games.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allybros.videogamesapp.R
import com.allybros.videogamesapp.commons.adapter.ViewType
import com.allybros.videogamesapp.commons.adapter.ViewTypeDelegateAdapter
import com.allybros.videogamesapp.commons.extensions.inflate


class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup) = TurnsViewHolder(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    }

    class TurnsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
            parent.inflate(R.layout.games_item_loading)) {
    }
}