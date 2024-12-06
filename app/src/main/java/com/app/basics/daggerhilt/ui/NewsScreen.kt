package com.app.basics.daggerhilt.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.basics.daggerhilt.R
import com.app.basics.daggerhilt.storage.db.Question

private const val TAG = "NewsScreen"

@Composable
fun NewsList(modifier: Modifier, questions: List<Question>) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.questions),
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.titleLarge,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium
        )
        LazyColumn {
            items(questions) { item ->
                NewsCard(question = item) { question ->
                    Log.d(TAG, "Clicked on ${question.title}")
                }
            }
        }
    }
}

@Composable
fun NewsCard(question: Question, onClick: (question: Question) -> Unit) {
    val haptic = LocalHapticFeedback.current
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.LightGray),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        onClick = {
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            onClick(question)
        }
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            // Question Title
            Text(
                text = question.title,
                style = MaterialTheme.typography.titleMedium,
                fontSize = 16.sp
            )
            // Question Owner
            Text(
                text = stringResource(id = R.string.owner_name, question.owner.displayName),
                modifier = Modifier.align(Alignment.End)
            )
        }
    }
}