package com.ashtray.spiderman.common.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import com.ashtray.spiderman.R

class GPAddScoreCallItem(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private var minusButton: TextView? = null
    private var plusButton: TextView? = null
    private var tvScore: TextView? = null
    private var clearButton: TextView? = null

    init {
        setViewAndInitializeComponents(context)
        addHandlerAndListenersWhereNecessary()
    }

    private fun addHandlerAndListenersWhereNecessary() {
        minusButton?.setOnClickListener {
            val currentScoreString = tvScore?.text?.toString() ?: "0"
            val nextScore = when (currentScoreString.isEmpty()) {
                true -> -1
                else -> currentScoreString.toInt() - 1
            }
            tvScore?.text = nextScore.toString()
        }
        plusButton?.setOnClickListener {
            val currentScoreString = tvScore?.text?.toString() ?: "0"
            val nextScore = when (currentScoreString.isEmpty()) {
                true -> 1
                else -> currentScoreString.toInt() + 1
            }
            tvScore?.text = nextScore.toString()
        }
        clearButton?.setOnClickListener {
            tvScore?.text = ""
        }
    }

    private fun setViewAndInitializeComponents(context: Context) {
        val cView = inflate(context, R.layout.gp_custom_add_score_call_item, this)
        minusButton = cView.findViewById(R.id.minus_button)
        plusButton = cView.findViewById(R.id.plus_button)
        tvScore = cView.findViewById(R.id.tv_score)
        clearButton = cView.findViewById(R.id.clear_button)
    }

    fun setScoreHint(hint: String) {
        tvScore?.hint = hint
    }

    fun getScore(): Int {
        val currentScoreString = tvScore?.text?.toString() ?: "0"
        return when (currentScoreString.isEmpty()) {
            true -> 0
            else -> currentScoreString.toInt()
        }
    }
}