package com.example.healthcarearogyanidhiapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.healthcarearogyanidhiapp.SchemeEntity
import com.example.healthcarearogyanidhiapp.SchemeRepository

class GovernmentSchemesViewModel : ViewModel() {

    val schemes: List<SchemeEntity> =
        SchemeRepository.schemes
}