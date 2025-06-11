package com.example.molar2

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import android.net.Uri



@Composable
fun LiScreen(navController: NavController) {
    Scaffold(

        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        LiScreen(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun LiScreen(navController: NavController, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0D232E))
            .padding(16.dp)
    ) {
        TopNavigationBar(navController = navController)
        Spacer(modifier = Modifier.height(8.dp))

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

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp)
        ) {
            Spacer(modifier = Modifier.height(12.dp))



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
                            val lithiumModelPath = "models/goldmol.glb"
                            navController.navigate("ar_screen/${Uri.encode(lithiumModelPath)}")

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
                "Lithium (from Ancient Greek λίθος (líthos) 'stone'; symbol Li and atomic number 3) is a soft, silvery-white alkali metal. Under standard conditions, it is the least dense metal and the least dense solid element. Like all alkali metals, lithium is highly reactive and flammable, and must be stored in vacuum, inert atmosphere, or inert liquid such as purified kerosene[7] or mineral oil. It exhibits a metallic luster. It corrodes quickly in air to a dull silvery gray, then black tarnish. It does not occur freely in nature, but occurs mainly as pegmatitic minerals, which were once the main source of lithium. Due to its solubility as an ion, it is present in ocean water and is commonly obtained from brines. Lithium metal is isolated electrolytically from a mixture of lithium chloride and potassium chloride.",
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
                "Lithium and its compounds have several industrial applications, including heat-resistant glass and ceramics, lithium grease lubricants, flux additives for iron, steel and aluminium production, lithium metal batteries, and lithium-ion batteries. These uses consume more than three-quarters of lithium production.",
                color = Color.White,
                fontSize = 14.sp
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Sources Section
            Text("Sources", color = Color(0xFFFFB100), fontWeight = FontWeight.Bold)
            Text(
                "The nucleus of the lithium atom verges on instability, since the two stable lithium isotopes found in nature have among the lowest binding energies per nucleon of all stable nuclides. Because of its relative nuclear instability, lithium is less common in the solar system than 25 of the first 32 chemical elements even though its nuclei are very light: it is an exception to the trend that heavier nuclei are less common.[8] For related reasons, lithium has important uses in nuclear physics. The transmutation of lithium atoms to helium in 1932 was the first fully human-made nuclear reaction, and lithium deuteride serves as a fusion fuel in staged thermonuclear weapons.",
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

        }
    }
}




@Preview(showBackground = true)
@Composable
fun LiScreenPreview() {
    LiScreen(navController = rememberNavController())
}
