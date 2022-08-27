package com.ashtray.spiderman.features.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.app.GPApp
import com.ashtray.spiderman.common.app.GPApp.Companion.getSharedPref
import com.ashtray.spiderman.common.helpers.GPLog.d
import com.ashtray.spiderman.common.ui.GPFragment
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : GPFragment() {

    override val mTag = "SplashScreenFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        d(mTag, "onCreateView: called")
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)
    }

    override fun onResume() {
        super.onResume()
        performLoadingTask()
    }

    override fun handleBackButtonPressed(): Boolean {
        return false
    }

    private fun performLoadingTask() {
        viewLifeCycleOwnerScope?.launch {
            delay(500)
            //tryToDownloadNewAds()
            delay(500)
            openHomePageOrOnBoardingScreen()
        }
    }

    private fun openHomePageOrOnBoardingScreen() {
        val toFragment = when(getSharedPref().getOnBoardingPendingStatus()) {
            true -> GPApp.getFactory().getOnBoardingFragment(true)
            else -> GPApp.getFactory().getHomeFragment()
        }
        changeFragment(toFragment, TransactionType.SINGLE_FRAGMENT)
    }
}