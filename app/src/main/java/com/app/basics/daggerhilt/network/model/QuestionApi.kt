package com.app.basics.daggerhilt.network.model

data class QuestionApi(
    val tags: List<String>,
    val owner: OwnerApi,
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
