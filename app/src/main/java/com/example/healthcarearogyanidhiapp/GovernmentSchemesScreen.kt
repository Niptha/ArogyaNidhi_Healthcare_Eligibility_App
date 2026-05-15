package com.example.healthcarearogyanidhiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthcarearogyanidhiapp.viewmodel.GovernmentSchemesViewModel

@Composable
fun GovernmentSchemesScreen(
    governmentSchemesViewModel: GovernmentSchemesViewModel = viewModel()
) {

    val schemes = governmentSchemesViewModel.schemes

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE0F7FA),
                        Color.White
                    )
                )
            )
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Government Health Schemes",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00695C)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Explore healthcare schemes available for citizens.",
            fontSize = 15.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(25.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {

            items(schemes) { scheme ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),

                    shape = RoundedCornerShape(24.dp),

                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),

                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 8.dp
                    )
                ) {

                    Column(
                        modifier = Modifier.padding(20.dp)
                    ) {

                        Row {

                            Icon(
                                imageVector = Icons.Default.HealthAndSafety,
                                contentDescription = null,
                                tint = Color(0xFF00897B)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Text(
                                text = scheme.name,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF004D40)
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        Text(
                            text = scheme.description,
                            fontSize = 15.sp,
                            color = Color.DarkGray
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        Text(
                            text = "Eligibility: ${scheme.eligibility}",
                            fontWeight = FontWeight.SemiBold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Benefits: ${scheme.benefits}",
                            color = Color(0xFF2E7D32),
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "Required Documents:",
                            fontWeight = FontWeight.Bold
                        )

                        Text(
                            text = scheme.documents,
                            color = Color.Gray
                        )
                    }
                }
            }
        }
    }
}