package com.ashtray.spiderman.features.onboarding.entities

import com.ashtray.spiderman.common.helpers.GPConst

data class OnBoardingDividerItem(
    override val itemType: Int = GPConst.ON_BOARDING_ITEM_TYPE_DIVIDER
) : OnBoardingItem