package com.app.basics.daggerhilt.network.model

import com.google.gson.annotations.SerializedName

data class QuestionsResponse(
    @SerializedName("items")
    val questions: List<QuestionApi>,
    @SerializedName("has_more")
    val hasMore: Boolean,
    @SerializedName("quota_max")
    val quotaMax: Int,
    @SerializedName("quota_remaining")
    val quotaRemaining: Int
)
