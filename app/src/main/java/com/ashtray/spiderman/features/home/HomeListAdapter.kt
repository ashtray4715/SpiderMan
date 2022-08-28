package com.ashtray.spiderman.features.home

import androidx.recyclerview.widget.RecyclerView
import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import com.ashtray.spiderman.R
import com.ashtray.spiderman.database.GameEntity
import java.util.ArrayList

internal class HomeListAdapter(
    private val mContext: Context?,
    private val mCallBacks: HomeListViewHolder.CallBacks
) : RecyclerView.Adapter<HomeListViewHolder>() {

    private val gameEntities = ArrayList<GameEntity>()

    @SuppressLint("NotifyDataSetChanged")
    fun updateGameList(gameEntities: List<GameEntity>) {
        this.gameEntities.clear()
        this.gameEntities.addAll(gameEntities)
        notifyDataSetChanged()
    }

    fun getGameEntity(position: Int): GameEntity? {
        return when (0 <= position && position < gameEntities.size) {
            true -> gameEntities[position]
            else -> null
        }
    }

    override fun getItemCount(): Int {
        return gameEntities.size
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HomeListViewHolder {
        val mInflater = LayoutInflater.from(mContext)
        val v = mInflater.inflate(R.layout.single_game_layout, viewGroup, false)
        return HomeListViewHolder(mCallBacks, v)
    }

    override fun onBindViewHolder(gameObjectViewHolder: HomeListViewHolder, i: Int) {
        gameObjectViewHolder.displayGameObject(gameEntities[i])
    }
}