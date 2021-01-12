package com.allybros.videogamesapp

import android.os.Bundle
import android.util.Log
import android.view.View
import com.allybros.videogamesapp.commons.App
import com.allybros.videogamesapp.commons.RxBaseActivity
import com.allybros.videogamesapp.commons.extensions.loadImg
import com.allybros.videogamesapp.feature.favourites.Prefs
import com.allybros.videogamesapp.feature.games.GamesManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_properties.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class PropertiesActivity : RxBaseActivity() {

    var favFlag : Boolean = false
    private val prefs: Prefs by lazy {
        Prefs(App.instance)
    }
    var arrayListGames = ArrayList<String>()
    private val gamesManager by lazy { GamesManager() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_properties)
        val id = intent.getStringExtra("ID")
        getGameInfo(id!!)
        var arrayGames = prefs.myStringArray
        for(item: String in arrayGames){
            arrayListGames.add(item)
        }
        for (item: String in arrayGames){
            if(item.equals(id)) {
                favButton.background = resources.getDrawable(R.drawable.ic_baseline_favorite_24)
                favFlag = true
            }
        }

        favButton.setOnClickListener(View.OnClickListener { view ->
            if(favFlag){
                favButton.background = resources.getDrawable(R.drawable.ic_baseline_favorite_border)
                favFlag = false
                arrayListGames.remove(id)
            }else{
                favButton.background = resources.getDrawable(R.drawable.ic_baseline_favorite_24)
                favFlag = true
                arrayListGames.add(id)
            }
        })
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
                            Snackbar.make(game_description, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }

    override fun onPause() {
        val vowels_array: Array<String> = arrayListGames.toTypedArray()
        prefs.myStringArray = vowels_array
        super.onPause()
    }
}