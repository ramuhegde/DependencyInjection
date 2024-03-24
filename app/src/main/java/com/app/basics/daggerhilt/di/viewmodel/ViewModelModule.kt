package com.app.basics.daggerhilt.di.viewmodel

import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.network.QuestionsApi
import com.app.basics.daggerhilt.repo.QuestionsRepo
import com.app.basics.daggerhilt.repo.QuestionsRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ViewModelModule {

    @Provides
    fun provideQuestionsRepo(
        questionsApi: QuestionsApi,
        dispatchers: CoroutineDispatcherProvider
    ): QuestionsRepo = QuestionsRepoImpl(questionsApi, dispatchers)
}