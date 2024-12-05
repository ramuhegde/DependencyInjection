package com.app.basics.daggerhilt.ui

import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun NewsCard(title: String, description: String) {
    Card {
        Text(text = title)
        Text(text = description)
    }
}