package com.app.basics.daggerhilt.di

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProviderImpl
import com.app.basics.daggerhilt.storage.StorageManager
import com.app.basics.daggerhilt.storage.StorageManagerImpl
import com.app.basics.daggerhilt.storage.db.QuestionDao
import com.app.basics.daggerhilt.storage.db.StackOverflowDb
import com.app.basics.daggerhilt.storage.preference.PrefsDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StorageModule {

    private const val DB_NAME = "stack_overflow_db"
    private const val PREFERENCES_FILE_NAME = "stack_overflow_prefs"

    @Provides
    @Singleton
    fun provideStackOverflowDb(context: Application): StackOverflowDb {
        return Room.databaseBuilder(
            context, StackOverflowDb::class.java, DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideQuestionDao(stackOverflowDb: StackOverflowDb): QuestionDao =
        stackOverflowDb.questionDao()

    @Provides
    @Singleton
    fun providePreferenceDataStore(
        @ApplicationContext context: Context,
        dispatcher: CoroutineDispatcherProvider
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.create(
            // To handle any exception by the serializer during deserialization
            // Corruption Handler will handle the data corruption accordingly
            corruptionHandler = ReplaceFileCorruptionHandler(produceNewData = { emptyPreferences() }),
            // Add migration only when needed
            // migrations = listOf(SharedPreferencesMigration(context, PREFERENCES_FILE_NAME)),
            // Scope for the data store
            scope = CoroutineScope(dispatcher.ioDispatcher + SupervisorJob()),
            // File to save the data store.
            produceFile = { context.preferencesDataStoreFile(PREFERENCES_FILE_NAME) }
        )
    }

    @Provides
    @Singleton
    fun providePrefsDataStore(dataStore: DataStore<Preferences>): PrefsDataStore =
        PrefsDataStore(dataStore)

    @Provides
    @Singleton
    fun provideStorageManager(
        dao: QuestionDao,
        prefsDataStore: PrefsDataStore,
        dispatcher: CoroutineDispatcherProvider
    ): StorageManager = StorageManagerImpl(dao, prefsDataStore, dispatcher)

    @Provides
    @Singleton
    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider =
        CoroutineDispatcherProviderImpl()
}