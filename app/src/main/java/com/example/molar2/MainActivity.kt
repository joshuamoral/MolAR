package com.example.molar2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.molar2.ui.theme.MolAR2Theme
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MolAR2Theme {
                val navController = rememberNavController()
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFF0D232E)
                ) {
                    NavHost(navController = navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController) }
                        composable("metals") { MetalsScreen(navController) }
                        composable("li_screen") { LiScreen(navController) }
                        composable("be_screen") { BeScreen(navController) }
                        composable("b_screen") { BScreen(navController) }
                        composable("settings") { Setting_ActContent(navController) }
                        composable("assessment") { Assessment(navController) }
                        composable("login") { LoginScreen(navController) }
                        composable("register") { RegistrationScreen(navController) }
                        composable("profile") { ProfileAct(navController) }
                        composable("matching_level") { MatchingLvl(navController) }
                        composable("identify_level") { IdentifyLvl(navController) }
                        composable("combination_level") { CombinationLvl(navController) }
                        composable("pics_level") { PicsLvl(navController) }
                        composable("notification") { Notification(navController) }
                        composable("identify_lvl_1") { IdentifyLvl1(navController) }

                        composable("ar_screen/{model}") { backStackEntry ->
                            val model = backStackEntry.arguments?.getString("model") ?: "models/lithium.glb"
                            ARScreen(navController, model)
                        }

                        composable("picslvl1") { PicsLvl1(navController) }

                        composable("qrscanner") {
                            QRCodeScanner { scannedContent ->
                                // Navigate to ARScreen with model path depending on QR content
                                navController.navigate("ar_screen/$scannedContent")
                            }
                        }


                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFF0D232E),
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            // Apply the TopNavigationBar
            TopNavigationBar(navController)

            Spacer(modifier = Modifier.height(16.dp))

            // Progress Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(
                        Brush.verticalGradient(
                            listOf(Color(0xFF165A72), Color(0xFF1D546A))
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ),
                contentAlignment = Alignment.CenterStart
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text("MY PROGRESS", color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Bold, lineHeight = 13.sp)
                        Text("Track Your Learning", color = Color.LightGray, fontSize = 10.sp, lineHeight = 10.sp)
                    }
                    Spacer(Modifier.weight(1f))
                    CircularProgressIndicator(
                        progress = 0.6f,
                        color = Color(0xFFFFA726),
                        modifier = Modifier
                            .padding(16.dp)
                            .size(30.dp),
                        strokeWidth = 4.dp
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Welcome
            Text(
                buildAnnotatedString {
                    withStyle(SpanStyle(Color.White)) { append("Welcome Back, ") }
                    withStyle(SpanStyle(Color(0xFFFFA726))) { append("Juan!") }
                },
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                        lineHeight = 20.sp
            )

            Text(
                "Explore the Elements, Ignite Your Curiosity \nLearn Anywhere, Anytime!",
                color = Color.LightGray,
                fontSize = 12.sp,
                        lineHeight = 12.sp
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Main Banner Box
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(Color(0xFF113543), Color(0xFF1D546A))
                        ),
                        shape = RoundedCornerShape(20.dp)
                    )
            )

            Spacer(modifier = Modifier.height(24.dp))

            // REVIEW MORE Title
            Text(
                text = "REVIEW MORE",
                color = Color(0xFFFFA726),
                fontWeight = FontWeight.Bold,
                fontSize = 14.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Home Cards
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Spacer(modifier = Modifier.weight(1f))
                HomeCard("About MolAR", android.R.drawable.ic_menu_info_details)
                HomeCard("Achievements", android.R.drawable.ic_menu_compass)
                HomeCard("Learn More", android.R.drawable.ic_menu_search)
                Spacer(modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun HomeCard(title: String, iconId: Int) {
    Box(
        modifier = Modifier
            .size(100.dp)
            .background(
                brush = Brush.verticalGradient(
                    listOf(Color(0xFF113543), Color(0xFF1D546A))
                ),
                shape = RoundedCornerShape(16.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                contentDescription = "$title Icon",
                tint = Color(0xFF2F98C7),
                modifier = Modifier.size(40.dp)
            )
            Text(text = title, color = Color.White, fontSize = 10.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    MolAR2Theme {
        val navController = rememberNavController()
        HomeScreen(navController = navController)
    }
}
