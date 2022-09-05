package com.ashtray.spiderman.features.onboarding

import android.view.LayoutInflater
import android.view.ViewGroup
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashtray.spiderman.BuildConfig
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.app.GPFactory
import com.ashtray.spiderman.common.app.GPSharedPref
import com.ashtray.spiderman.common.helpers.ArgumentScanner
import com.ashtray.spiderman.common.helpers.GPLog
import com.ashtray.spiderman.common.ui.GPFragment
import com.ashtray.spiderman.databinding.FragmentOnboardingBinding
import com.ashtray.spiderman.features.onboarding.entities.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class OnBoardingFragment : GPFragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!

    private var _mListAdapter: OnBoardingListAdapter? = null
    private val mListAdapter get() = _mListAdapter!!

    private var isFromSplashFragment = false

    @Inject
    lateinit var sharedPref: GPSharedPref

    @Inject
    lateinit var fragmentFactory: GPFactory

    override val log = GPLog("OnBoardingFragment")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log.d("onCreateView: called")
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        _mListAdapter = OnBoardingListAdapter(
            mContext = context,
            onBoardingItems = generateOnBoardingItems()
        )
        isFromSplashFragment = ArgumentScanner(arguments).isFromSplashFragment()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _mListAdapter = null
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvItemList.layoutManager = LinearLayoutManager(context)
        binding.rvItemList.adapter = mListAdapter

        viewLifeCycleOwnerScope?.launch(Dispatchers.IO) {
            sharedPref.setOnBoardingPendingStatus(false)
        }

        binding.continueButton.setCustomClickListener { continueBtnClicked() }
    }

    override fun handleBackButtonPressed(): Boolean {
        if (isFromSplashFragment) {
            return false
        }
        changeFragment(this, TransactionType.REMOVE_FRAGMENT)
        return true
    }

    private fun continueBtnClicked() {
        if (isFromSplashFragment) {
            changeFragment(
                fragment = fragmentFactory.getHomeFragment(),
                transactionType = TransactionType.SINGLE_FRAGMENT
            )
        } else {
            changeFragment(
                fragment = this,
                transactionType = TransactionType.REMOVE_FRAGMENT
            )
        }
    }

    private fun generateOnBoardingItems(): List<OnBoardingItem> {
        val mContext = context ?: return emptyList()
        return listOf(
            OnBoardingAppInfoItem(
                appName = getString(R.string.app_name),
                versionString = "Version - ${BuildConfig.VERSION_NAME}"
            ),
            OnBoardingDividerItem(),
            OnBoardingFeatureItem(
                title = getString(R.string.on_boarding_title1),
                description = getString(R.string.on_boarding_description1),
                drawable = ContextCompat.getDrawable(mContext, R.drawable.on_boarding1)
            ),
            OnBoardingDividerItem(),
            OnBoardingFeatureItem(
                title = getString(R.string.on_boarding_title2),
                description = getString(R.string.on_boarding_description2),
                drawable = ContextCompat.getDrawable(mContext, R.drawable.on_boarding2)
            ),
            OnBoardingDividerItem(),
            OnBoardingFeatureItem(
                title = getString(R.string.on_boarding_title3),
                description = getString(R.string.on_boarding_description3),
                drawable = ContextCompat.getDrawable(mContext, R.drawable.on_boarding3)
            )
        )
    }
}