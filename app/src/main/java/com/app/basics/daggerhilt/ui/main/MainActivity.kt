package com.app.basics.daggerhilt.ui.main

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.app.basics.daggerhilt.R
import com.app.basics.daggerhilt.di.coroutine.CoroutineDispatcherProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var dispatcher: CoroutineDispatcherProvider

    private val coroutineScope by lazy { CoroutineScope(dispatcher.mainDispatcher) }

    private lateinit var questionsText: TextView
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Alternate way to create viewmodel instance
        // viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        questionsText = findViewById(R.id.questions_text_view)
    }

    override fun onStart() {
        super.onStart()

        coroutineScope.launch(dispatcher.ioDispatcher) {
            val list = viewModel.getQuestions()
            withContext(dispatcher.mainDispatcher) {
                // TODO: Bind it with recycler view
                questionsText.text = list.toString()
            }
        }
    }
}