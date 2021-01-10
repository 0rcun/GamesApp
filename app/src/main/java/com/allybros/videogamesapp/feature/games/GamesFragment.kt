package com.allybros.videogamesapp.feature.games

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.allybros.videogamesapp.R
import com.allybros.videogamesapp.commons.GameItem
import com.allybros.videogamesapp.commons.extensions.inflate
import com.allybros.videogamesapp.feature.games.adapter.GamesAdapter
import kotlinx.android.synthetic.main.fragment_games.*

class GamesFragment : Fragment() {

    private val gamesRecyclerView by lazy {
        games_recyleView
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_games)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        gamesRecyclerView.setHasFixedSize(true)
        gamesRecyclerView.layoutManager = LinearLayoutManager(context)

        initAdapter()

        if (savedInstanceState == null) {
            val games = mutableListOf<GameItem>()
            for (i in 1..10) {
                games.add(GameItem(
                        "$i. Game Name",
                        4F,
                        "$i/$i/$i$i$i$i", // image url
                        "http://lorempixel.com/200/200/technics/$i"
                ))
            }
            (gamesRecyclerView.adapter as GamesAdapter).addGame(games)
        }
    }

    private fun initAdapter() {
        if (games_recyleView.adapter == null) {
            games_recyleView.adapter = GamesAdapter()
        }
    }
}