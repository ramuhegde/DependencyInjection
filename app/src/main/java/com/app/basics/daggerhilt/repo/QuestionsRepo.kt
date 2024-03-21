package com.app.basics.daggerhilt.repo

import com.app.basics.daggerhilt.network.model.Question

interface QuestionsRepo {
    suspend fun getQuestions(): List<Question>
}