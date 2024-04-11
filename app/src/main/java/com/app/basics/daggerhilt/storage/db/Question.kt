package com.app.basics.daggerhilt.storage.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Question(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val tags: List<String>,
    val owner: Owner,
    val isAnswered: Boolean,
    val viewCount: Int,
    val answerCount: Int,
    val score: Int,
    val lastActivityDate: Int,
    val creationDate: Int,
    val questionId: Int,
    val link: String,
    val title: String
)

data class Owner(
    val reputation: Int,
    val userId: Int,
    val userType: String,
    val profileImage: String,
    val displayName: String,
    val link: String
)
