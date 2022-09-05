package com.ashtray.spiderman.features.onboarding.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.ashtray.spiderman.features.onboarding.entities.OnBoardingItem

abstract class OnBoardingViewHolder(itemView: View) : ViewHolder(itemView) {
    abstract fun displayOnBoardingItem(item: OnBoardingItem)
}