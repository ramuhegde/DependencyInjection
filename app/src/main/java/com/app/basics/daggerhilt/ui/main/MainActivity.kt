package com.app.basics.daggerhilt.ui.main

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.app.basics.daggerhilt.databinding.ActivityMainBinding
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    @Inject
    lateinit var dispatcher: CoroutineDispatcherProvider

    private val coroutineScope by lazy { CoroutineScope(dispatcher.mainDispatcher) }

    private lateinit var questionsText: TextView
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val questionsAdapter = QuestionsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        // Alternate way to create viewmodel instance
        // viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        questionsText = findViewById(R.id.questions_text_view)
    }

    override fun onStart() {
        super.onStart()

        binding.questionsRecyclerView.adapter = questionsAdapter

        coroutineScope.launch(dispatcher.ioDispatcher) {
            val list = viewModel.getQuestions()
            withContext(dispatcher.mainDispatcher) {
                questionsAdapter.bindQuestions(list)
            }
        }
    }
}