package com.example.healthcarearogyanidhiapp

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DashboardScreen(
    userName: String = "User"
) {

    var currentScreen by remember {
        mutableStateOf("dashboard")
    }

    if (currentScreen == "eligibility") {
        EligibilityScreen()
        return
    }

    if (currentScreen == "hospital") {
        HospitalScreen()
        return
    }
    if (currentScreen == "schemes") {
        GovernmentSchemesScreen()
        return
    }
    if (currentScreen == "documents") {
        DocumentsScreen()
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE0F7FA),
                        Color(0xFFF8FFFE),
                        Color.White
                    )
                )
            )
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 24.dp)
    ) {

        Spacer(modifier = Modifier.height(20.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {

                Text(
                    text = "Welcome,",
                    fontSize = 18.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = userName,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00695C)
                )
            }

            Card(
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                modifier = Modifier.shadow(
                    elevation = 6.dp,
                    shape = RoundedCornerShape(16.dp)
                )
            ) {

                IconButton(
                    onClick = { }
                ) {

                    Icon(
                        imageVector = Icons.Default.Settings,
                        contentDescription = null,
                        tint = Color(0xFF00695C)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "Our Services",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF004D40)
        )

        Spacer(modifier = Modifier.height(20.dp))

        DashboardCard(
            title = "Eligibility Checker",
            subtitle = "Check eligible government schemes",
            icon = Icons.Default.CheckCircle,
            color = Color(0xFFE8F5E9),
            onClick = {
                currentScreen = "eligibility"
            }
        )

        DashboardCard(
            title = "Government Schemes",
            subtitle = "Explore available healthcare benefits",
            icon = Icons.Default.Description,
            color = Color(0xFFE3F2FD),
            onClick = {
                currentScreen = "schemes"

            }
        )

        DashboardCard(
            title = "Hospital Finder",
            subtitle = "Locate nearby empanelled hospitals",
            icon = Icons.Default.LocalHospital,
            color = Color(0xFFFFF3E0),
            onClick = {
                currentScreen = "hospital"
            }
        )

        DashboardCard(
            title = "Required Documents",
            subtitle = "View documents needed for schemes",
            icon = Icons.Default.Folder,
            color = Color(0xFFF3E5F5),
            onClick = {
                currentScreen = "documents"

            }
        )

        Spacer(modifier = Modifier.height(28.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 8.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Text(
                    text = "Health Tip 💡",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF00695C)
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Regular health checkups help in early detection and better treatment.",
                    fontSize = 15.sp,
                    color = Color.DarkGray
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        NavigationBar(
            containerColor = Color.White
        ) {

            NavigationBarItem(
                selected = true,
                onClick = { },
                icon = {
                    Icon(Icons.Default.Home, contentDescription = null)
                },
                label = {
                    Text("Home")
                }
            )

            NavigationBarItem(
                selected = false,
                onClick = { },
                icon = {
                    Icon(Icons.Default.Person, contentDescription = null)
                },
                label = {
                    Text("Profile")
                }
            )

            NavigationBarItem(
                selected = false,
                onClick = { },
                icon = {
                    Icon(Icons.Default.Info, contentDescription = null)
                },
                label = {
                    Text("About")
                }
            )
        }

        Spacer(modifier = Modifier.height(20.dp))
    }
}

@Composable
fun DashboardCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    color: Color,
    onClick: () -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .padding(vertical = 10.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(24.dp)
            )
            .clickable {
                onClick()
            },

        shape = RoundedCornerShape(24.dp),

        colors = CardDefaults.cardColors(
            containerColor = color
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),

            verticalAlignment = Alignment.CenterVertically
        ) {

            Card(
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Box(
                    modifier = Modifier
                        .size(60.dp),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = Color(0xFF00695C),
                        modifier = Modifier.size(32.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.width(18.dp))

            Column {

                Text(
                    text = title,
                    fontSize = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1F2937)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = subtitle,
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )
            }
        }
    }
}