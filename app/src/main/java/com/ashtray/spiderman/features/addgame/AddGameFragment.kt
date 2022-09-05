package com.ashtray.spiderman.features.addgame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.ashtray.spiderman.common.helpers.GPConst
import com.ashtray.spiderman.common.helpers.GPLog
import com.ashtray.spiderman.common.ui.GPFragment
import com.ashtray.spiderman.database.GameEntity
import com.ashtray.spiderman.databinding.FragmentAddGameBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddGameFragment : GPFragment() {

    private var _binding: FragmentAddGameBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AddGameViewModel>()

    override val log = GPLog("AddGameFragment")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddGameBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.radioPlayerCount3.setOnClickListener { selectedPlayerCount(3) }
        binding.radioPlayerCount4.setOnClickListener { selectedPlayerCount(4) }
        binding.radioPlayerCount5.setOnClickListener { selectedPlayerCount(5) }
        binding.radioPlayerCount6.setOnClickListener { selectedPlayerCount(6) }
        binding.saveButton.setCustomClickListener { addButtonPressed() }
        binding.cancelButton.setCustomClickListener { handleBackButtonPressed() }
        binding.actionBar.setBackListener { handleBackButtonPressed() }
        binding.actionBar.setMenuListener { addButtonPressed() }

        binding.editTextFinalTargetScore.filters = GPConst.FIVE_LENGTH_NUMBER_FILTER

        selectedPlayerCount(3) // first time
    }

    override fun handleBackButtonPressed(): Boolean {
        hideSystemKeyboardIfPossible()
        changeFragment(this, TransactionType.REMOVE_FRAGMENT)
        return true
    }

    /**
     * Visibility of edit texts for player name 4, 5 & 6 is changeable,
     * Visibility of edit texts for player name 1, 2 & 3 is always visible,
     */
    private fun selectedPlayerCount(count: Int) {
        viewLifeCycleOwnerScope?.launch(Dispatchers.Main) {
            binding.radioPlayerCount3.isChecked = (count == 3)
            binding.radioPlayerCount4.isChecked = (count == 4)
            binding.radioPlayerCount5.isChecked = (count == 5)
            binding.radioPlayerCount6.isChecked = (count == 6)
            binding.editTextPlayerName4.visibility = when (4 <= count) {
                true -> View.VISIBLE
                else -> View.GONE
            }
            binding.editTextPlayerName5.visibility = when (5 <= count) {
                true -> View.VISIBLE
                else -> View.GONE
            }
            binding.editTextPlayerName6.visibility = when (6 <= count) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

    private fun addButtonPressed() {
        viewLifeCycleOwnerScope?.launch {
            val mGameId = System.currentTimeMillis()
            val mGameName = binding.editTextGameName.text.toString().trim()
            if (mGameName.isEmpty()) {
                showToastMessage("Enter game name")
                return@launch
            }
            val mFinalTarget = binding.editTextFinalTargetScore.text.toString()
            if (mFinalTarget.isEmpty()) {
                showToastMessage("Enter final target score")
                return@launch
            }
            val mFinalTargetScore = mFinalTarget.toInt()
            val mPlayerCount = when {
                binding.radioPlayerCount3.isChecked -> 3
                binding.radioPlayerCount4.isChecked -> 4
                binding.radioPlayerCount5.isChecked -> 5
                binding.radioPlayerCount6.isChecked -> 6
                else -> 4
            }
            val mPlayerNames = getPlayerNameFromEditTexts(mPlayerCount)
            for (i in 0 until mPlayerCount) {
                if (mPlayerNames[i].isEmpty()) {
                    showToastMessage("Enter all player's name")
                    return@launch
                }
            }
            for (i in 0 until mPlayerCount) {
                for (j in 0 until mPlayerCount) {
                    if (i != j && mPlayerNames[i] == mPlayerNames[j]) {
                        showToastMessage("Same name not allowed")
                        return@launch
                    }
                }
            }

            val gameEntity = GameEntity(
                gameId = mGameId,
                gameName = mGameName,
                totalPlayer = mPlayerCount,
                playerName1 = mPlayerNames[0],
                playerName2 = mPlayerNames[1],
                playerName3 = mPlayerNames[2],
                playerName4 = mPlayerNames[3],
                playerName5 = mPlayerNames[4],
                playerName6 = mPlayerNames[5],
                targetScore = mFinalTargetScore,
                notifyOnTargetReached = binding.checkboxNotifyFinalTarget.isChecked
            )

            hideSystemKeyboardIfPossible()
            viewModel.insertNewGame(gameEntity)
            showToastMessage("Saved successfully")
            changeFragment(
                fragment = this@AddGameFragment,
                transactionType = TransactionType.REMOVE_FRAGMENT
            )
        }
    }

    private fun getPlayerNameFromEditTexts(playerCount: Int): Array<String> {
        val playerName = mutableListOf<String>()
        playerName.add(binding.editTextPlayerName1.text.toString().trim())
        playerName.add(binding.editTextPlayerName2.text.toString().trim())
        playerName.add(binding.editTextPlayerName3.text.toString().trim())
        when (4 <= playerCount) {
            true -> playerName.add(binding.editTextPlayerName4.text.toString().trim())
            else -> playerName.add("")
        }
        when (5 <= playerCount) {
            true -> playerName.add(binding.editTextPlayerName5.text.toString().trim())
            false -> playerName.add("")
        }
        when (6 <= playerCount) {
            true -> playerName.add(binding.editTextPlayerName6.text.toString().trim())
            false -> playerName.add("")
        }
        return playerName.toTypedArray()
    }

}