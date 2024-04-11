package com.app.basics.daggerhilt.util

import android.content.Context
import android.icu.util.Calendar
import com.app.basics.daggerhilt.R
import com.app.basics.daggerhilt.network.model.OwnerApi
import com.app.basics.daggerhilt.network.model.QuestionApi
import com.app.basics.daggerhilt.storage.db.Owner
import com.app.basics.daggerhilt.storage.db.Question
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

fun QuestionApi.toQuestions(): Question {
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

fun Long.toDate(context: Context): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000

    return try {
        val simpleDateFormat =
            SimpleDateFormat(context.getString(R.string.dd_mm_yyyy), Locale.ENGLISH)
        simpleDateFormat.timeZone = TimeZone.getDefault()
        simpleDateFormat.format(calendar.time)
    } catch (e: Exception) {
        e.printStackTrace()
        ""
    }
}