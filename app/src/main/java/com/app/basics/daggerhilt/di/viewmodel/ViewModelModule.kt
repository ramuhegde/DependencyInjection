package com.app.basics.daggerhilt.di.viewmodel

import androidx.lifecycle.ViewModel
import com.app.basics.daggerhilt.ui.main.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(questionsViewModel: MainViewModel): ViewModel
}