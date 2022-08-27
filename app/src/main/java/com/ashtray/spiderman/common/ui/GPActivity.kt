package com.ashtray.spiderman.common.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.lifecycle.lifecycleScope
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.app.GPApp
import com.ashtray.spiderman.common.helpers.GPLog.d
import com.ashtray.spiderman.common.helpers.GPSafeRun
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.ashtray.spiderman.common.ui.GPFragment.TransactionType

class GPActivity : AppCompatActivity(), GPFragment.CallBacks {

    private val mTag = "GPActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gp)
        d(mTag, "onCreate: called")

        changeFragment(
            GPApp.getFactory().getSplashScreenFragment(),
            TransactionType.SINGLE_FRAGMENT
        )
    }

    override fun onBackPressed() {
        val unRef = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val fragment = unRef as GPFragment?
        if (fragment == null || !fragment.handleBackButtonPressed()) {
            d(mTag, "fragment can't handle back press [CLOSING_APP]")
            super.onBackPressed()
        }
    }

    override fun changeFragment(fragment: GPFragment, transactionType: TransactionType) {
        lifecycleScope.launch {
            when (transactionType) {
                TransactionType.ADD_FRAGMENT -> changeFragmentAdd(fragment)
                TransactionType.REMOVE_FRAGMENT -> changeFragmentRemove(fragment)
                TransactionType.SINGLE_FRAGMENT -> changeFragmentSingle(fragment)
            }
        }
    }

    override fun showToastMessage(message: String) {
        lifecycleScope.launch(Dispatchers.Main) {
            Toast.makeText(this@GPActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    private suspend fun changeFragmentAdd(fragment: GPFragment) {
        d(mTag, "changeFragment: add, ${fragment.mTag}")
        withContext(Dispatchers.Main) {
            GPSafeRun(mTag) {
                supportFragmentManager.commit {
                    setCustomAnimations(R.anim.enter_right, R.anim.exit_left)
                    add(R.id.fragment_container, fragment, fragment.mTag)
                }
            }
        }
    }

    private suspend fun changeFragmentRemove(fragment: GPFragment) {
        d(mTag, "changeFragment: remove, ${fragment.mTag}")
        withContext(Dispatchers.Main) {
            GPSafeRun(mTag) {
                supportFragmentManager.commit {
                    setCustomAnimations(R.anim.enter_left, R.anim.exit_right)
                    remove(fragment)
                }
            }
        }
    }

    private suspend fun changeFragmentSingle(fragment: GPFragment) {
        d(mTag, "changeFragment: clear_all_and_add_new, ${fragment.mTag}")
        withContext(Dispatchers.Main) {
            GPSafeRun(mTag) {
                for (previousFragment in supportFragmentManager.fragments) {
                    supportFragmentManager.commitNow { remove(previousFragment) }
                }
                supportFragmentManager.commit {
                    setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    add(R.id.fragment_container, fragment, fragment.mTag)
                }
            }
        }
    }

    
}