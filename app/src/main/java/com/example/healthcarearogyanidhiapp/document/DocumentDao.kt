package com.example.healthcarearogyanidhiapp.document

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DocumentDao {

    @Insert
    suspend fun insertDocument(document: DocumentEntity)

    @Query("SELECT * FROM documents")
    suspend fun getAllDocuments(): List<DocumentEntity>
}