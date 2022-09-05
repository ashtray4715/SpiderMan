package com.ashtray.spiderman.features.onboarding.entities

import android.graphics.drawable.Drawable
import com.ashtray.spiderman.common.helpers.GPConst

data class OnBoardingFeatureItem(
    override val itemType: Int = GPConst.ON_BOARDING_ITEM_TYPE_FEATURE,
    val title: String,
    val description: String,
    val drawable: Drawable?
) : OnBoardingItem