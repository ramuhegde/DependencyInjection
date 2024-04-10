package com.app.basics.daggerhilt.di.viewmodel

import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.network.QuestionsApi
import com.app.basics.daggerhilt.repo.QuestionsRepo
import com.app.basics.daggerhilt.repo.QuestionsRepoImpl
import com.app.basics.daggerhilt.storage.StorageManager
import com.app.basics.daggerhilt.util.NetworkUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideQuestionsRepo(
        questionsApi: QuestionsApi,
        storageManager: StorageManager,
        networkUtil: NetworkUtil,
        dispatchers: CoroutineDispatcherProvider
    ): QuestionsRepo = QuestionsRepoImpl(questionsApi, storageManager, networkUtil, dispatchers)
}