package com.app.basics.daggerhilt.di.app

import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCoroutineDispatcherProvider(): CoroutineDispatcherProvider =
        CoroutineDispatcherProviderImpl()
}