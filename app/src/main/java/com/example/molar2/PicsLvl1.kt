package com.example.molar2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.draw.clip



@Composable
fun PicsLvl1(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        PicsLvl1Content(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun PicsLvl1Content(navController: NavController, modifier: Modifier = Modifier) {
    val maxSlots = 6
    val answerState = remember { mutableStateListOf("", "", "", "", "", "") }

    val letters = listOf("Z", "Z", "M", "M", "T", "I", "L", "E", "R", "V", "S", "I")

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0D232E))
            .padding(16.dp)
    ) {
        TopNavigationBar(navController = navController)

        Spacer(modifier = Modifier.height(8.dp))

        Text("Level 1", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Text("4pics, 1word", color = Color.LightGray, fontSize = 14.sp)

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = 0.5f,
            modifier = Modifier.fillMaxWidth().height(8.dp),
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

            ImageGrid()

            Spacer(modifier = Modifier.height(24.dp))

            // Answer Slots
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                answerState.forEachIndexed { index, letter ->
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .background(Color.White, shape = RoundedCornerShape(8.dp))
                            .clickable {
                                // 👈 Clear this slot when tapped
                                if (answerState[index].isNotEmpty()) {
                                    answerState[index] = ""
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = letter,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Letter Grid
            LazyVerticalGrid(
                columns = GridCells.Fixed(6),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(letters) { letter ->
                    Box(
                        modifier = Modifier
                            .size(50.dp)
                            .background(Color(0xFF1D546A), shape = RoundedCornerShape(12.dp))
                            .clickable {
                                // Find first empty slot and fill it
                                val index = answerState.indexOfFirst { it.isEmpty() }
                                if (index != -1) {
                                    answerState[index] = letter
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = letter,
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp
                        )
                    }
                }
            }


            // Submit Button
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 0.dp) // this will move it up relative to its position
                    .padding(bottom = 4.dp), // optional: adds breathing room from bottom
                contentAlignment = Alignment.Center
            ) {
                Button(
                    onClick = { /* Handle Submit */ },
                    modifier = Modifier
                        .width(250.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1D546A))
                ) {
                    Text(
                        text = "Next",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }

        }
    }
}


@Composable
fun ImageGrid() {
    val imageList = if (LocalInspectionMode.current) {
        // Show placeholder images in preview
        List(4) { android.R.drawable.ic_menu_gallery }
    } else {
        listOf(
            R.drawable.silv1,
            R.drawable.silv2,
            R.drawable.silv3,
            R.drawable.silv4
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF113543), Color(0xFF1D546A))
                ),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(imageList) { imageRes ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(Color.DarkGray, RoundedCornerShape(12.dp))
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "Quiz Image",
                        contentScale = ContentScale.Crop, // 👈 key part to fill container
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PicsLvl1Preview() {
    // Preview-safe with dummy nav controller
    MaterialTheme {
        PicsLvl1Content(navController = rememberNavController())
    }
}
