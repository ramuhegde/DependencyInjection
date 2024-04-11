package com.app.basics.daggerhilt.di.app

import android.app.Application
import androidx.room.Room
import com.app.basics.daggerhilt.storage.db.StackOverflowDb
import com.app.basics.daggerhilt.storage.sharedpref.SharedPref
import dagger.Module
import dagger.Provides
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
    fun provideSharedPref(context: Application): SharedPref = SharedPref(context)
}