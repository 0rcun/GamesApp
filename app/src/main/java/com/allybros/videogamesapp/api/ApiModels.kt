package com.allybros.videogamesapp.api

class GamesResponse(
    val results: List<GamesResultResponse>,
    val next: String?,
    val previous: String?
)

class GamesResultResponse(
    val name: String,
    val rating: Float,
    val released: String,
    val background_image: String,
)
