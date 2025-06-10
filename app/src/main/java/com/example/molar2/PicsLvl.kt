package com.example.molar2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun PicsLvl(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        PicsLvlContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun PicsLvlContent(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TopNavigationBar(navController = navController)
        Spacer(modifier = Modifier.height(8.dp))

        // Header card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF113543),
                            Color(0xFF1D546A)
                        )
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column {
                Text(
                    text = "4 PICS, 1 WORD",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp,
                    lineHeight = 14.sp
                )
                Text(
                    text = "Match the corresponding element",
                    color = Color.LightGray,
                    fontSize = 12.sp,
                    lineHeight = 12.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Level cards with navigation
        PicsLvlCard(
            title = "Level 1: Basic",
            subtitle = "All four images clearly hint at a common metal.",
            progress = 0.1f,
            color = Color(0xFFFFA726),
            onClick = { navController.navigate("picslvl1") }
        )

        PicsLvlCard(
            title = "Level 2: Intermediate",
            subtitle = "Images relate to the metal’s uses, properties, or appearance.",
            progress = 0.1f,
            color = Color(0xFF2F98C7),
            onClick = { /* navController.navigate("picslvl2") */ }
        )

        PicsLvlCard(
            title = "Level 3: Advanced",
            subtitle = "Challenging and abstract! Images may symbolize industrial uses, scientific applications, or rare forms of the metal.",
            progress = 0.1f,
            color = Color(0xFF90A4AE),
            onClick = { /* navController.navigate("picslvl3") */ }
        )

        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun PicsLvlCard(
    title: String,
    subtitle: String,
    progress: Float,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFF113543), shape = RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = color, fontSize = 14.sp, fontWeight = FontWeight.Bold, lineHeight = 16.sp)
                Text(subtitle, color = Color.LightGray, fontSize = 12.sp, lineHeight = 13.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PicsLvlPreview() {
    PicsLvl(navController = rememberNavController())
}
