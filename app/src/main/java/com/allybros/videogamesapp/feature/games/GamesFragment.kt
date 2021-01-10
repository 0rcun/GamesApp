package com.allybros.videogamesapp.feature.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.allybros.videogamesapp.R
import com.allybros.videogamesapp.commons.RxBaseFragment
import com.allybros.videogamesapp.commons.extensions.inflate
import com.allybros.videogamesapp.feature.games.adapter.GamesAdapter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_games.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class GamesFragment : RxBaseFragment() {

    private val gamesManager by lazy { GamesManager() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_games)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        games_recyleView.setHasFixedSize(true)
        games_recyleView.layoutManager = LinearLayoutManager(context)

        initAdapter()

        if (savedInstanceState == null) {
            requestGames()
        }
    }

    private fun requestGames(){
        val subscription = gamesManager
                        .getGames()
                        .subscribeOn(Schedulers.io())/*It is a API request*/
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { retrievedGames ->
                                    (games_recyleView.adapter as GamesAdapter).addGame(retrievedGames)
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