package com.app.basics.daggerhilt.storage.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface QuestionDao {

    @Query("SELECT * from question")
    suspend fun getAllQuestions(): List<Question>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveQuestionList(questions: List<Question>)
}