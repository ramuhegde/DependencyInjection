package com.app.basics.daggerhilt.storage

import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.storage.db.Question
import com.app.basics.daggerhilt.storage.db.QuestionDao
import com.app.basics.daggerhilt.storage.sharedpref.SharedPref
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageManager @Inject constructor(
    private val dao: QuestionDao,
    private val sharedPref: SharedPref,
    private val dispatchers: CoroutineDispatcherProvider
) {

    suspend fun getQuestionsList() = withContext(dispatchers.ioDispatcher) {
        dao.getAllQuestions()
    }

    suspend fun saveQuestionsList(questions: List<Question>) = withContext(dispatchers.ioDispatcher) {
        dao.saveQuestionList(questions)
    }

    suspend fun getLastApiCallTime() = sharedPref.getLastApiCallTime()

    suspend fun saveLastApiCallTime(timeInMillis: Long) {
        sharedPref.saveLastApiCallTime(timeInMillis)
    }
}