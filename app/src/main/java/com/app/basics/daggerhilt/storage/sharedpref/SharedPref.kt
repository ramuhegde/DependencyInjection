package com.app.basics.daggerhilt.storage.sharedpref

import android.content.Context
import javax.inject.Inject

class SharedPref @Inject constructor(context: Context) {

    companion object {
        const val SHARED_PREF_NAME = "stack_overflow_prefs"

        private const val LAST_API_CALL_TIME = "last_call_time"
    }

    private val sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)
    private val editor = sharedPrefs.edit()

    suspend fun saveLastApiCallTime(timeInMillis: Long) {
        editor.putLong(LAST_API_CALL_TIME, timeInMillis)
        editor.apply()
    }

    suspend fun getLastApiCallTime(): Long {
        return sharedPrefs.getLong(LAST_API_CALL_TIME, 0)
    }
}