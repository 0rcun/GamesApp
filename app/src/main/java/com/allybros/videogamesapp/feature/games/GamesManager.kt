package com.allybros.videogamesapp.feature.games

import android.util.Log
import com.allybros.videogamesapp.api.GameProperties
import com.allybros.videogamesapp.api.RestAPI
import com.allybros.videogamesapp.api.Secrets
import com.allybros.videogamesapp.commons.GameItem
import com.allybros.videogamesapp.commons.Games
import rx.Observable

class GamesManager(private val api: RestAPI = RestAPI()) {

    fun getGames(next: String): Observable<Games> {
        return Observable.create {
                subscriber ->
            val callResponse = api.getGames(Secrets.x_rapidapi_key,Secrets.x_rapidapi_host, next.get(next.length-1).toString())
            val response = callResponse.execute()

            if(response.isSuccessful) {
                Log.d("Response size: ",""+response.body()?.results?.size)


                val games = response.body()?.results?.map {
                    GameItem(it.name,it.rating,it.id,it.released,it.background_image)
                }
                val gamesResponse = Games(
                    response?.body()?.next ?: "",
                    response?.body()?.previous ?: "",
                        games!!)
                subscriber.onNext(gamesResponse)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }

    fun getGame(id: String): Observable<GameProperties> {
        return Observable.create {
            subscriber ->
            val callResponse = api.getGame(Secrets.x_rapidapi_key,Secrets.x_rapidapi_host, id)
            val response = callResponse.execute()

            if(response.isSuccessful) {

                val game = GameProperties(
                        response.body()!!.name,
                        response.body()!!.released,
                        response.body()!!.metacritic,
                        response.body()!!.description,
                        response.body()!!.background_image
                )
                Log.d("GamesManager", response.body()?.metacritic.toString())
                subscriber.onNext(game)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}
