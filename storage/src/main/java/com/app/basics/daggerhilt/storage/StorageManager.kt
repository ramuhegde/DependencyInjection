package com.app.basics.daggerhilt.storage

import com.app.basics.daggerhilt.storage.db.Question

interface StorageManager {
    suspend fun getQuestionsList(): List<Question>
    suspend fun saveQuestionsList(questions: List<Question>)
    suspend fun getLastApiCallTime(): Long
    suspend fun saveLastApiCallTime(timeInMillis: Long)
}