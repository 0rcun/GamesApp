package com.allybros.videogamesapp.feature.games.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.allybros.videogamesapp.PropertiesActivity
import com.allybros.videogamesapp.R
import com.allybros.videogamesapp.commons.GameItem
import com.allybros.videogamesapp.commons.extensions.loadImg
import kotlinx.android.synthetic.main.viewpager_item.view.*

class ViewPagerAdapter(
    private val imagesList: Array<GameItem>?,
    private val context: Context?
    ) : RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.viewpager_item, parent, false)
        return ViewPagerHolder(view)
    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.itemView.setOnClickListener(View.OnClickListener {
            val id = imagesList?.get(position)?.id
            id?.let { it1 -> Log.d("Clicked: ", it1) }

            val intent = Intent(context, PropertiesActivity::class.java).apply{
                putExtra("ID",id)
            }
            if (context != null) {
                context.startActivity(intent)
            }

        })
        imagesList?.get(position)?.let { holder.itemView.viewPager_imageview.loadImg(it.background_image) }
    }

    override fun getItemCount(): Int {
        return imagesList?.size!!
    }


    class ViewPagerHolder(view: View) : RecyclerView.ViewHolder(view) {

    }
}