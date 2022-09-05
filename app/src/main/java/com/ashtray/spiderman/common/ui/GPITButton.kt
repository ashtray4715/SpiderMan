package com.ashtray.spiderman.common.ui

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.helpers.GPLog
import java.lang.Exception

class GPITButton(context: Context, attrs: AttributeSet) : RelativeLayout(context, attrs) {

    private val log = GPLog("GPITButton")

    private var clickListener: OnClickListener? = null

    private var globalView: RelativeLayout? = null
    private var imageView: ImageView? = null
    private var textView: TextView? = null

    private val drawableMap = mapOf(
        0 to R.drawable.group_item_vertical_first_card_bg,
        1 to R.drawable.group_item_middle_card_bg,
        2 to R.drawable.group_item_vertical_last_card_bg,
        3 to R.drawable.group_item_horizontal_first_card_bg,
        4 to R.drawable.group_item_middle_card_bg,
        5 to R.drawable.group_item_horizontal_last_card_bg,
        6 to R.drawable.group_item_single_card_bg
    )

    init {
        setViewAndInitializeComponents(context)
        addHandlerAndListenersWhereNecessary()
        drawComponentsForTheFirstTime(context, attrs)
    }

    fun setCustomClickListener(clickListener: OnClickListener?) {
        this.clickListener = clickListener
    }

    private fun addHandlerAndListenersWhereNecessary() {
        globalView?.setOnClickListener { view -> clickListener?.onClick(view) }
    }

    private fun setViewAndInitializeComponents(context: Context) {
        val cView = inflate(context, R.layout.gp_custom_it_button, this)
        globalView = cView.findViewById(R.id.global_view)
        imageView = cView.findViewById(R.id.img_view)
        textView = cView.findViewById(R.id.text_view)
    }

    @SuppressLint("NonConstantResourceId")
    private fun drawComponentsForTheFirstTime(context: Context, attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.GPITButton)
        try {
            val paramCount = typedArray.indexCount
            for (i in 0 until paramCount) {
                when (val currentAttribute = typedArray.getIndex(i)) {
                    R.styleable.GPITButton_itb_text -> {
                        textView?.text = typedArray.getString(currentAttribute)
                    }
                    R.styleable.GPITButton_itb_icon -> {
                        val resId0 = typedArray.getResourceId(currentAttribute, -1)
                        val drawable0 = ContextCompat.getDrawable(context, resId0)
                        imageView?.setImageDrawable(drawable0)
                    }
                    R.styleable.GPITButton_itb_background -> {
                        val defDrawable = R.drawable.group_item_middle_card_bg
                        val input = typedArray.getInt(currentAttribute, 1)
                        val drawable = drawableMap[input] ?: defDrawable
                        globalView?.background = ContextCompat.getDrawable(context, drawable)
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