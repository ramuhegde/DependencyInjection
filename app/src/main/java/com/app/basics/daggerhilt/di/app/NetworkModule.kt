package com.app.basics.daggerhilt.di.app

import com.app.basics.daggerhilt.network.QuestionsApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.stackexchange.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideQuestionsApi(retrofit: Retrofit): QuestionsApi {
        return retrofit.create(QuestionsApi::class.java)
    }

}