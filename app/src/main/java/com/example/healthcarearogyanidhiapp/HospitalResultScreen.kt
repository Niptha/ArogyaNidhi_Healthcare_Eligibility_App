package com.example.healthcarearogyanidhiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HospitalResultScreen(district: String) {

    val hospitals = when(district.lowercase()) {

        "guntur" -> listOf(
            "Ramesh Hospital",
            "Government General Hospital",
            "Amaravathi Hospital"
        )

        "vijayawada" -> listOf(
            "Andhra Hospital",
            "Aayush Hospital",
            "City Cancer Center"
        )

        else -> listOf(
            "No hospitals found"
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0FDFA))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Nearby Hospitals",
            fontSize = 30.sp,
            color = Color(0xFF0F766E)
        )

        Spacer(modifier = Modifier.height(20.dp))

        hospitals.forEach { hospital ->

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),

                shape = RoundedCornerShape(20.dp),

                elevation = CardDefaults.cardElevation(
                    defaultElevation = 8.dp
                ),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(20.dp)
                ) {

                    Text(
                        text = hospital,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "Scheme Accepted: Ayushman Bharat",
                        fontSize = 16.sp
                    )

                    Text(
                        text = "Contact: 9876543210",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}