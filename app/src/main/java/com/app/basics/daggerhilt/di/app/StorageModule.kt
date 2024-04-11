package com.app.basics.daggerhilt.di.app

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.storage.db.StackOverflowDb
import com.app.basics.daggerhilt.storage.sharedpref.SharedPref
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
object StorageModule {

    private const val DB_NAME = "stack_overflow_db"

    @Provides
    @Singleton
    fun provideStackOverflowDb(context: Application): StackOverflowDb {
        return Room.databaseBuilder(
            context, StackOverflowDb::class.java, DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideQuestionDao(stackOverflowDb: StackOverflowDb) = stackOverflowDb.questionDao()


    @Provides
    @Singleton
    fun provideDataStorePref(
        context: Application,
        dispatchers: CoroutineDispatcherProvider
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
            migrations = listOf(SharedPreferencesMigration(context, SharedPref.SHARED_PREF_NAME)),
            scope = CoroutineScope(dispatchers.ioDispatcher + SupervisorJob()),
            produceFile = { context.preferencesDataStoreFile(SharedPref.SHARED_PREF_NAME) }
        )
    }

    @Provides
    @Singleton
    fun provideSharedPref(dataStore: DataStore<Preferences>): SharedPref = SharedPref(dataStore)
}