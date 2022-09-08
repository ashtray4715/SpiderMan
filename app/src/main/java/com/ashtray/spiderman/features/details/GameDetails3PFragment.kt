package com.ashtray.spiderman.features.details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ashtray.spiderman.common.helpers.ArgScanner
import com.ashtray.spiderman.common.helpers.GPLog
import com.ashtray.spiderman.common.ui.GPFragment
import com.ashtray.spiderman.database.GameWithScores
import com.ashtray.spiderman.database.ScoreEntity
import com.ashtray.spiderman.databinding.FragmentGameDetails3pBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameDetails3PFragment : GPFragment() {

    private val viewModel by viewModels<GameDetails3PViewModel>()

    private var _binding: FragmentGameDetails3pBinding? = null
    private val binding get() = _binding!!

    private var gameWithScores: GameWithScores? = null

    private val startCallCallBack = object : StartCall3PDialog.CallBacks {
        override fun onSaveBtnPressed(scoreEntity: ScoreEntity) {
            viewLifeCycleOwnerScope?.launch {
                viewModel.insertScoreEntity(scoreEntity)
                showToastMessage("Saved successfully")
            }
        }
    }

    override val log = GPLog("GameDetails3PFragment")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        log.d("onCreateView: called")
        _binding = FragmentGameDetails3pBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSingleGameWithScores(
            ArgScanner(arguments).getGameId()
        ).observe(viewLifecycleOwner) {
            it?.let { mGameWithScores ->
                gameWithScores = mGameWithScores
                log.d("game_id = ${mGameWithScores.gameEntity.gameId}")
                log.d("score_size = ${mGameWithScores.scoreEntities.size}")
            }
        }

        binding.actionBar.setMenuListener { handleAddNewScoreMenuPressed() }
    }

    override fun handleBackButtonPressed(): Boolean {
        changeFragment(this, TransactionType.REMOVE_FRAGMENT)
        return true
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun handleAddNewScoreMenuPressed() {
        val mGameEntity = gameWithScores?.gameEntity ?: return
        viewLifeCycleOwnerScope?.launch {
            withContext(Dispatchers.Main) {
                context?.let { mContext ->
                    StartCall3PDialog(mContext, mGameEntity).apply {
                        setCallBacks(startCallCallBack)
                        //setOnShowListener(adLoaderOnDialogShown)
                        //setOnDismissListener(adRefresherOnDialogExit)
                        show()
                    }
                }
            }
        }
    }

}