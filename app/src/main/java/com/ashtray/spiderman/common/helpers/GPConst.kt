package com.ashtray.spiderman.common.helpers

import android.text.InputFilter
import android.text.Spanned

object GPConst {
    /* fragment to fragment passing keys */
    const val PK_IS_FROM_SPLASH_FRAGMENT = "is_from_splash_fragment"

    /* shared preference file name*/
    const val SHARED_PREF_FILE_NAME = "spiderman_data"

    /* Shared preference key*/
    const val SP_KEY_ON_BOARDING_STATUS = "on_boarding_status"

    /* Room database*/
    const val APP_DATABASE_NAME = "spiderman_db"

    /* Commonly used 5 length number filter */
    val FIVE_LENGTH_NUMBER_FILTER: Array<InputFilter> = arrayOf(
        getNumberFilterOfUnlimitedLength(),
        InputFilter.LengthFilter(5)
    )

    private fun getNumberFilterOfUnlimitedLength(): InputFilter {
        return object : InputFilter {
            override fun filter(
                string: CharSequence,
                start: Int,
                end: Int,
                spanned: Spanned?,
                dStart: Int,
                dEnd: Int
            ): CharSequence? {
                for (i in start until end) {
                    if (string[i] in '0'..'9') {
                        continue
                    }
                    return ""
                }
                return null
            }
        }
    }
}