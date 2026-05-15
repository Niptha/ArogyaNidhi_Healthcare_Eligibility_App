package com.example.healthcarearogyanidhiapp.viewmodel

import android.app.Application
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthcarearogyanidhiapp.AppDatabase
import com.example.healthcarearogyanidhiapp.DocumentRepository
import com.example.healthcarearogyanidhiapp.document.DocumentEntity
import kotlinx.coroutines.launch

class DocumentViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository: DocumentRepository

    var documentList =
        mutableStateListOf<DocumentEntity>()

    init {

        val documentDao =
            AppDatabase
                .getDatabase(application)
                .documentDao()

        repository = DocumentRepository(documentDao)

        loadDocuments()
    }

    fun addDocument(
        documentName: String,
        documentType: String
    ) {

        viewModelScope.launch {

            repository.insertDocument(
                DocumentEntity(
                    documentName = documentName,
                    documentType = documentType
                )
            )

            loadDocuments()
        }
    }

    private fun loadDocuments() {

        viewModelScope.launch {

            documentList.clear()

            documentList.addAll(
                repository.getAllDocuments()
            )
        }
    }
}