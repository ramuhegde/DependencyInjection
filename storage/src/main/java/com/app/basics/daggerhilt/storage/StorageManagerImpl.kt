package com.app.basics.daggerhilt.storage

import android.util.Log
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.storage.db.Question
import com.app.basics.daggerhilt.storage.db.QuestionDao
import com.app.basics.daggerhilt.storage.preference.PrefsDataStore
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageManagerImpl @Inject constructor(
    private val dao: QuestionDao,
    private val prefsDataStore: PrefsDataStore,
    private val dispatcher: CoroutineDispatcherProvider
): StorageManager {

    companion object {
        private const val TAG = "StorageManager"
    }

    override suspend fun getQuestionsList(): List<Question> = withContext(dispatcher.ioDispatcher) {
        return@withContext dao.getQuestions()
    }

    override suspend fun saveQuestionsList(questions: List<Question>) =
        withContext(dispatcher.ioDispatcher) {
            Log.v(TAG, "Successfully saved questions list in Db")
            dao.insertQuestions(questions)
        }


    override suspend fun getLastApiCallTime() = prefsDataStore.getLastApiCallTime()

    override suspend fun saveLastApiCallTime(timeInMillis: Long) =
        prefsDataStore.saveLastApiCallTime(timeInMillis)
}