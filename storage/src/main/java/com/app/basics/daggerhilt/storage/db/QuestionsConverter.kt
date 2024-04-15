package com.app.basics.daggerhilt.storage.db

import androidx.room.TypeConverter
import com.google.gson.Gson

class QuestionsConverter {

    @TypeConverter
    fun convertListOfTagsToString(tags: List<String>): String {
        return Gson().toJson(tags)
    }

    @TypeConverter
    fun convertStringToListOfTags(tags: String): List<String> {
        return Gson().fromJson(tags, Array<String>::class.java).toList()
    }

    @TypeConverter
    fun convertOwnerToString(owner: Owner): String {
        return Gson().toJson(owner)
    }

    @TypeConverter
    fun convertStringToOwner(owner: String): Owner {
        return Gson().fromJson(owner, Owner::class.java)
    }
}