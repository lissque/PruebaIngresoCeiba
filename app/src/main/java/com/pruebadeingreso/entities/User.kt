package com.pruebadeingreso.entities
import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "User")
data class User (

    @PrimaryKey @SerializedName("id"       ) var id       : Int,
    @ColumnInfo @SerializedName("name"     ) var name     : String,
    @ColumnInfo @SerializedName("username" ) var username : String,
    @ColumnInfo @SerializedName("email"    ) var email    : String,
    @Embedded @SerializedName("address"  ) var address  : Address,
    @ColumnInfo @SerializedName("phone"    ) var phone    : String,
    @ColumnInfo @SerializedName("website"  ) var website  : String,
    @Embedded @SerializedName("company"  ) var company  : Company

)