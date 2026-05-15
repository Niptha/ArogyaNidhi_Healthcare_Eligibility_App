package com.example.healthcarearogyanidhiapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schemes")
data class SchemeEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val description: String,

    val eligibility: String,

    val benefits: String,

    val documents: String
)