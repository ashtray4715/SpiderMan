package com.ashtray.spiderman.features.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.app.GPSharedPref
import com.ashtray.spiderman.common.helpers.GPLog
import com.ashtray.spiderman.common.ui.GPFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment: GPFragment() {

    @Inject
    lateinit var sharedPref : GPSharedPref

    override val mTag = "OnBoardingFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        GPLog.d(mTag, "onCreateView: called")
        return inflater.inflate(R.layout.fragment_onboarding, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifeCycleOwnerScope?.launch(Dispatchers.IO) {
            sharedPref.setOnBoardingPendingStatus(false)
        }

    }
}