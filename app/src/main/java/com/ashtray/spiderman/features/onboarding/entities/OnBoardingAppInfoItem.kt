package com.ashtray.spiderman.features.onboarding.entities

import com.ashtray.spiderman.common.helpers.GPConst

data class OnBoardingAppInfoItem(
    override val itemType: Int = GPConst.ON_BOARDING_ITEM_TYPE_APP_INFO,
    val appName: String,
    val versionString: String
) : OnBoardingItem