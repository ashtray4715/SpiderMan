package com.ashtray.spiderman.common.ui

import com.ashtray.spiderman.common.helpers.GPLog.e
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import java.lang.RuntimeException

abstract class GPFragment : Fragment() {

    interface CallBacks {
        fun changeFragment(fragment: GPFragment, transactionType: TransactionType)
        fun showToastMessage(message: String)
    }

    enum class TransactionType {
        ADD_FRAGMENT, REMOVE_FRAGMENT, SINGLE_FRAGMENT
    }

    private var callBacks: CallBacks? = null

    /**
     * This function will be used, only to initialize the callback, and
     * The callback is responsible for providing ads from the activity.
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        callBacks = when (context is CallBacks) {
            true -> context
            else -> throw RuntimeException("Must implement GPFragment.CallBacks")
        }
    }

    /**
     * This function will be only to de-initialize the callback, cause
     * once the fragment is detached then the callback is no longer needed
     */
    override fun onDetach() {
        callBacks = null
        super.onDetach()
    }

    abstract val mTag: String

    open fun handleBackButtonPressed(): Boolean {
        return false
    }

    protected fun changeFragment(fragment: GPFragment, transactionType: TransactionType) {
        callBacks?.changeFragment(fragment, transactionType)
    }

    protected fun showToastMessage(message: String) {
        callBacks?.showToastMessage(message)
    }

    protected fun hideSystemKeyboardIfPossible() {
        try {
            val wToken = activity?.currentFocus?.windowToken
            val service = context?.getSystemService(Context.INPUT_METHOD_SERVICE)
            val immService =  service as InputMethodManager?
            immService?.hideSoftInputFromWindow(wToken, 0)
        } catch (e: Exception) {
            e(mTag, "exception occurs while closing soft keyboard")
        }
    }

    protected val viewLifeCycleOwnerScope get() = try {
        viewLifecycleOwner.lifecycleScope
    } catch (e: Exception) {
        null
    }
}