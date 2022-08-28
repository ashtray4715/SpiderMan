package com.ashtray.spiderman.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.viewModels
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.helpers.GPLog
import com.ashtray.spiderman.common.ui.GPFragment
import com.ashtray.spiderman.database.GameEntity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment: GPFragment() {

    private val viewModel by viewModels<HomeViewModel>()

    private val textView: TextView by lazy {
        requireView().findViewById(R.id.text_view)
    }

    override val mTag = "HomeFragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        GPLog.d(mTag, "onCreateView: called ${viewModel.repository.dao.hashCode()}")
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllTheGameEntities().observe(viewLifecycleOwner) { gameList ->
            GPLog.d(mTag, "total game found ${gameList.size} pcs")
        }

        textView.setOnClickListener {
            viewLifeCycleOwnerScope?.launch {
                viewModel.insertNewGame(
                    GameEntity(
                        gameId = System.currentTimeMillis(),
                        gameName = "physics",
                        totalPlayer = 3,
                        playerName1 = "gobinda",
                        playerName2 = "joy",
                        playerName3 = "mondol",
                        targetScore = 100,
                        notifyOnTargetReached = false
                    )
                )
                showToastMessage("added successfully")
            }
        }
    }
}