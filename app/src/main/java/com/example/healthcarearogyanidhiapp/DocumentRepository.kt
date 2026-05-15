package com.example.healthcarearogyanidhiapp

import com.example.healthcarearogyanidhiapp.document.DocumentDao
import com.example.healthcarearogyanidhiapp.document.DocumentEntity

class DocumentRepository(
    private val documentDao: DocumentDao
) {

    suspend fun insertDocument(
        document: DocumentEntity
    ) {
        documentDao.insertDocument(document)
    }

    suspend fun getAllDocuments(): List<DocumentEntity> {

        return documentDao.getAllDocuments()
    }
}