package com.example.molar2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.Brush

@Composable
fun MatchingLvl(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        MatchingLvl(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MatchingLvl(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // ✅ TopNavigationBar at the very top — exactly like Setting_ActContent
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
        )
        {Column {
            Text(
                text = "Match Element",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 14.sp // 👈 match line height to font size
            )
            Text(
                text = "Match the corresponding element",
                color = Color.LightGray,
                fontSize = 12.sp,
                lineHeight = 12.sp // 👈 match again
            )
        }

        }

        Spacer(modifier = Modifier.height(24.dp))

        // Assessment cards
        MatchingLvl("Level 1: Basic", "Match well-known elements to their illustration. Straightforward and familiar!Match well-known elements to their illustration. Straightforward and familiar!", 0.1f,  Color(0xFFFFA726))
        MatchingLvl("Level 2: Intermediate", "Match less common elements.", 0.1f, Color(0xFF2F98C7))
        MatchingLvl("Level 3: Advanced", "Match well-known compound elements to their illustration. ", 0.1f, Color(0xFF90A4AE))


        Spacer(modifier = Modifier.weight(1f)) // Push bottom nav to bottom
    }
}

@Composable
fun MatchingLvl(title: String, subtitle: String, progress: Float, color: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFF113543), shape = RoundedCornerShape(20.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = color, fontSize = 14.sp, fontWeight = FontWeight.Bold,  lineHeight = 16.sp)
                Text(subtitle, color = Color.LightGray, fontSize = 12.sp,lineHeight = 13.sp )

            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MatchingLvl() {
    MatchingLvl(navController = rememberNavController())
}
