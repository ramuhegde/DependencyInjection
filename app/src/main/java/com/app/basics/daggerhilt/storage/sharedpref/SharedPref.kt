package com.app.basics.daggerhilt.storage.sharedpref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SharedPref @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        const val SHARED_PREF_NAME = "stack_overflow_prefs"

        private val LAST_API_CALL_TIME = longPreferencesKey("last_call_time")
    }

    suspend fun saveLastApiCallTime(timeInMillis: Long) {
        dataStore.edit { pref ->
            pref[LAST_API_CALL_TIME] = timeInMillis
        }
    }

    suspend fun getLastApiCallTime(): Long {
        return dataStore.data.map { pref ->
            pref[LAST_API_CALL_TIME] ?: 0
        }.first()
    }
}