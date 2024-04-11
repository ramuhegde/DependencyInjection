package com.app.basics.daggerhilt.repo

import android.util.Log
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.network.QuestionsApi
import com.app.basics.daggerhilt.network.model.QuestionApi
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
        private const val TAG = "QuestionsRepo"
        private const val TIME_IN_24_HRS = 24 * 60 * 60 * 1000
    }

    override suspend fun getQuestions() = withContext(dispatcher.ioDispatcher) {
        return@withContext if (networkUtil.isConnectedToNetwork()) {
            try {
                val questionsList = questionsApi.getQuestions().questions.toQuestionsList()
                // Save the questions in Db only if the time gap is greater than or equal to 24hrs.
                val lastApiCallTime = storageManager.getLastApiCallTime()
                if (lastApiCallTime == 0L || System.currentTimeMillis() >= lastApiCallTime + TIME_IN_24_HRS) {
                    launch {
                        Log.v(TAG, "Last API call is greater than 24hr, save the data in DB")
                        storageManager.saveQuestionsList(questionsList)
                        // Save the API call time.
                        storageManager.saveLastApiCallTime(System.currentTimeMillis())
                    }
                } else {
                    Log.v(TAG, "Last API call is less than 24hr, skipping the caching")
                }
                Log.v(TAG, "Successfully fetched questions from API")
                questionsList
            } catch (e: Exception) {
                Log.e(TAG, "Failed to get questions from API: ${e.message}")
                e.printStackTrace()
                Log.v(TAG, "Getting questions from Db")
                storageManager.getQuestionsList()
            }
        } else {
            Log.v(TAG, "Device is not connected to internet, Getting questions from Db")
            storageManager.getQuestionsList()
        }
    }
}