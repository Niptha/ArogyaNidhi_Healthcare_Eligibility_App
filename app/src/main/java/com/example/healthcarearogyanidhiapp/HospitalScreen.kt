package com.example.healthcarearogyanidhiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HospitalScreen() {

    var district by remember {
        mutableStateOf("")
    }

    var showHospitalResult by remember {
        mutableStateOf(false)
    }

    if(showHospitalResult) {

        HospitalResultScreen(district)
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF0FDFA))
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(50.dp))

        Text(
            text = "Hospital Locator",
            fontSize = 30.sp,
            color = Color(0xFF0F766E)
        )

        Spacer(modifier = Modifier.height(30.dp))

        OutlinedTextField(
            value = district,

            onValueChange = {
                district = it
            },

            label = {
                Text("Enter District")
            },

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {

                showHospitalResult = true
            },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0F766E)
            ),

            shape = RoundedCornerShape(16.dp),

            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
        ) {

            Text(
                text = "Find Nearest Hospital",
                fontSize = 18.sp
            )
        }
    }
}