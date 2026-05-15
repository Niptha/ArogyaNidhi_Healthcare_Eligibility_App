package com.example.healthcarearogyanidhiapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface SchemeDao {

    @Insert
    suspend fun insertScheme(
        scheme: SchemeEntity
    )

    @Query("SELECT * FROM schemes")
    suspend fun getAllSchemes(): List<SchemeEntity>
}