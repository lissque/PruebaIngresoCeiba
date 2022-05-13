package com.pruebadeingreso.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pruebadeingreso.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM User")
    suspend fun getAllUsers():List<User>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUsers(user: List<User>)
}