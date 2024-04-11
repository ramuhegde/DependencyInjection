package com.app.basics.daggerhilt.di.app

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.app.basics.daggerhilt.di.activity.ActivityComponent
import com.app.basics.daggerhilt.di.activity.ActivityModule
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.network.QuestionsApi
import com.app.basics.daggerhilt.storage.db.QuestionDao
import com.app.basics.daggerhilt.storage.db.StackOverflowDb
import com.app.basics.daggerhilt.storage.sharedpref.SharedPref
import dagger.BindsInstance
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Component(modules = [AppModule::class, NetworkModule::class, StorageModule::class])
@Singleton
// Component should be scoped with same scope annotation as it's Module class
// In this case AppModule and NetworkModule is scoped by @Singleton
interface CoreComponent {

    // All the providers function defined in the module, need to be declared in component.
    // This helps dagger to generate provider function based on Module.
    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider
    fun provideRetrofit(): Retrofit
    fun provideQuestionsApi(): QuestionsApi
    fun provideStackOverflowDb(): StackOverflowDb
    fun provideQuestionDao(): QuestionDao
    fun provideDataStorePref(): DataStore<Preferences>
    fun provideSharedPref(): SharedPref

    // ActivityComponent is a SubComponent of CoreComponent.
    // SubComponent class needs to be provided by declaring them in their parent component class
    // Hence ActivityComponent is declared here.
    fun provideActivityComponent(activityModule: ActivityModule): ActivityComponent

    // Builder is used for exposing bootstrapping dependencies
    // Since we are using an object, we cannot provide application instance using constructor
    // Hence using @BindsInstance to provide application instance
    // Below is the way to provide application instance using the builder
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build(): CoreComponent
    }

    // In case of
    // @Component(dependencies = [class name], modules = [AppModule::class, NetworkModule::class])
    // No need of providing the child component in the parent component class
    // But all the providers function needs to be re-declared in the child component
    // check ActivityComponent for the example.
}