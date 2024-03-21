package com.app.basics.daggerhilt.di.viewmodel

import com.app.basics.daggerhilt.di.activity.ActivityComponent
import com.app.basics.daggerhilt.di.activity.ActivityModule
import com.app.basics.daggerhilt.repo.QuestionsRepo
import dagger.Component
import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class])
interface ViewModelComponent {
    fun provideQuestionsRepo(): QuestionsRepo
    fun provideActivityComponent(activityModule: ActivityModule): ActivityComponent
}