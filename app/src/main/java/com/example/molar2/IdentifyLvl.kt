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


@Composable
fun IdentifyLvl(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        IdentifyLvl(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun IdentifyLvl(navController: NavController, modifier: Modifier = Modifier) {
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
                text = "Identify Compound",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                lineHeight = 14.sp // 👈 match line height to font size
            )
            Text(
                text = "Identify the following compounds",
                color = Color.LightGray,
                fontSize = 12.sp,
                lineHeight = 12.sp // 👈 match again
            )
        }

        }

        Spacer(modifier = Modifier.height(24.dp))

        // Assessment cards
        IdentifyLvl(
            title = "Level 1: Basic",
            subtitle = "Identify common elements like metals you often hear about.",
            progress = 0.1f,
            color = Color(0xFFFFA726),
            onClick = { navController.navigate("identify_lvl_1") } // ✅ Navigate on tap
        )
        IdentifyLvl(
            title = "Level 2: Intermediate",
            subtitle = "Less common elements. Think beyond the basics—look for clues in the image!",
            progress = 0.1f,
            color = Color(0xFF2F98C7),
            onClick = { /* Navigate to level 2 if needed */ }
        )
        IdentifyLvl(
            title = "Level 3: Advanced",
            subtitle = "Rare or tricky elements. Use your knowledge of symbols, uses, or appearance.",
            progress = 0.1f,
            color = Color(0xFF90A4AE),
            onClick = { /* Navigate to level 3 if needed */ }
        )


        Spacer(modifier = Modifier.weight(1f)) // Push bottom nav to bottom
    }
}

@Composable
fun IdentifyLvl(title: String, subtitle: String, progress: Float, color: Color, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(Color(0xFF113543), shape = RoundedCornerShape(20.dp))
            .clickable(onClick = onClick) // ✅ Make the card clickable
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
fun IdentifyLvl() {
    IdentifyLvl(navController = rememberNavController())
}
