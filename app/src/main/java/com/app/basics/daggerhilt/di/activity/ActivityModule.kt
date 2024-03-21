package com.app.basics.daggerhilt.di.activity

import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.di.scope.ActivityScope
import com.app.basics.daggerhilt.network.QuestionsApi
import com.app.basics.daggerhilt.repo.QuestionsRepo
import com.app.basics.daggerhilt.repo.QuestionsRepoImpl
import dagger.Module
import dagger.Provides

@Module
object ActivityModule {

    @Provides
    @ActivityScope
    fun provideQuestionsRepo(
        questionsApi: QuestionsApi,
        dispatchers: CoroutineDispatcherProvider
    ): QuestionsRepo = QuestionsRepoImpl(questionsApi, dispatchers)
}