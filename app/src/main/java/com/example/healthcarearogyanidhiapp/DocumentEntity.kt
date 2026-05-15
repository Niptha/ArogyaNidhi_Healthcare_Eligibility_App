package com.example.healthcarearogyanidhiapp

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "documents")
data class DocumentEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val documentName: String,

    val documentType: String,

    val filePath: String
)