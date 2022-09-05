package com.ashtray.spiderman.features.onboarding.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.ashtray.spiderman.R
import com.ashtray.spiderman.features.onboarding.entities.OnBoardingFeatureItem
import com.ashtray.spiderman.features.onboarding.entities.OnBoardingItem

class OnBoardingFeatureViewHolder(itemView: View) : OnBoardingViewHolder(itemView) {
    private val tvTitle: TextView by lazy {
        itemView.findViewById(R.id.tv_title)
    }

    private val tvDescription: TextView by lazy {
        itemView.findViewById(R.id.tv_description)
    }

    private val ivIcon: ImageView by lazy {
        itemView.findViewById(R.id.iv_icon)
    }

    override fun displayOnBoardingItem(item: OnBoardingItem) {
        if (item is OnBoardingFeatureItem) {
            tvTitle.text = item.title
            tvDescription.text = item.description
            ivIcon.setImageDrawable(item.drawable)
        }
    }
}