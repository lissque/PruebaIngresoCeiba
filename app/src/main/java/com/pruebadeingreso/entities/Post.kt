package com.pruebadeingreso.entities

import com.google.gson.annotations.SerializedName

data class Post (
    @SerializedName("userId"  ) var userId  : Int,
    @SerializedName("id"   ) var id  : Int,
    @SerializedName("title"    ) var tittle    : String,
    @SerializedName("body" ) var body : String,
)