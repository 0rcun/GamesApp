package com.allybros.videogamesapp

import android.os.Bundle
import android.util.Log
import com.allybros.videogamesapp.commons.RxBaseActivity
import com.allybros.videogamesapp.commons.extensions.loadImg
import com.allybros.videogamesapp.feature.games.GamesManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_properties.*
import kotlinx.android.synthetic.main.fragment_games.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class PropertiesActivity : RxBaseActivity() {

    private val gamesManager by lazy { GamesManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_properties)
        val id = intent.getStringExtra("ID")
        getGameInfo(id!!)
    }


    private fun getGameInfo(id: String){
        val subscription = gamesManager.getGame(id)
                .subscribeOn(Schedulers.io())/*It is a API request*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedGames ->
                            game_name_properties.text = retrievedGames.name
                            game_released_properties.text = retrievedGames.released
                            game_rating_properties.text = retrievedGames.metacritic.toString()
                            Log.d("Properties: ",retrievedGames.metacritic.toString())
                            game_description.text = retrievedGames.description
                            imageView_properties.loadImg(retrievedGames.background_image)
                        },
                        { e->
                            Snackbar.make(games_recyleView, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }
}