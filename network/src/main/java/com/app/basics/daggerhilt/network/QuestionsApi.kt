package com.app.basics.daggerhilt.network

import com.app.basics.daggerhilt.network.model.QuestionsResponse
import retrofit2.http.GET

interface QuestionsApi {

    @GET("/2.3/questions?order=desc&sort=activity&site=stackoverflow")
    suspend fun getQuestions(): QuestionsResponse
}