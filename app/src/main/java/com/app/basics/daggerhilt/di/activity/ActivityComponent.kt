package com.app.basics.daggerhilt.di.activity

import com.app.basics.daggerhilt.di.app.CoreComponent
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.di.scope.ActivityScope
import com.app.basics.daggerhilt.di.viewmodel.ViewModelModule
import com.app.basics.daggerhilt.network.QuestionsApi
import com.app.basics.daggerhilt.repo.QuestionsRepo
import com.app.basics.daggerhilt.ui.main.MainActivity
import dagger.Component
import dagger.Subcomponent
import retrofit2.Retrofit

@Subcomponent(modules = [ActivityModule::class, ViewModelModule::class])
@ActivityScope
// Component should be scoped with same scope annotation as it's Module class
// In this case AppModule and NetworkModule is scoped by @ActivityScope
interface ActivityComponent {

    // All the providers function defined in the module, need to be declared in component.
    // This helps dagger to generate provider function based on Module.
    fun provideQuestionsRepo(): QuestionsRepo

    // Injector
    fun inject(activity: MainActivity)
}


/**
 * If ActivityComponent was depended on CoreComponent instead of using @SubComponent
 */

//@Component(dependencies = [CoreComponent::class], modules = [ActivityModule::class, ViewModelModule::class])
//@ActivityScope
// Component should be scoped with same scope annotation as it's Module class
// In this case AppModule and NetworkModule is scoped by @ActivityScope
//interface ActivityComponent {
//
//    // All the providers function defined in the module, need to be declared in component.
//    // Below are the functions re-declared here.
//    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider
//    fun provideRetrofit(): Retrofit
//    fun provideQuestionsApi(): QuestionsApi

      // ActivityComponent's own provider function (not from CoreComponent)
//    fun provideQuestionsRepo(): QuestionsRepo
//
//    // Injector
//    fun inject(activity: MainActivity)
//}

