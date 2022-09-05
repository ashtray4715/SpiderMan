package com.ashtray.spiderman.features.onboarding.viewholders

import android.view.View
import android.widget.TextView
import com.ashtray.spiderman.R
import com.ashtray.spiderman.features.onboarding.entities.OnBoardingAppInfoItem
import com.ashtray.spiderman.features.onboarding.entities.OnBoardingItem

class OnBoardingAppInfoViewHolder(itemView: View) : OnBoardingViewHolder(itemView) {
    private val tvAppName: TextView by lazy {
        itemView.findViewById(R.id.tv_app_name)
    }

    private val tvVersion: TextView by lazy {
        itemView.findViewById(R.id.tv_version)
    }

    override fun displayOnBoardingItem(item: OnBoardingItem) {
        if (item is OnBoardingAppInfoItem) {
            tvAppName.text = item.appName
            tvVersion.text = item.versionString
        }
    }
}