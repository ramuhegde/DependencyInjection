package com.app.basics.daggerhilt.di.app

import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProviderImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
object AppModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider =
        CoroutineDispatcherProviderImpl()
}