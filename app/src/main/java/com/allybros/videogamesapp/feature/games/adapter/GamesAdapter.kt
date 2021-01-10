package com.allybros.videogamesapp.feature.games.adapter

import android.view.ViewGroup
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.RecyclerView
import com.allybros.videogamesapp.commons.GameItem
import com.allybros.videogamesapp.commons.adapter.AdapterConstants
import com.allybros.videogamesapp.commons.adapter.ViewType
import com.allybros.videogamesapp.commons.adapter.ViewTypeDelegateAdapter


class GamesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: ArrayList<ViewType>
    private var delegateAdapters = SparseArrayCompat<ViewTypeDelegateAdapter>()
    private val loadingItem = object : ViewType {
        override fun getViewType() = AdapterConstants.LOADING
    }

    init {
        delegateAdapters.put(AdapterConstants.LOADING, LoadingDelegateAdapter())
        delegateAdapters.put(AdapterConstants.GAMES, GameDelegateAdapter())
        items = ArrayList()
        items.add(loadingItem)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegateAdapters.get(viewType)!!.onCreateViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(holder, this.items[position])
    }

    override fun getItemViewType(position: Int): Int {
        return this.items.get(position).getViewType()
    }

    fun addGame(game: List<GameItem>) {
        // first remove loading and notify
        val initPosition = items.size - 1
        items.removeAt(initPosition)
        notifyItemRemoved(initPosition)

        // insert game and the loading at the end of the list
        items.addAll(game)
        items.add(loadingItem)
        notifyItemRangeChanged(initPosition, items.size + 1 /* plus loading item */)
    }

    fun clearAndAddGames(game: List<GameItem>) {
        items.clear()
        notifyItemRangeRemoved(0, getLastPosition())

        items.addAll(game)
        items.add(loadingItem)
        notifyItemRangeInserted(0, items.size)
    }

    fun getGames(): List<GameItem> {
        return items
                .filter { it.getViewType() == AdapterConstants.GAMES }
                .map { it as GameItem }
    }

    private fun getLastPosition() = if (items.lastIndex == -1) 0 else items.lastIndex


}

