package com.app.basics.daggerhilt.repo

import com.app.basics.daggerhilt.storage.db.Question

interface QuestionsRepo {
    suspend fun getQuestions(): List<Question>
}