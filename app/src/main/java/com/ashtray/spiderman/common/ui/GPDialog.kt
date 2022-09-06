package com.ashtray.spiderman.common.ui

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.view.Window
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.ashtray.spiderman.R

abstract class GPDialog(context: Context) : Dialog(context) {

    protected abstract fun getLayoutId(): Int

    protected open fun initializeComponents() {
        // do nothing here
    }

    protected open fun drawBeforeAddingObservers() {
        // do nothing here
    }

    protected open fun addHandlersAndObservers() {
        // do nothing here
    }

    protected open fun drawAfterAddingObservers() {
        // do nothing here
    }

    protected fun showToastMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(getLayoutId())
        window?.setLayout(MATCH_PARENT, WRAP_CONTENT)
        window?.setBackgroundDrawable(
            ContextCompat.getDrawable(
                context,
                R.drawable.common_dialog_background
            )
        )
        window?.setDimAmount(0.97f)
        window?.attributes?.windowAnimations = R.style.DialogScaleAnimation
        setCancelable(false)

        initializeComponents()

        drawBeforeAddingObservers()

        addHandlersAndObservers()

        drawAfterAddingObservers()
    }
}