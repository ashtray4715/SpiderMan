package com.ashtray.spiderman.common.ui

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import android.widget.TextView
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.helpers.GPLog
import java.lang.Exception

class GPMenuTitle(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private val log = GPLog("GPMenuTitle")

    private var tvTitle: TextView? = null

    init {
        setViewAndInitializeComponents()
        drawComponentsForTheFirstTime(context, attrs)
    }

    private fun setViewAndInitializeComponents() {
        val view = inflate(context, R.layout.gp_custom_menu_title, this)
        tvTitle = view.findViewById(R.id.tv_title)
    }

    private fun drawComponentsForTheFirstTime(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.GPMenuTitle)
        try {
            val paramCount = typedArray.indexCount
            for (i in 0 until paramCount) {
                when (val currentAttribute = typedArray.getIndex(i)) {
                    R.styleable.GPMenuTitle_mt_text -> {
                        tvTitle?.text = (typedArray.getString(currentAttribute))
                    }
                }
            }
        } catch (e: Exception) {
            log.e("drawComponentsForTheFirstTime: problem occurs")
            e.printStackTrace()
        } finally {
            typedArray.recycle()
        }
    }
}