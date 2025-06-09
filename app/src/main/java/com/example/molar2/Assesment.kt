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
import androidx.compose.foundation.clickable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.compose.foundation.clickable



@Composable
fun Assessment(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        Assessment(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Assessment(navController: NavController, modifier: Modifier = Modifier) {
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
                text = "STUDENT ASSESSMENT",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 14.sp // 👈 match line height to font size
            )
            Text(
                text = "Check Your Learnings",
                color = Color.LightGray,
                fontSize = 12.sp,
                lineHeight = 12.sp // 👈 match again
            )
        }

        }

        Spacer(modifier = Modifier.height(24.dp))

        // Assessment cards
        AssessmentCard(
            title = "Match Element",
            subtitle = "Match the corresponding element",
            progress = 0.1f,
            color = Color(0xFFFFA726),
            onClick = { navController.navigate("matching_level") } // 👈 Navigate on click
        )

        AssessmentCard("Identify Compound", "Identify the following compounds", 0.1f, Color(0xFF2F98C7), onClick = { navController.navigate("identify_level") } )
        AssessmentCard("Complete Combination", "Complete the compound bond", 0.1f, Color(0xFF90A4AE), onClick = { navController.navigate("combination_level") })
        AssessmentCard("4 pics, 1 word", "Guess the 1 correct word", 0.1f, Color(0xFFFFA726), onClick = { navController.navigate("pics_level") })

        Spacer(modifier = Modifier.weight(1f)) // Push bottom nav to bottom
    }
}

@Composable
fun AssessmentCard(
    title: String,
    subtitle: String,
    progress: Float,
    color: Color,
    onClick: () -> Unit = {} // ✅ Add this line
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFF113543), shape = RoundedCornerShape(20.dp))
            .clickable { onClick() }  // ✅ Now this works
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = color, fontSize = 14.sp, fontWeight = FontWeight.Bold,  lineHeight = 16.sp)
                Text(subtitle, color = Color.LightGray, fontSize = 12.sp, lineHeight = 13.sp)
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AssessmentPreview() {
    Assessment(navController = rememberNavController())
}
