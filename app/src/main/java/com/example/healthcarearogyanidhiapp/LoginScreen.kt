package com.example.healthcarearogyanidhiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
fun LoginScreen() {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    var errorMessage by remember {
        mutableStateOf("")
    }

    var isLoggedIn by remember {
        mutableStateOf(false)
    }

    var showRegisterScreen by remember {
        mutableStateOf(false)
    }

    /*
        REGISTERED USER DATA
        (Temporary Local Storage)
    */

    var registeredEmail by remember {
        mutableStateOf("")
    }

    var registeredPassword by remember {
        mutableStateOf("")
    }

    /*
        OPEN REGISTER SCREEN
    */

    if (showRegisterScreen) {

        RegisterScreen(

            onRegisterSuccess = { emailValue, passwordValue ->

                registeredEmail = emailValue
                registeredPassword = passwordValue

                showRegisterScreen = false

                errorMessage =
                    "Registration Successful. Please Login"
            },

            onBackClick = {

                showRegisterScreen = false
            }
        )

        return
    }

    /*
        AFTER LOGIN
    */

    if (isLoggedIn) {

        DashboardScreen(
            userName = email.substringBefore("@")
        )

        return
    }

    /*
        LOGIN UI
    */

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

        Spacer(modifier = Modifier.height(70.dp))

        Text(
            text = "🏥",
            fontSize = 60.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "ArogyaNidhi",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF0F766E)
        )

        Text(
            text = "Healthcare Eligibility Checker",
            fontSize = 15.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(50.dp))

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

            singleLine = true,

            shape = RoundedCornerShape(16.dp),

            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(20.dp))

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

            singleLine = true,

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
                color = Color.Red,
                fontSize = 14.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        /*
            LOGIN BUTTON
        */

        Button(

            onClick = {

                when {

                    email.isBlank() ||
                            password.isBlank() -> {

                        errorMessage =
                            "Please fill all fields"
                    }

                    !email.contains("@gmail.com") -> {

                        errorMessage =
                            "Enter valid Gmail ID"
                    }

                    registeredEmail.isEmpty() -> {

                        errorMessage =
                            "Account not registered. Please register first."
                    }

                    email != registeredEmail ||
                            password != registeredPassword -> {

                        errorMessage =
                            "Invalid Email or Password"
                    }

                    else -> {

                        errorMessage = ""
                        isLoggedIn = true
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
                text = "Login",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        /*
            REGISTER OPTION
        */

        Row {

            Text(
                text = "Don't have an account? "
            )

            Text(
                text = "Register",
                color = Color(0xFF0F766E),
                fontWeight = FontWeight.Bold,

                modifier = Modifier.clickable {

                    showRegisterScreen = true
                }
            )
        }
    }
}