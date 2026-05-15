package com.example.healthcarearogyanidhiapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthcarearogyanidhiapp.viewmodel.EligibilityViewModel

@Composable
fun ResultScreen(
    income: String,
    bplStatus: String,
    eligibilityViewModel: EligibilityViewModel = viewModel()
) {

    val schemes = SchemeRepository.schemes

    val filteredSchemes = eligibilityViewModel.getEligibleSchemes(
        income = income,
        bplStatus = bplStatus,
        schemes = schemes
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        item {

            Text(
                text = "Eligible Healthcare Schemes",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF00695C)
            )

            Spacer(modifier = Modifier.height(20.dp))
        }

        if (filteredSchemes.isEmpty()) {

            item {

                Card(
                    modifier = Modifier.fillMaxWidth(),

                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFFFEBEE)
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Text(
                            text = "No Eligible Schemes Found",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Red
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text =
                                "Based on the entered income and BPL status, there are currently no healthcare schemes available.",
                            color = Color.DarkGray
                        )
                    }
                }
            }
        }

        items(filteredSchemes) { scheme ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE0F7FA)
                ),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 6.dp
                )
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Row {

                        Icon(
                            imageVector = Icons.Default.Verified,
                            contentDescription = null,
                            tint = Color(0xFF00796B)
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            text = scheme.name,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF004D40)
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = scheme.description,
                        fontSize = 15.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Eligibility: ${scheme.eligibility}",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Benefits: ${scheme.benefits}",
                        fontWeight = FontWeight.SemiBold
                    )

                    Spacer(modifier = Modifier.height(6.dp))

                    Text(
                        text = "Required Documents:",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF00695C)
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = scheme.documents
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}