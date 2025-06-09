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
import androidx.compose.foundation.clickable

@Composable
fun Notification(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        Notification(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Notification(navController: NavController, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Top bar
        TopNavigationBar(navController = navController)

        Spacer(modifier = Modifier.height(24.dp))

        // List of notifications
        val notifications = listOf(
            Triple("Fun Fact of the Day", "Did you know? Gold doesn’t rust because it’s chemically inert. Learn why.", "23 min"),
            Triple("Progress Reminder", "You’ve learned 15 metals so far—almost halfway there! Next up: Actinides.", "1 hr"),
            Triple("Weekend Deep Dives", "Dive deeper! Today’s long-form lesson: Rare Earth Metals and Why They Matter.", "3 hr"),
            Triple("Review & Refresh", "Flashback Friday! Quick review of the metals you’ve met this week.", "1 dy"),
            Triple("Fun Fact of the Day", "Titanium is used in spacecraft. Why? Tap to find out!", "2 wk"),
            Triple("Study Streak Reminders", "Keep your learning streak alive! Explore a new metal today.", "3 hr")
        )

        notifications.forEachIndexed { index, (title, subtitle, time) ->
            NotificationItem(
                title = title,
                subtitle = subtitle,
                time = time,
                showIndicator = index < 3 // ✅ Show yellow dot for first 3 only
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Push content to top if screen is tall
    }
}

@Composable
fun NotificationItem(
    title: String,
    subtitle: String,
    time: String,
    showIndicator: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (showIndicator) {
                    Box(
                        modifier = Modifier
                            .size(6.dp)
                            .background(Color(0xFFFFA726), shape = RoundedCornerShape(50))
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text(
                    text = title,
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                text = time,
                color = Color.Gray,
                fontSize = 12.sp
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = subtitle,
            color = Color.LightGray,
            fontSize = 14.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color.DarkGray, thickness = 1.dp)
    }
}

@Preview(showBackground = true)
@Composable
fun NotificationPreview() {
    Notification(navController = rememberNavController())
}
