package com.app.basics.daggerhilt.storage.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@TypeConverters(QuestionsConverter::class)
@Database(entities = [Question::class], version = 1)
abstract class StackOverflowDb : RoomDatabase() {
    abstract fun questionDao(): QuestionDao
}