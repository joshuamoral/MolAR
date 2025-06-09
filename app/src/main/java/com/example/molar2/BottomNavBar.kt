package com.example.molar2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.molar2.ui.theme.MolAR2Theme

@Composable
fun BottomNavigationBar(navController: NavController) {
    val isInPreview = LocalInspectionMode.current
    val navBackStackEntry by run {
        if (!isInPreview) {
            navController.currentBackStackEntryAsState()
        } else {
            // Return a fixed value for preview context
            derivedStateOf { null }
        }
    }

    val currentRoute = navBackStackEntry?.destination?.route

    val bottomPaddingValue = 13.dp

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = bottomPaddingValue),
                contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .width(340.dp)
                .height(60.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF113543), Color(0xFF113543))
                    ),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            IconButton(onClick = {
                if (!isInPreview) navController.navigate("home")
            }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home",
                    tint = if (currentRoute == "home") Color(0xFFFFA726) else Color.White
                )
            }

            IconButton(onClick = {
                if (!isInPreview) navController.navigate("metals")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.e_symbol),
                    contentDescription = "Metals",
                    modifier = Modifier.size(23.dp),
                    tint = if (currentRoute == "metals") Color(0xFFFFA726) else Color.White
                )
            }

            IconButton(onClick = {
                if (!isInPreview) navController.navigate("assessment")
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.robotbook),
                    contentDescription = "Assessment",
                    modifier = Modifier.size(23.dp),
                    tint = if (currentRoute == "assessment" || currentRoute == "matching_level" || currentRoute == "identify_level" || currentRoute == "combination_level" || currentRoute == "pics_level") Color(0xFFFFA726) else Color.White
                )
            }

            IconButton(onClick = {
                if (!isInPreview) navController.navigate("settings")
            }) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings",
                    tint = if (currentRoute == "settings" || currentRoute == "profile") Color(0xFFFFA726) else Color.White
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    MolAR2Theme {
        // Use rememberNavController only for preview context — safe now
        val navController = rememberNavController()
        Box(
            modifier = Modifier
                .background(Color(0xFF0D232E))
                .padding(16.dp)
        ) {
            BottomNavigationBar(navController)
        }
    }
}
