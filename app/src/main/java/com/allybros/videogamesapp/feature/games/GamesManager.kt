package com.allybros.videogamesapp.feature.games

import android.util.Log
import com.allybros.videogamesapp.api.RestAPI
import com.allybros.videogamesapp.api.Secrets
import com.allybros.videogamesapp.commons.GameItem
import rx.Observable

class GamesManager(private val api: RestAPI = RestAPI()) {

    fun getGames(): Observable<List<GameItem>> {
        return Observable.create {
                subscriber ->
            val callResponse = api.getGames(Secrets.x_rapidapi_key,Secrets.x_rapidapi_host)
            val response = callResponse.execute()

            if(response.isSuccessful) {
                Log.d("Response size: ",""+response.body()?.results?.size)
                val games = response.body()?.results?.map {
                    GameItem(it.name,it.rating,it.released,it.background_image)
                }
                subscriber.onNext(games)
                subscriber.onCompleted()
            } else {
                subscriber.onError(Throwable(response.message()))
            }
        }
    }
}
