package com.app.basics.daggerhilt.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.app.basics.daggerhilt.ui.NewsCard
import com.app.basics.daggerhilt.ui.theme.QuestionAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestionAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val paddingModifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                    BindNewsCards(modifier = paddingModifier)
                }
            }
        }
    }

    @Composable
    private fun BindNewsCards(modifier: Modifier) {
        LaunchedEffect(Unit) {
            viewModel.getQuestions()
        }

        LazyColumn(modifier = modifier) {
            items(viewModel.questions) { item ->
                NewsCard(title = item.title, description = item.owner.displayName)
            }
        }
    }
}