package com.ashtray.spiderman.features.home

import androidx.recyclerview.widget.RecyclerView.ViewHolder
import android.widget.TextView
import android.annotation.SuppressLint
import android.view.View
import com.ashtray.spiderman.R
import com.ashtray.spiderman.database.GameEntity

internal class HomeListViewHolder(
    private val callBacks: CallBacks?,
    itemView: View
) : ViewHolder(itemView) {

    interface CallBacks {
        fun onItemPressed(position: Int)
    }

    private val tvGameName: TextView by lazy {
        itemView.findViewById(R.id.tv_game_name)
    }

    private val tvPlayerCount: TextView by lazy {
        itemView.findViewById(R.id.tv_player_count)
    }

    private val tvTotalTarget: TextView by lazy {
        itemView.findViewById(R.id.tv_target_score)
    }

    private val tvTargetNoti: TextView by lazy {
        itemView.findViewById(R.id.tv_target_notification)
    }

    init {
        itemView.setOnClickListener {
            callBacks?.onItemPressed(bindingAdapterPosition)
        }
    }

    @SuppressLint("DefaultLocale", "SetTextI18n")
    fun displayGameObject(gameEntity: GameEntity) {
        tvTotalTarget.text = "Target score - ${gameEntity.targetScore}"
        tvGameName.text = gameEntity.gameName
        tvPlayerCount.text = gameEntity.totalPlayer.toString()
        tvTargetNoti.text = when(gameEntity.notifyOnTargetReached) {
            true -> "Target noti - On"
            false -> "Target noti - Off"
        }
    }

}