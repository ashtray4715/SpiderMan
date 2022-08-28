package com.ashtray.spiderman.common.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import androidx.fragment.app.commitNow
import androidx.lifecycle.lifecycleScope
import com.ashtray.spiderman.R
import com.ashtray.spiderman.common.app.GPFactory
import com.ashtray.spiderman.common.helpers.GPLog
import com.ashtray.spiderman.common.helpers.GPSafeRun
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.ashtray.spiderman.common.ui.GPFragment.TransactionType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GPActivity : AppCompatActivity(), GPFragment.CallBacks {

    @Inject
    lateinit var factory: GPFactory

    private val log = GPLog("GPActivity")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gp)
        log.d("onCreate: called")

        changeFragment(
            factory.getSplashScreenFragment(),
            TransactionType.SINGLE_FRAGMENT
        )
    }

    override fun onBackPressed() {
        val unRef = supportFragmentManager.findFragmentById(R.id.fragment_container)
        val fragment = unRef as GPFragment?
        if (fragment == null || !fragment.handleBackButtonPressed()) {
            log.d("fragment can't handle back press [CLOSING_APP]")
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
        log.d("changeFragment: add, ${fragment.log.tag}")
        withContext(Dispatchers.Main) {
            GPSafeRun(log) {
                supportFragmentManager.commit {
                    setCustomAnimations(R.anim.enter_right, R.anim.exit_left)
                    add(R.id.fragment_container, fragment, fragment.log.tag)
                }
            }
        }
    }

    private suspend fun changeFragmentRemove(fragment: GPFragment) {
        log.d("changeFragment: remove, ${fragment.log.tag}")
        withContext(Dispatchers.Main) {
            GPSafeRun(log) {
                supportFragmentManager.commit {
                    setCustomAnimations(R.anim.enter_left, R.anim.exit_right)
                    remove(fragment)
                }
            }
        }
    }

    private suspend fun changeFragmentSingle(fragment: GPFragment) {
        log.d("changeFragment: clear_all_and_add_new, ${fragment.log.tag}")
        withContext(Dispatchers.Main) {
            GPSafeRun(log) {
                for (previousFragment in supportFragmentManager.fragments) {
                    supportFragmentManager.commitNow { remove(previousFragment) }
                }
                supportFragmentManager.commit {
                    setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                    add(R.id.fragment_container, fragment, fragment.log.tag)
                }
            }
        }
    }
}