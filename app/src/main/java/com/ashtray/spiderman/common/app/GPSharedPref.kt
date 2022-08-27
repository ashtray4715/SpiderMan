package com.ashtray.spiderman.common.app

import com.ashtray.spiderman.common.helpers.GPLog.e
import android.annotation.SuppressLint
import android.content.SharedPreferences
import android.util.Base64
import com.ashtray.spiderman.common.helpers.GPConst
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.ObjectInputStream
import java.io.ObjectOutputStream
import java.lang.Exception

class GPSharedPref(private val sharedPrefObj: SharedPreferences) {

    private val mTag = "GPSharedPref"

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
            sharedPrefObj.edit().apply {
                putString(key, Base64.encodeToString(sData.toByteArray(), Base64.DEFAULT))
                commit()
            }
        } catch (e: Exception) {
            e(mTag, "set value failed [key $key]")
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
            sharedPrefObj.getString(key, null) ?. let { sData ->
                val input = ByteArrayInputStream(Base64.decode(sData, Base64.DEFAULT))
                val inputStream = ObjectInputStream(input)
                ret = inputStream.readObject()
            }
        } catch (e: Exception) {
            e(mTag, "get value failed [key $key]")
            e.printStackTrace()
        }
        return ret
    }

    fun getOnBoardingPendingStatus() : Boolean {
        val retValue = getData(GPConst.SP_KEY_ON_BOARDING_STATUS) as Boolean?
        return retValue ?: true
    }

    fun setOnBoardingPendingStatus(status: Boolean) = setData(
        GPConst.SP_KEY_ON_BOARDING_STATUS,
        status
    )
}