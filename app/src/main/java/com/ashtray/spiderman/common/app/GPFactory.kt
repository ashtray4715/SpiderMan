package com.ashtray.spiderman.common.app

import android.os.Bundle
import com.ashtray.spiderman.common.helpers.GPConst
import com.ashtray.spiderman.features.addgame.AddGameFragment
import com.ashtray.spiderman.features.details.GameDetails3PFragment
import com.ashtray.spiderman.features.details.GameDetails4PFragment
import com.ashtray.spiderman.features.details.GameDetails5PFragment
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

    fun getAddGameFragment() = AddGameFragment()

    fun getGameDetails3PFragment(selectedGameId: Long): GameDetails3PFragment {
        val args = Bundle()
        args.putLong(GPConst.PK_SELECTED_GAME_ID, selectedGameId)
        val fragment = GameDetails3PFragment()
        fragment.arguments = args
        return fragment
    }

    fun getGameDetails4PFragment(selectedGameId: Long): GameDetails4PFragment {
        val args = Bundle()
        args.putLong(GPConst.PK_SELECTED_GAME_ID, selectedGameId)
        val fragment = GameDetails4PFragment()
        fragment.arguments = args
        return fragment
    }

    fun getGameDetails5PFragment(selectedGameId: Long): GameDetails5PFragment {
        val args = Bundle()
        args.putLong(GPConst.PK_SELECTED_GAME_ID, selectedGameId)
        val fragment = GameDetails5PFragment()
        fragment.arguments = args
        return fragment
    }

}