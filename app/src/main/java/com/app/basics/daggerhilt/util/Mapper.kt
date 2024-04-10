package com.app.basics.daggerhilt.util

import com.app.basics.daggerhilt.network.model.OwnerApi
import com.app.basics.daggerhilt.network.model.QuestionApi
import com.app.basics.daggerhilt.storage.db.Owner
import com.app.basics.daggerhilt.storage.db.Question

fun QuestionApi.toQuestion(): Question {
    this.apply {
        return Question(
            questionId = questionId,
            tags = tags,
            owner = owner.toOwner(),
            isAnswered = isAnswered,
            viewCount = viewCount,
            answerCount = answerCount,
            score = score,
            lastActivityDate = lastActivityDate,
            creationDate = creationDate,
            link = link,
            title = title,
        )
    }
}

fun OwnerApi.toOwner(): Owner {
    this.apply {
        return Owner(
            reputation = reputation,
            userId = userId,
            userType = userType,
            profileImage = profileImage,
            displayName = displayName,
            link = link,
        )
    }
}

fun List<QuestionApi>.toQuestionsList(): List<Question> {
    return this.map {
        it.toQuestion()
    }
}