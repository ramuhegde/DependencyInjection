package com.app.basics.daggerhilt.repo

import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.network.QuestionsApi
import com.app.basics.daggerhilt.network.model.QuestionApi
import kotlinx.coroutines.withContext

class QuestionsRepoImpl(
    private val questionsApi: QuestionsApi,
    private val dispatcher: CoroutineDispatcherProvider
) : QuestionsRepo {

    override suspend fun getQuestions() = withContext(dispatcher.ioDispatcher) {
        return@withContext try {
            questionsApi.getQuestions().questions
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList<QuestionApi>()
        }

    }
}