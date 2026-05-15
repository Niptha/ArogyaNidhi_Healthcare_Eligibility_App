package com.example.healthcarearogyanidhiapp.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.healthcarearogyanidhiapp.SchemeEntity

class EligibilityViewModel : ViewModel() {

    var showResultScreen = mutableStateOf(false)
        private set

    fun showResults() {
        showResultScreen.value = true
    }

    fun getEligibleSchemes(
        income: String,
        bplStatus: String,
        schemes: List<SchemeEntity>
    ): List<SchemeEntity> {

        val annualIncome = income.toIntOrNull() ?: 0

        return schemes.filter {

            if (bplStatus == "Yes") {

                annualIncome <= 300000

            } else {

                annualIncome <= 200000
            }
        }
    }
}