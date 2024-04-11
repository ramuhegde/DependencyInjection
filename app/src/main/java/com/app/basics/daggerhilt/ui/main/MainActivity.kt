package com.app.basics.daggerhilt.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.lifecycle.ViewModelProvider
import com.app.basics.daggerhilt.databinding.ActivityMainBinding
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import com.app.basics.daggerhilt.di.viewmodel.ViewModelFactory
import com.app.basics.daggerhilt.ui.BaseActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var dispatcher: CoroutineDispatcherProvider

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val coroutineScope by lazy { CoroutineScope(dispatcher.mainDispatcher) }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    private val questionsAdapter = QuestionsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
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