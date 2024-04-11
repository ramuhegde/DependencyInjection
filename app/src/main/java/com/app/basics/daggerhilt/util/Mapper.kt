package com.app.basics.daggerhilt.util

import com.app.basics.daggerhilt.network.model.OwnerApi
import com.app.basics.daggerhilt.network.model.QuestionApi
import com.app.basics.daggerhilt.storage.db.Owner
import com.app.basics.daggerhilt.storage.db.Question

fun QuestionApi.toQuestions(): Question {
    this.apply {
        return Question(
            questionId = questionId,
            tags = tags,
            isAnswered = isAnswered,
            owner = owner.toOwner(),
            answerCount = answerCount,
            creationDate = creationDate,
            lastActivityDate = lastActivityDate,
            link = link,
            score = score,
            title = title,
            viewCount = viewCount
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
            link = link
        )
    }
}

fun List<QuestionApi>.toQuestionsList(): List<Question> {
    return this.map {
        it.toQuestions()
    }
}