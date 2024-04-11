package com.app.basics.daggerhilt.network.model

import com.google.gson.annotations.SerializedName

data class QuestionApi(
    val tags: List<String>,
    val owner: OwnerApi,
    @SerializedName("is_answered")
    val isAnswered: Boolean,
    @SerializedName("view_count")
    val viewCount: Int,
    @SerializedName("answer_count")
    val answerCount: Int,
    val score: Int,
    @SerializedName("last_activity_date")
    val lastActivityDate: Long,
    @SerializedName("creation_date")
    val creationDate: Long,
    @SerializedName("question_id")
    val questionId: Int,
    val link: String,
    val title: String
)
