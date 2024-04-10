package com.app.basics.daggerhilt.di.app

import android.app.Application
import androidx.room.Room
import com.app.basics.daggerhilt.storage.db.StackOverflowDb
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object StorageModule {

    @Provides
    @Singleton
    fun provideStackOverflowDb(context: Application): StackOverflowDb {
        return Room.databaseBuilder(
            context, StackOverflowDb::class.java, "stack_overflow_db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideQuestionDao(stackOverflowDb: StackOverflowDb) = stackOverflowDb.questionDao()
}