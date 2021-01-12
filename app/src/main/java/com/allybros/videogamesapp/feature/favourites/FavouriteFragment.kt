package com.allybros.videogamesapp.feature.favourites

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.allybros.videogamesapp.R
import com.allybros.videogamesapp.commons.*
import com.allybros.videogamesapp.feature.games.GamesManager
import com.allybros.videogamesapp.feature.games.adapter.FavouriteAdapter
import com.allybros.videogamesapp.feature.games.adapter.GamesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_favourite.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class FavouriteFragment : RxBaseFragment() {

    private var games: Games? = null
    private val gamesManager by lazy { GamesManager() }
    private val prefs: Prefs by lazy {
        Prefs(App.instance)
    }
    private var filtredGames: ArrayList<GameItem> = ArrayList()

    var arrayGames = prefs.myStringArray

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        favourites_recylerview.setHasFixedSize(true)
        favourites_recylerview.layoutManager = LinearLayoutManager(context)
        initAdapter()


        if (savedInstanceState == null) {
            requestGames()
        }
    }

    private fun requestGames(){
        val subscription = gamesManager.getGames(games?.next ?: "1")
                .subscribeOn(Schedulers.io())/*It is a API request*/
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { retrievedGames ->
                            Log.d("Receiver: ",games?.next.toString());
                            for(item: String in arrayGames){
                                for(game: GameItem in retrievedGames.games){
                                    if(game.id.equals(item)) filtredGames.add(game)
                                }
                            }
                            (favourites_recylerview.adapter as FavouriteAdapter).addGame(filtredGames)
                        },
                        { e->
                            Snackbar.make(favourites_recylerview, e.message ?: "", Snackbar.LENGTH_LONG).show()
                        }
                )
        subscriptions.add(subscription)
    }


    private fun initAdapter() {
        if (favourites_recylerview.adapter == null) {
            favourites_recylerview.adapter = FavouriteAdapter()
        }
    }

}