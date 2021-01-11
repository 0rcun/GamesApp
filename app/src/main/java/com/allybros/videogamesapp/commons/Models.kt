package com.allybros.videogamesapp.commons

import com.allybros.videogamesapp.commons.adapter.AdapterConstants
import com.allybros.videogamesapp.commons.adapter.ViewType

data class Games(
        val next: String,
        val previous: String,
        val games: List<GameItem>)

data class GameItem(
    val game_name: String,
    val rating: Float,
    val id: String,
    val released: String,
    val background_image: String,
    ) : ViewType {
    override fun getViewType() = AdapterConstants.GAMES
}