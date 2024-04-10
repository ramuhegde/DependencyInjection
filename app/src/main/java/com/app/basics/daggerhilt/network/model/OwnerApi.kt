package com.app.basics.daggerhilt.network.model

import com.google.gson.annotations.SerializedName

data class OwnerApi(
    val reputation: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user_type")
    val userType: String,
    @SerializedName("profile_image")
    val profileImage: String,
    @SerializedName("display_name")
    val displayName: String,
    val link: String
)
