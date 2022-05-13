package com.pruebadeingreso.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Geo")
data class Geo (

    @SerializedName("lat" ) var lat : String,
    @SerializedName("lng" ) var lng : String

)