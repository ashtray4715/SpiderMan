package com.ashtray.spiderman.features.details

import android.content.Context
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.ui.GPStartCallItem
import com.ashtray.spiderman.common.ui.GPDialog
import com.ashtray.spiderman.common.ui.GPITButton
import com.ashtray.spiderman.database.GameEntity
import com.ashtray.spiderman.database.ScoreEntity

class StartCall3PDialog(
    context: Context,
    val gameEntity: GameEntity
) : GPDialog(context) {

    interface CallBacks {
        fun onSaveBtnPressed(scoreEntity: ScoreEntity)
    }

    private lateinit var item1: GPStartCallItem
    private lateinit var item2: GPStartCallItem
    private lateinit var item3: GPStartCallItem

    private lateinit var tvSaveBtn: GPITButton
    private lateinit var tvCancelBtn: GPITButton

    private var callBacks: CallBacks? = null

    fun setCallBacks(callBacks: CallBacks) {
        this.callBacks = callBacks
    }

    override fun getLayoutId(): Int {
        return R.layout.dialog_start_call_3p
    }

    override fun initializeComponents() {
        item1 = findViewById(R.id.item1)
        item2 = findViewById(R.id.item2)
        item3 = findViewById(R.id.item3)
        tvSaveBtn = findViewById(R.id.save_button)
        tvCancelBtn = findViewById(R.id.cancel_button)
    }

    override fun drawBeforeAddingObservers() {
        item1.setScoreHint(String.format("%s's score", gameEntity.playerName1))
        item2.setScoreHint(String.format("%s's score", gameEntity.playerName2))
        item3.setScoreHint(String.format("%s's score", gameEntity.playerName3))
    }

    override fun addHandlersAndObservers() {
        tvSaveBtn.setCustomClickListener { handleSaveButtonPressed() }
        tvCancelBtn.setCustomClickListener { dismiss() }
    }

    private fun handleSaveButtonPressed() {
        callBacks?.onSaveBtnPressed(
            ScoreEntity(
                gameId = gameEntity.gameId,
                score1 = item1.getScore(),
                score2 = item2.getScore(),
                score3 = item3.getScore(),
                isFinal = false
            )
        )
        dismiss()
    }
}