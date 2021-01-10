package com.allybros.videogamesapp.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface  GamesApi {
    @GET("/games")
    fun getGames(@Header("x-rapidapi-key") x_rapidapi_key: String,
                 @Header("x-rapidapi-host") x_rapidapi_host: String,
                 @Header("useQueryString") useQueryString: Boolean): Call<GamesResponse>;
}