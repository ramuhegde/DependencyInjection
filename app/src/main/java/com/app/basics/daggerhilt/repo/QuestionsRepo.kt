package com.app.basics.daggerhilt.repo

import com.app.basics.daggerhilt.network.model.QuestionApi

interface QuestionsRepo {
    suspend fun getQuestions(): List<QuestionApi>
}