package com.example.healthcarearogyanidhiapp

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DocumentsScreen() {

    val context = LocalContext.current

    var documents by remember {
        mutableStateOf(listOf<DocumentEntity>())
    }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->

        uri?.let {

            val fileName =
                "Uploaded Document"

            val newDocument = DocumentEntity(
                documentName = fileName,
                documentType = "PDF/Image",
                filePath = uri.toString()
            )

            documents =
                documents + newDocument
        }
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
            .padding(20.dp)
    ) {

        Spacer(modifier = Modifier.height(15.dp))

        Text(
            text = "Required Documents",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF00695C)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Securely store your important healthcare documents.",
            fontSize = 15.sp,
            color = Color.DarkGray
        )

        Spacer(modifier = Modifier.height(25.dp))

        Button(
            onClick = {

                launcher.launch("*/*")
            },

            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF00897B)
            ),

            shape = RoundedCornerShape(18.dp),

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp)
        ) {

            Icon(
                imageVector = Icons.Default.UploadFile,
                contentDescription = null
            )

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "Upload Document",
                fontSize = 18.sp
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        if (documents.isEmpty()) {

            Card(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(22.dp),

                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                )
            ) {

                Column(
                    modifier = Modifier.padding(25.dp)
                ) {

                    Text(
                        text = "No Documents Uploaded",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    Text(
                        text = "Upload Aadhaar, Income Certificate, BPL Card and other healthcare documents.",
                        color = Color.Gray
                    )
                }
            }

        } else {

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {

                items(documents) { document ->

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
                                    imageVector = Icons.Default.Description,
                                    contentDescription = null,
                                    tint = Color(0xFF00897B)
                                )

                                Spacer(modifier = Modifier.width(10.dp))

                                Text(
                                    text = document.documentName,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "Type: ${document.documentType}"
                            )

                            Spacer(modifier = Modifier.height(18.dp))

                            Button(
                                onClick = {

                                    val intent = Intent(
                                        Intent.ACTION_VIEW,
                                        Uri.parse(document.filePath)
                                    )

                                    context.startActivity(intent)
                                },

                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color(0xFF00897B)
                                )
                            ) {

                                Text("Open Document")
                            }
                        }
                    }
                }
            }
        }
    }
}
