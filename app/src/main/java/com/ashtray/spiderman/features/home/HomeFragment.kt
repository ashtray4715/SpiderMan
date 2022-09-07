package com.ashtray.spiderman.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.ashtray.spiderman.common.app.GPFactory
import com.ashtray.spiderman.common.helpers.GPLog
import com.ashtray.spiderman.common.ui.GPFragment
import com.ashtray.spiderman.database.GameEntity
import com.ashtray.spiderman.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : GPFragment() {

    @Inject
    lateinit var factory: GPFactory

    private val viewModel by viewModels<HomeViewModel>()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private var _homeListAdapter: HomeListAdapter? = null
    private val homeListAdapter get() = _homeListAdapter

    private val viewHolderCB = object : HomeListViewHolder.CallBacks {
        override fun onItemPressed(position: Int) {
            openGameDetailsFragment(position)
        }
    }

    override val log = GPLog("HomeFragment")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log.d("onCreateView: called")
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        _homeListAdapter = HomeListAdapter(context, viewHolderCB)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _homeListAdapter = null
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvGameList.layoutManager = LinearLayoutManager(context)
        binding.rvGameList.adapter = homeListAdapter
        binding.emptyGameListView.visibility = View.GONE

        viewModel.getAllTheGameEntities().observe(viewLifecycleOwner) { mList ->
            viewLifeCycleOwnerScope?.launch(Dispatchers.Main) {
                handleGameListUpdate(mList)
            }
        }
        binding.actionBar.setMenuListener { handleAddGameMenuClicked() }
        binding.actionBar.setBackListener { handleSettingsMenuClicked() }
        binding.floatingAction.setCustomClickListener { handleAddGameMenuClicked() }
    }

    private fun handleExploreFeatureMenuClicked() {
        viewLifeCycleOwnerScope?.launch {
            changeFragment(
                fragment = factory.getOnBoardingFragment(false),
                transactionType = TransactionType.ADD_FRAGMENT
            )
        }
    }

    private fun handleSettingsMenuClicked() {
        log.d("handle settings menu clicked")
        showToastMessage("settings menu")
    }

    private fun handleGameListUpdate(gameEntities: List<GameEntity>?) {
        if (gameEntities.isNullOrEmpty()) {
            binding.emptyGameListView.visibility = View.VISIBLE
            homeListAdapter?.updateGameList(ArrayList())
        } else {
            binding.emptyGameListView.visibility = View.GONE
            homeListAdapter?.updateGameList(gameEntities)
        }
    }

    private fun handleAddGameMenuClicked() {
        viewLifeCycleOwnerScope?.launch {
            changeFragment(
                fragment = factory.getAddGameFragment(),
                transactionType = TransactionType.ADD_FRAGMENT
            )
        }
    }

    private fun openGameDetailsFragment(position: Int) {
        log.d("open game details fragment $position")
        homeListAdapter?.getGameEntity(position)?.let { gameEntity ->
            when (gameEntity.totalPlayer) {
                3 -> changeFragment(
                    factory.getGameDetails3PFragment(gameEntity.gameId),
                    TransactionType.ADD_FRAGMENT
                )
                4 -> changeFragment(
                    factory.getGameDetails4PFragment(gameEntity.gameId),
                    TransactionType.ADD_FRAGMENT
                )
                5 -> changeFragment(
                    factory.getGameDetails5PFragment(gameEntity.gameId),
                    TransactionType.ADD_FRAGMENT
                )
            }
        }
    }
}