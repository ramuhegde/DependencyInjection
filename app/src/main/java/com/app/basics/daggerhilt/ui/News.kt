package com.app.basics.daggerhilt.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NewsCard(title: String, description: String) {
    Card (modifier = Modifier
        .fillMaxSize()
        .padding(8.dp)) {
        Column (modifier = Modifier.padding(8.dp)) {
            Text(text = title)
            Text(text = description)
        }
    }
}