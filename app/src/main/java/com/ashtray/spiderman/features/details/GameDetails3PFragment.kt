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
import com.ashtray.spiderman.database.GameEntity
import com.ashtray.spiderman.databinding.FragmentGameDetails3pBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class GameDetails3PFragment : GPFragment() {

    private val viewModel by viewModels<GameDetails3PViewModel>()

    private var _binding: FragmentGameDetails3pBinding? = null
    private val binding get() = _binding!!

    private var gameEntity: GameEntity? = null

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

        viewLifeCycleOwnerScope?.launch(Dispatchers.Main) {
            val gameIdNow = ArgScanner(arguments).getGameId()
            gameEntity = viewModel.getGameEntity(gameIdNow)
        }

        binding.actionBar.setMenuListener { handleAddNewScoreMenuPressed() }
    }

    override fun handleBackButtonPressed(): Boolean {
        changeFragment(this, TransactionType.REMOVE_FRAGMENT)
        return true
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun handleAddNewScoreMenuPressed() {
        val mGameEntity = gameEntity ?: return
        viewLifeCycleOwnerScope?.launch {
            withContext(Dispatchers.Main) {
                context?.let { mContext ->
                    AddScoreCall3PDialog(mContext, mGameEntity).apply {
                        //setCallBacks(addScoreDialogCB)
                        //setOnShowListener(adLoaderOnDialogShown)
                        //setOnDismissListener(adRefresherOnDialogExit)
                        show()
                    }
                }
            }
        }
    }

}