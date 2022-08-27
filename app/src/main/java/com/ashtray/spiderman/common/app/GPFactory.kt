package com.ashtray.spiderman.common.app

import android.os.Bundle
import com.ashtray.spiderman.common.helpers.GPConst
import com.ashtray.spiderman.features.home.HomeFragment
import com.ashtray.spiderman.features.onboarding.OnBoardingFragment
import com.ashtray.spiderman.features.splash.SplashScreenFragment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GPFactory @Inject constructor() {
    fun getSplashScreenFragment() = SplashScreenFragment()

    fun getOnBoardingFragment(isFromSplashFragment: Boolean): OnBoardingFragment {
        val args = Bundle()
        args.putBoolean(GPConst.PK_IS_FROM_SPLASH_FRAGMENT, isFromSplashFragment)
        val fragment = OnBoardingFragment()
        fragment.arguments = args
        return fragment
    }

    fun getHomeFragment() = HomeFragment()
}