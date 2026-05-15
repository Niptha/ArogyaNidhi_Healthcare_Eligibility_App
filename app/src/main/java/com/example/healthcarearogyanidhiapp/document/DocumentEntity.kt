package com.example.healthcarearogyanidhiapp.document

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "documents")
data class DocumentEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val documentName: String,

    val documentType: String
)