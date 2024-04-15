package com.app.basics.daggerhilt.di.app

import android.app.Application
import com.app.basics.daggerhilt.util.NetworkUtil
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
    fun provideNetworkUtil(application: Application) = NetworkUtil(application)
}