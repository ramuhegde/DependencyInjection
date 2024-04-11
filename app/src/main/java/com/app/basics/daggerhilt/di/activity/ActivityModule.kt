package com.app.basics.daggerhilt.di.activity

import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.di.scope.ActivityScope
import com.app.basics.daggerhilt.network.QuestionsApi
import com.app.basics.daggerhilt.repo.QuestionsRepo
import com.app.basics.daggerhilt.repo.QuestionsRepoImpl
import com.app.basics.daggerhilt.storage.StorageManager
import com.app.basics.daggerhilt.util.NetworkUtil
import dagger.Module
import dagger.Provides

@Module
object ActivityModule {

    @Provides
    @ActivityScope
    fun provideQuestionsRepo(
        questionsApi: QuestionsApi,
        storageManager: StorageManager,
        networkUtil: NetworkUtil,
        dispatchers: CoroutineDispatcherProvider
    ): QuestionsRepo = QuestionsRepoImpl(questionsApi, storageManager, networkUtil, dispatchers)
}