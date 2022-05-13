package com.pruebadeingreso.entities

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Company")
data class Company (

    @SerializedName("name"        )
    var name        : String,
    @SerializedName("catchPhrase" )
    var catchPhrase : String,
    @SerializedName("bs"          )
    var bs          : String

)