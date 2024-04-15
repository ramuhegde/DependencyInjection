package com.app.basics.daggerhilt.storage.preference

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PrefsDataStore @Inject constructor(private val dataStore: DataStore<Preferences>) {

    object PreferenceKeys {
        val LAST_API_CALL_TIME = longPreferencesKey("last_save_time")
    }

    suspend fun saveLastApiCallTime(value: Long) {
        dataStore.edit { prefs ->
            prefs[PreferenceKeys.LAST_API_CALL_TIME] = value
        }
    }

    suspend fun getLastApiCallTime(): Long {
        return dataStore.data.map { prefs ->
            prefs[PreferenceKeys.LAST_API_CALL_TIME] ?: 0
        }.first()
    }
}