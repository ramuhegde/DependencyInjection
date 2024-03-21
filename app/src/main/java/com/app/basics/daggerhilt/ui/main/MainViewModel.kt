package com.app.basics.daggerhilt.ui.main

import androidx.lifecycle.ViewModel
import com.app.basics.daggerhilt.network.model.Question
import com.app.basics.daggerhilt.repo.QuestionsRepo
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repo: QuestionsRepo) : ViewModel() {

    suspend fun getQuestions(): List<Question> {
        return repo.getQuestions()
    }
}