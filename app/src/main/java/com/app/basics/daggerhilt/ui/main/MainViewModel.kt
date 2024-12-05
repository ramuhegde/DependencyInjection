package com.app.basics.daggerhilt.ui.main

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.basics.daggerhilt.repo.QuestionsRepo
import com.app.basics.daggerhilt.storage.db.Question
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repo: QuestionsRepo) : ViewModel() {

    private val _questions = mutableStateListOf<Question>()
    val questions get() = _questions

    suspend fun getQuestions() {
        viewModelScope.launch {
            val questionList = repo.getQuestions()
            _questions.clear()
            _questions.addAll(questionList)
        }
    }
}