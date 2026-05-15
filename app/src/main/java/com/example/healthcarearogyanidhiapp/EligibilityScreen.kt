package com.example.healthcarearogyanidhiapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.healthcarearogyanidhiapp.viewmodel.EligibilityViewModel

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun EligibilityScreen() {
    val eligibilityViewModel: EligibilityViewModel = viewModel()

    var selectedState by remember { mutableStateOf("") }
    var income by remember { mutableStateOf("") }
    var selectedBPL by remember { mutableStateOf("") }
    var selectedOccupation by remember { mutableStateOf("") }
    var familySize by remember { mutableStateOf("") }

    var expandedState by remember { mutableStateOf(false) }
    var expandedBPL by remember { mutableStateOf(false) }
    var expandedOccupation by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }

    var currentStep by remember { mutableStateOf(1) }

    var showResultScreen by remember {
        mutableStateOf(false)
    }

    val states = listOf(
        "Karnataka",
        "Kerala",
        "Tamil Nadu",
        "Andhra Pradesh",
        "Maharashtra",
        "Telangana",
        "Other State"
    )

    val bplOptions = listOf(
        "Yes",
        "No"
    )

    val occupations = listOf(
        "Farmer",
        "Daily Wage Worker",
        "Student",
        "Private Employee",
        "Government Employee",
        "Self Employed",
        "Other"
    )

    if (showResultScreen) {

        ResultScreen(
            income = income,
            bplStatus = selectedBPL
        )

        return
    }

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
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Eligibility Checker",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00695C)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Complete the 5-step healthcare eligibility assessment.",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        LinearProgressIndicator(
            progress = currentStep / 5f,
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp),

            color = Color(0xFF00897B),

            trackColor = Color(0xFFE0E0E0)
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Step $currentStep of 5",
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(30.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            ),

            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {

            Column(
                modifier = Modifier.padding(24.dp)
            ) {

                AnimatedVisibility(
                    visible = currentStep == 1,
                    enter = fadeIn() + slideInVertically()
                ) {

                    Column {

                        Text(
                            text = "Which state are you from?",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        ExposedDropdownMenuBox(
                            expanded = expandedState,
                            onExpandedChange = {
                                expandedState = !expandedState
                            }
                        ) {

                            OutlinedTextField(
                                value = selectedState,
                                onValueChange = {},
                                readOnly = true,
                                label = {
                                    Text("Select State")
                                },

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),

                                shape = RoundedCornerShape(16.dp)
                            )

                            ExposedDropdownMenu(
                                expanded = expandedState,
                                onDismissRequest = {
                                    expandedState = false
                                }
                            ) {

                                states.forEach {

                                    DropdownMenuItem(
                                        text = {
                                            Text(it)
                                        },

                                        onClick = {

                                            selectedState = it
                                            expandedState = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                AnimatedVisibility(
                    visible = currentStep == 2,
                    enter = fadeIn() + slideInVertically()
                ) {

                    Column {

                        Text(
                            text = "Enter your annual income",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            value = income,

                            onValueChange = {
                                income = it
                            },

                            label = {
                                Text("Annual Income")
                            },

                            modifier = Modifier.fillMaxWidth(),

                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                }

                AnimatedVisibility(
                    visible = currentStep == 3,
                    enter = fadeIn() + slideInVertically()
                ) {

                    Column {

                        Text(
                            text = "Do you have BPL status?",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        ExposedDropdownMenuBox(
                            expanded = expandedBPL,
                            onExpandedChange = {
                                expandedBPL = !expandedBPL
                            }
                        ) {

                            OutlinedTextField(
                                value = selectedBPL,
                                onValueChange = {},
                                readOnly = true,
                                label = {
                                    Text("BPL Status")
                                },

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),

                                shape = RoundedCornerShape(16.dp)
                            )

                            ExposedDropdownMenu(
                                expanded = expandedBPL,
                                onDismissRequest = {
                                    expandedBPL = false
                                }
                            ) {

                                bplOptions.forEach {

                                    DropdownMenuItem(
                                        text = {
                                            Text(it)
                                        },

                                        onClick = {

                                            selectedBPL = it
                                            expandedBPL = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                AnimatedVisibility(
                    visible = currentStep == 4,
                    enter = fadeIn() + slideInVertically()
                ) {

                    Column {

                        Text(
                            text = "Select your occupation",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        ExposedDropdownMenuBox(
                            expanded = expandedOccupation,
                            onExpandedChange = {
                                expandedOccupation = !expandedOccupation
                            }
                        ) {

                            OutlinedTextField(
                                value = selectedOccupation,
                                onValueChange = {},
                                readOnly = true,
                                label = {
                                    Text("Occupation")
                                },

                                modifier = Modifier
                                    .fillMaxWidth()
                                    .menuAnchor(),

                                shape = RoundedCornerShape(16.dp)
                            )

                            ExposedDropdownMenu(
                                expanded = expandedOccupation,
                                onDismissRequest = {
                                    expandedOccupation = false
                                }
                            ) {

                                occupations.forEach {

                                    DropdownMenuItem(
                                        text = {
                                            Text(it)
                                        },

                                        onClick = {

                                            selectedOccupation = it
                                            expandedOccupation = false
                                        }
                                    )
                                }
                            }
                        }
                    }
                }

                AnimatedVisibility(
                    visible = currentStep == 5,
                    enter = fadeIn() + slideInVertically()
                ) {

                    Column {

                        Text(
                            text = "How many family members?",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold
                        )

                        Spacer(modifier = Modifier.height(20.dp))

                        OutlinedTextField(
                            value = familySize,

                            onValueChange = {
                                familySize = it
                            },

                            label = {
                                Text("Family Size")
                            },

                            modifier = Modifier.fillMaxWidth(),

                            shape = RoundedCornerShape(16.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(25.dp))

                if (errorMessage.isNotEmpty()) {

                    Text(
                        text = errorMessage,
                        color = Color.Red
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }

                Button(
                    onClick = {

                        when (currentStep) {

                            1 -> {

                                if (selectedState.isEmpty()) {

                                    errorMessage =
                                        "Please select your state"

                                } else {

                                    errorMessage = ""
                                    currentStep++
                                }
                            }

                            2 -> {

                                if (income.isEmpty()) {

                                    errorMessage =
                                        "Please enter income"

                                } else {

                                    errorMessage = ""
                                    currentStep++
                                }
                            }

                            3 -> {

                                if (selectedBPL.isEmpty()) {

                                    errorMessage =
                                        "Please select BPL status"

                                } else {

                                    errorMessage = ""
                                    currentStep++
                                }
                            }

                            4 -> {

                                if (selectedOccupation.isEmpty()) {

                                    errorMessage =
                                        "Please select occupation"

                                } else {

                                    errorMessage = ""
                                    currentStep++
                                }
                            }

                            5 -> {

                                if (familySize.isEmpty()) {

                                    errorMessage =
                                        "Please enter family size"

                                } else {

                                    errorMessage = ""

                                    showResultScreen = true
                                }
                            }
                        }
                    },

                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),

                    shape = RoundedCornerShape(18.dp),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00897B)
                    )
                ) {

                    Icon(
                        imageVector = if (currentStep == 5)
                            Icons.Default.CheckCircle
                        else
                            Icons.Default.ArrowForward,

                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text =
                            if (currentStep == 5)
                                "Check Eligibility"
                            else
                                "Next",

                        fontSize = 18.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}