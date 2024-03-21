package com.app.basics.daggerhilt.ui.main

import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.app.basics.daggerhilt.R
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

    private lateinit var questionsText: TextView
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        injector.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

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