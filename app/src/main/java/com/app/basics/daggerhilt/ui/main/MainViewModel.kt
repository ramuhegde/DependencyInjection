package com.app.basics.daggerhilt.ui.main

import androidx.lifecycle.ViewModel
import com.app.basics.daggerhilt.network.model.QuestionApi
import com.app.basics.daggerhilt.repo.QuestionsRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: QuestionsRepo) : ViewModel() {

    suspend fun getQuestions(): List<QuestionApi> {
        return repo.getQuestions()
    }
}