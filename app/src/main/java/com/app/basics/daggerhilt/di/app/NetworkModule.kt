package com.app.basics.daggerhilt.di.app

import android.app.Application
import com.app.basics.daggerhilt.network.QuestionsApi
import com.app.basics.daggerhilt.util.NetworkUtil
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    private const val BASE_URL = "https://api.stackexchange.com"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideQuestionsApi(retrofit: Retrofit): QuestionsApi {
        return retrofit.create(QuestionsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkUtil(application: Application): NetworkUtil = NetworkUtil(application)

}