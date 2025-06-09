package com.example.molar2


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopNavigationBar(navController: NavController, isNotificationScreen: Boolean = false) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val isNotificationScreen = currentRoute == "notification"
    val notificationIconColor = if (isNotificationScreen) Color(0xFFFFA726) else Color.White

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
                .padding(top = 22.dp)
                .clickable {
                    if (navController.previousBackStackEntry != null) {
                        navController.popBackStack()
                    }
                }
        )

        Image(
            painter = painterResource(id = R.drawable.molar_logo),
            contentDescription = "MolAR Logo",
            modifier = Modifier
                .height(40.dp)
        )

        Box(
            modifier = Modifier
                .padding(top = 22.dp)
                .size(45.dp)
                .clickable {
                    navController.navigate("notification")
                },
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = notificationIconColor,
                modifier = Modifier
                    .size(30.dp)
                    .padding(top = 6.dp) // This moves it down
            )

            Box(
                modifier = Modifier
                    .padding(top = 6.dp)
                    .size(6.dp)

                    .background(color = Color(0xFFFFA726), shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TopNavigationBarPreview() {
    val navController = rememberNavController()
    TopNavigationBar(navController, isNotificationScreen = false)
}
