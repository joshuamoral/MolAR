package com.example.molar2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.res.painterResource
import androidx.compose.material3.Text
import androidx.compose.material3.Divider

@Composable
fun LiAct(navController: NavController) {
    Scaffold(
        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        LiActContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LiActContent(navController: NavController, modifier: Modifier = Modifier) {
    // Make the entire Column scrollable
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)  // Add vertical scroll functionality
    ) {
        // ✅ TopNavigationBar at the very top
        TopNavigationBar(navController = navController)

        // Section for Lithium Information
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(Color(0xFF113543), Color(0xFF1D546A))
                    ),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                "Lithium",
                color = Color.White,
                modifier = Modifier
                    .padding(top = 0.dp, start = 12.dp, bottom = 3.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Image with AR button
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.lithium_image),
                contentDescription = "Lithium Image",
                modifier = Modifier.fillMaxSize()
            )

            // AR Icon Button
            Image(
                painter = painterResource(id = R.drawable.ar_symbol),
                contentDescription = "View in AR",
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.TopEnd)
                    .padding(8.dp)
                    .clickable {
                        // TODO: Add AR Action
                    }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Atomic Number and Element Info
        Box(
            modifier = Modifier.wrapContentSize()
        ) {
            Text(
                text = "3",
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.7f),
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 2.dp, start = 2.dp)
            )

            Text(
                text = "Li",
                fontSize = 48.sp,
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Center)
                    .padding(start = 16.dp)
            )
        }

        // Additional Information and Sections
        Text("Lithium", color = Color(0xFFFFB100), fontSize = 20.sp)
        Text(
            "Lithium is silvery in appearance, like other alkali metals...",
            color = Color.White,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.lithium_uses),
            contentDescription = "Lithium Uses",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(bottom = 8.dp)
        )

        Text("Uses", color = Color(0xFFFFB100), fontWeight = FontWeight.Bold)
        Text(
            "Lithium has many uses including in heat transfer applications...",
            color = Color.White,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Sources Section
        Text("Sources", color = Color(0xFFFFB100), fontWeight = FontWeight.Bold)
        Text(
            "Lithium does not occur freely in nature, but is found in small units in igneous rocks...",
            color = Color.White,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Details Section
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 55.dp)
        ) {
            Text("Details", color = Color(0xFFFFB100), fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))

            DetailRow("Latin Name", "Lithos")
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 0.5.dp)

            DetailRow("Discovery Year", "1817")
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 0.5.dp)

            DetailRow("Atomic Weight", "6.941")
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 0.5.dp)

            DetailRow("Melting Point", "180.50 °C")
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 0.5.dp)

            DetailRow("Boiling Point", "1,342 °C")
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 0.5.dp)

            DetailRow("Number of Valence Electrons", "1")
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 0.5.dp)

            DetailRow("Phase at STP", "Solid")
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 0.5.dp)

            DetailRow("Electronic Configuration", "[He]2s1")
            Divider(color = Color.Gray.copy(alpha = 0.3f), thickness = 0.5.dp)

            DetailRow("Discovered by", "Johann August Arfvedson")
        }

        Spacer(modifier = Modifier.weight(1f)) // Push bottom nav to bottom
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(label, color = Color.White)
        Text(value, color = Color(0xFFFFB100))
    }
}

@Preview(showBackground = true)
@Composable
fun LiAct() {
    LiAct(navController = rememberNavController())
}
