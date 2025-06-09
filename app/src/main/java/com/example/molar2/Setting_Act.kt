package com.example.molar2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController

@Composable
fun Setting_ActContent(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        Setting_ActContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun Setting_ActContent(navController: NavController, modifier: Modifier = Modifier) {
    var pushNotif by remember { mutableStateOf(true) }
    var taskReminder by remember { mutableStateOf(true) }
    var scoreReminder by remember { mutableStateOf(false) }

    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val isLargeScreen = screenWidth > 600.dp
    val sectionTitleFontSize = if (isLargeScreen) 18.sp else 14.sp

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        //Spacer(modifier = Modifier.height(16.dp))

        // ✅ Replacing inline top bar with reusable TopNavigationBar
        TopNavigationBar(navController = navController)

        Spacer(modifier = Modifier.height(8.dp))

        // User Info Card
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1D546A), shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF165A72), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .background(Color(0xFF165A72), shape = CircleShape)
                            .clickable {
                                navController.navigate("profile")
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "User",
                            tint = Color.White,
                            modifier = Modifier.size(36.dp)
                        )
                    }

                }
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Juan Dela Cruz", color = Color.White, fontWeight = FontWeight.Bold)
                    Text("Learner", color = Color.LightGray, fontSize = 12.sp)
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Settings", color = Color.White, fontWeight = FontWeight.Bold, fontSize = sectionTitleFontSize)
        Spacer(modifier = Modifier.height(15.dp))

        // Notification Toggles
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1D546A), shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            ToggleRow("Push Notification", Icons.Default.Notifications, pushNotif) { pushNotif = it }
            ToggleRow("Task Reminder", Icons.Default.Notifications, taskReminder) { taskReminder = it }
            ToggleRow("Score Reminder", Icons.Default.Notifications, scoreReminder) { scoreReminder = it }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text("Other Settings", color = Color.White, fontWeight = FontWeight.Bold, fontSize = sectionTitleFontSize)
        Spacer(modifier = Modifier.height(15.dp))

        // Other Settings
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF1D546A), shape = RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(imageVector = Icons.Default.Info, contentDescription = "FAQs", tint = Color.White)
                Spacer(modifier = Modifier.width(12.dp))
                Text("FAQs/Help", color = Color.White)
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("login") {
                        popUpTo(0) // clears backstack so user can't go back to Settings
                    } }
                    .padding(vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logout_icon),
                    contentDescription = "Logout",
                    tint = Color.White,
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text("Logout", color = Color.White)
            }

        }
    }
}

@Composable
fun ToggleRow(label: String, icon: ImageVector, checked: Boolean, onToggle: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(icon, contentDescription = null, tint = Color.White)
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = label, color = Color.White)
        }
        Switch(
            checked = checked,
            onCheckedChange = onToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFF1D546A),  // Set the thumb color when ON to #FFAD2F
                uncheckedThumbColor = Color(0xFFFFAD2F),  // Set the thumb color when OFF to #FFAD2F
                checkedTrackColor = Color(0xFFFFAD2F),  // Set the track color when ON to #FFAD2F
                uncheckedTrackColor = Color(0xFFBDBDBD)  // Set the track color when OFF to #FFAD2F
            )
        )
    }
}




@Preview(showBackground = true)
@Composable
fun SettingPreview() {
    Setting_ActContent(navController = rememberNavController())
}
