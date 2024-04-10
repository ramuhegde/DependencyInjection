package com.app.basics.daggerhilt.di.app

import android.app.Application
import androidx.room.Room
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.storage.StorageManager
import com.app.basics.daggerhilt.storage.db.QuestionDao
import com.app.basics.daggerhilt.storage.db.StackOverflowDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
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
    fun provideStorageManager(dao: QuestionDao, dispatcher: CoroutineDispatcherProvider) =
        StorageManager(dao, dispatcher)
}