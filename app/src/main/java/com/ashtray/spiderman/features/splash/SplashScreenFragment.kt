package com.ashtray.spiderman.features.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.app.GPFactory
import com.ashtray.spiderman.common.app.GPSharedPref
import com.ashtray.spiderman.common.helpers.GPLog
import com.ashtray.spiderman.common.helpers.GPLog.d
import com.ashtray.spiderman.common.ui.GPFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : GPFragment() {

    private val viewModel by viewModels<SplashViewModel>()

    @Inject
    lateinit var factory: GPFactory

    @Inject
    lateinit var sharedPref : GPSharedPref

    override val mTag = "SplashScreenFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        d(mTag, "onCreateView: called ${viewModel.repository.dao.hashCode()}")
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
        val toFragment = when(sharedPref.getOnBoardingPendingStatus()) {
            true -> factory.getOnBoardingFragment(true)
            else -> factory.getHomeFragment()
        }
        changeFragment(toFragment, TransactionType.SINGLE_FRAGMENT)
    }
}