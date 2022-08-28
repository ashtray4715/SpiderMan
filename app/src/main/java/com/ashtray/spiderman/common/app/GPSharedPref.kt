package com.ashtray.spiderman.common.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Base64
import com.ashtray.spiderman.common.helpers.GPConst
import com.ashtray.spiderman.common.helpers.GPLog
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GPSharedPref @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private val log = GPLog("GPSharedPref")

    /**
     * This setData function can insert any kind of object,
     * list, map etc into the shared preference
     */
    @SuppressLint("ApplySharedPref")
    private fun setData(key: String, value: Any?) {
        try {
            val sData = ByteArrayOutputStream()
            val serializer = ObjectOutputStream(sData)
            serializer.writeObject(value)

            //Insert serialized object into shared preferences
            context.getSharedPreferences(
                GPConst.SHARED_PREF_FILE_NAME,
                Application.MODE_PRIVATE
            ).edit().apply {
                putString(key, Base64.encodeToString(sData.toByteArray(), Base64.DEFAULT))
                commit()
            }
        } catch (e: Exception) {
            log.e("set value failed [key $key]")
            e.printStackTrace()
        }
    }

    /**
     * This getData function can retrieve any kind of object,
     * list, map etc from the shared preference
     */
    private fun getData(key: String): Any? {
        var ret: Any? = null
        try {
            context.getSharedPreferences(
                GPConst.SHARED_PREF_FILE_NAME,
                Application.MODE_PRIVATE
            ).getString(key, null)?.let { sData ->
                val input = ByteArrayInputStream(Base64.decode(sData, Base64.DEFAULT))
                val inputStream = ObjectInputStream(input)
                ret = inputStream.readObject()
            }
        } catch (e: Exception) {
            log.e("get value failed [key $key]")
            e.printStackTrace()
        }
        return ret
    }

    fun getOnBoardingPendingStatus(): Boolean {
        val retValue = getData(GPConst.SP_KEY_ON_BOARDING_STATUS) as Boolean?
        return retValue ?: true
    }

    fun setOnBoardingPendingStatus(status: Boolean) = setData(
        GPConst.SP_KEY_ON_BOARDING_STATUS,
        status
    )
}