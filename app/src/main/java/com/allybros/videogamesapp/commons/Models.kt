package com.allybros.videogamesapp.commons

import com.allybros.videogamesapp.commons.adapter.AdapterConstants
import com.allybros.videogamesapp.commons.adapter.ViewType

data class GameItem(
        val game_name: String,
        val rating: Float,
        val released: String,
        val thumbnail: String,
) : ViewType {
    override fun getViewType() = AdapterConstants.GAMES
}