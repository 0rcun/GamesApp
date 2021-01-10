package com.allybros.videogamesapp.api

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class RestAPI() {

    private val gamesApi: GamesApi

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rawg-video-games-database.p.rapidapi.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        gamesApi = retrofit.create(GamesApi::class.java)
    }

    fun getGames(x_rapidapi_key: String, x_rapidapi_host: String): Call<GamesResponse> {
        return gamesApi.getGames(x_rapidapi_key, x_rapidapi_host,true)
    }
}
