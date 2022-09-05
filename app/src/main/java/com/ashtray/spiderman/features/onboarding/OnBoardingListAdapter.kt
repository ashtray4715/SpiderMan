package com.ashtray.spiderman.features.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.helpers.GPConst
import com.ashtray.spiderman.features.onboarding.entities.OnBoardingItem
import com.ashtray.spiderman.features.onboarding.viewholders.*
import java.lang.IllegalArgumentException

class OnBoardingListAdapter(
    private val mContext: Context?,
    private val onBoardingItems: List<OnBoardingItem>
) : RecyclerView.Adapter<OnBoardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnBoardingViewHolder {
        return when (viewType) {
            GPConst.ON_BOARDING_ITEM_TYPE_FEATURE -> {
                OnBoardingFeatureViewHolder(
                    LayoutInflater.from(mContext).inflate(
                        R.layout.layout_on_boarding_feature_item, parent, false
                    )
                )
            }
            GPConst.ON_BOARDING_ITEM_TYPE_DIVIDER -> {
                OnBoardingDividerViewHolder(
                    LayoutInflater.from(mContext).inflate(
                        R.layout.layout_on_boarding_divider_item, parent, false
                    )
                )
            }
            GPConst.ON_BOARDING_ITEM_TYPE_APP_INFO -> {
                OnBoardingAppInfoViewHolder(
                    LayoutInflater.from(mContext).inflate(
                        R.layout.layout_on_boarding_app_info_item, parent, false
                    )
                )
            }
            else -> throw IllegalArgumentException("Unsupported item")
        }
    }

    override fun onBindViewHolder(holder: OnBoardingViewHolder, position: Int) {
        holder.displayOnBoardingItem(onBoardingItems[position])
    }

    override fun getItemCount() = onBoardingItems.size

    override fun getItemViewType(position: Int): Int {
        return onBoardingItems[position].itemType
    }

}