package com.app.basics.daggerhilt.di.app

import com.app.basics.daggerhilt.di.activity.ActivityComponent
import com.app.basics.daggerhilt.di.activity.ActivityModule
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.network.QuestionsApi
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class])
@Singleton
// Component should be scoped with same scope annotation as it's Module class
// In this case AppModule and NetworkModule is scoped by @Singleton
interface CoreComponent {

    // All the providers function defined in the module, need to be declared in component.
    // This helps dagger to generate provider function based on Module.
    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider
    fun provideRetrofit(): Retrofit
    fun provideQuestionsApi(): QuestionsApi

    // ActivityComponent is a SubComponent of CoreComponent.
    // SubComponent class needs to be provided by declaring them in their parent component class
    // Hence ActivityComponent is declared here.
    fun provideActivityComponent(activityModule: ActivityModule): ActivityComponent

    // In case of
    // @Component(dependencies = [class name], modules = [AppModule::class, NetworkModule::class])
    // No need of providing the child component in the parent component class
    // But all the providers function needs to be re-declared in the child component
    // check ActivityComponent for the example.
}