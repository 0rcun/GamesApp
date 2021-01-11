package com.allybros.videogamesapp.feature.games

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.allybros.videogamesapp.R
import com.allybros.videogamesapp.commons.Games
import com.allybros.videogamesapp.commons.InfiniteScrollListener
import com.allybros.videogamesapp.commons.RxBaseFragment
import com.allybros.videogamesapp.commons.extensions.inflate
import com.allybros.videogamesapp.feature.games.adapter.GamesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_games.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GamesFragment : RxBaseFragment() {

    private var games: Games? = null
    private val gamesManager by lazy { GamesManager() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_games)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        games_recyleView.setHasFixedSize(true)
        val linearLayout = LinearLayoutManager(context)
        games_recyleView.layoutManager = linearLayout
        games_recyleView.clearOnScrollListeners()
        games_recyleView.addOnScrollListener(InfiniteScrollListener({ requestGames() }, linearLayout))
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
                                    games = retrievedGames
                                    (games_recyleView.adapter as GamesAdapter).addGame(retrievedGames.games)
                                },
                                { e->
                                    Snackbar.make(games_recyleView, e.message ?: "",Snackbar.LENGTH_LONG).show()
                                }
                        )
        subscriptions.add(subscription)
    }


    private fun initAdapter() {
        if (games_recyleView.adapter == null) {
            games_recyleView.adapter = GamesAdapter()
        }
    }
}