package com.app.basics.daggerhilt.repo

import android.util.Log
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.network.QuestionsApi
import com.app.basics.daggerhilt.storage.StorageManager
import com.app.basics.daggerhilt.util.NetworkUtil
import com.app.basics.daggerhilt.util.toQuestionsList
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class QuestionsRepoImpl @Inject constructor(
    private val questionsApi: QuestionsApi,
    private val storageManager: StorageManager,
    private val networkUtil: NetworkUtil,
    private val dispatcher: CoroutineDispatcherProvider
) : QuestionsRepo {

    companion object {
        private const val TAG = "QuestionsRepoImpl"

        private const val TIME_IN_24HRS = 24 * 60 * 60 * 1000
    }

    override suspend fun getQuestions() = withContext(dispatcher.ioDispatcher) {
        return@withContext try {
            if (networkUtil.isConnectedToInternet()) {
                val questions = questionsApi.getQuestions().questions.toQuestionsList()

                val lastApiCallTime = storageManager.getLastApiCallTime()
                if (lastApiCallTime == 0L || System.currentTimeMillis() >= lastApiCallTime + TIME_IN_24HRS) {
                    launch {
                        Log.v(TAG, "Last API call cache is greater than 24hr, saving it in DB")
                        storageManager.saveQuestionsList(questions)
                        storageManager.saveLastApiCallTime(System.currentTimeMillis())
                    }
                } else {
                    Log.v(TAG, "Last API call cache is less than 24hr, Skipping the caching")
                }
                questions
            } else {
                Log.v(TAG, "Device is offline, Getting the questions from DB")
                storageManager.getQuestionsList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "Failed to get questions from API: ${e.message}")
            Log.v(TAG, "Getting the questions from DB")
            storageManager.getQuestionsList()
        }
    }
}