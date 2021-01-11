package com.allybros.videogamesapp.api

class GamesResponse(
    val results: List<GamesResultResponse>,
    val next: String?,
    val previous: String?
)

class GamesResultResponse(
    val name: String,
    val id: String,
    val rating: Float,
    val released: String,
    val background_image: String,
)


class GameProperties(
        val name: String,
        val released: String,
        val metacritic: String,
        val description: String,
        val background_image: String
)
