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
    }

    override suspend fun getQuestions() = withContext(dispatcher.ioDispatcher) {
        return@withContext if (networkUtil.isConnectedToNetwork()) {
            try {
                val questionsList = questionsApi.getQuestions().questions.toQuestionsList()
                // Save the questions in Db.
                launch {
                    storageManager.saveQuestionsList(questionsList)
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