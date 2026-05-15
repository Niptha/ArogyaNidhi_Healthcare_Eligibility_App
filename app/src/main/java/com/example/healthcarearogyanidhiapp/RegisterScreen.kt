package com.example.healthcarearogyanidhiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RegisterScreen(

    onRegisterSuccess: (String, String) -> Unit,

    onBackClick: () -> Unit
) {

    var name by remember {
        mutableStateOf("")
    }

    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    var confirmPassword by remember {
        mutableStateOf("")
    }

    var errorMessage by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE6FFFA),
                        Color.White
                    )
                )
            )
            .padding(24.dp),

        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Create Account",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F766E)
        )

        Spacer(modifier = Modifier.height(40.dp))

        /*
            FULL NAME
        */

        OutlinedTextField(
            value = name,

            onValueChange = {
                name = it
            },

            label = {
                Text("Full Name")
            },

            shape = RoundedCornerShape(16.dp),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        /*
            EMAIL
        */

        OutlinedTextField(
            value = email,

            onValueChange = {
                email = it
            },

            label = {
                Text("Email Address")
            },

            shape = RoundedCornerShape(16.dp),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        /*
            PASSWORD
        */

        OutlinedTextField(
            value = password,

            onValueChange = {
                password = it
            },

            label = {
                Text("Password")
            },

            visualTransformation =
                PasswordVisualTransformation(),

            shape = RoundedCornerShape(16.dp),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        /*
            CONFIRM PASSWORD
        */

        OutlinedTextField(
            value = confirmPassword,

            onValueChange = {
                confirmPassword = it
            },

            label = {
                Text("Confirm Password")
            },

            visualTransformation =
                PasswordVisualTransformation(),

            shape = RoundedCornerShape(16.dp),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        /*
            ERROR MESSAGE
        */

        if (errorMessage.isNotEmpty()) {

            Text(
                text = errorMessage,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        /*
            REGISTER BUTTON
        */

        Button(

            onClick = {

                when {

                    name.isBlank() ||
                            email.isBlank() ||
                            password.isBlank() ||
                            confirmPassword.isBlank() -> {

                        errorMessage =
                            "Please fill all fields"
                    }

                    !email.contains("@gmail.com") -> {

                        errorMessage =
                            "Enter valid Gmail ID"
                    }

                    password.length < 4 -> {

                        errorMessage =
                            "Password must be at least 4 characters"
                    }

                    password != confirmPassword -> {

                        errorMessage =
                            "Passwords do not match"
                    }

                    else -> {

                        errorMessage = ""

                        /*
                            SEND DATA TO LOGIN SCREEN
                        */

                        onRegisterSuccess(
                            email,
                            password
                        )
                    }
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(18.dp),

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF0F766E)
            )
        ) {

            Text(
                text = "Register",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        /*
            BACK TO LOGIN
        */

        TextButton(
            onClick = {
                onBackClick()
            }
        ) {

            Text(
                text = "Back to Login"
            )
        }
    }
}