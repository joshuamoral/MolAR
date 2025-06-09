package com.example.molar2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight


@Composable
fun IdentifyLvl1(navController: NavController) {
    Scaffold(

        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        IdentifyLvl1(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun IdentifyLvl1(navController: NavController, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0D232E))
            .padding(16.dp)
    ) {
        TopNavigationBar(navController = navController)
        Spacer(modifier = Modifier.height(8.dp))

        // Title Section
        Text(
            text = "Level 1",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Identify Compound",
            color = Color.LightGray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Progress Bar
        LinearProgressIndicator(
            progress = 0.5f,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            color = Color(0xFFFFA500),
            trackColor = Color.Gray
        )



        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))


            Spacer(modifier = Modifier.height(16.dp))

            // Compound Quiz Items
            val answers = remember { mutableStateListOf("", "", "", "", "", "") }

            for (i in 1..6) {
                CompoundItem(
                    number = i,
                    imageRes = when (i) {
                        1 -> R.drawable.img1
                        2 -> R.drawable.img2
                        3 -> R.drawable.img3
                        4 -> R.drawable.img4
                        5 -> R.drawable.img5
                        6 -> R.drawable.img2
                        else -> R.drawable.img1
                    },
                    answer = answers[i - 1],
                    onAnswerChange = { newText -> answers[i - 1] = newText }
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Submit Button with Gradient
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center // ✅ Horizontally center the button
            ) {
                Box(
                    modifier = Modifier
                        .width(250.dp)
                        .height(48.dp)
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color(0xFF113543),
                                    Color(0xFF1D546A)
                                )
                            ),
                            shape = RoundedCornerShape(18.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = {
                            // Handle answers here
                        },
                        modifier = Modifier
                            .fillMaxSize(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                        elevation = ButtonDefaults.buttonElevation(0.dp)
                    ) {
                        Text(
                            text = "Submit",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 16.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(32.dp)) // Bottom padding for scrolling
            }


        }
    }
}

@Composable
fun CompoundItem(number: Int, imageRes: Int, answer: String, onAnswerChange: (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color(0xFF113543),
                        Color(0xFF1D546A)
                    )
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "$number. What is the name of this compound?",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold
        )

        Spacer(modifier = Modifier.height(12.dp))

        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "Compound Image $number",
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Figure 0$number",
            color = Color.LightGray,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = answer,
            onValueChange = onAnswerChange,
            placeholder = { Text("Name the compound") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.Gray,
                cursorColor = Color.White,
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedLabelColor = Color.White,
                unfocusedLabelColor = Color.Gray
            )
        )
    }
}




@Preview(showBackground = true)
@Composable
fun IdentifyLvl1Preview() {
    IdentifyLvl1(navController = rememberNavController())
}
