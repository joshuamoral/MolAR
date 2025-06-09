package com.example.molar2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

data class Metal(val number: String, val symbol: String)

@Composable
fun MetalsScreen(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        containerColor = Color(0xFF0D232E)
    ) { innerPadding ->
        MetalsScreenContent(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun MetalsScreenContent(navController: NavController, modifier: Modifier = Modifier) {
    val metals = remember {
        listOf(
            Metal("3", "Li"), Metal("4", "Be"), Metal("11", "Na"), Metal("12", "Mg"),
            Metal("13", "Al"), Metal("19", "K"), Metal("20", "Ca"), Metal("21", "Sc"),
            Metal("22", "Ti"), Metal("23", "V"), Metal("24", "Cr"), Metal("25", "Mn"),
            Metal("26", "Fe"), Metal("27", "Co"), Metal("28", "Ni"), Metal("29", "Cu"),
            Metal("30", "Zn"), Metal("31", "Ga"), Metal("37", "Rb"), Metal("38", "Sr"),
            Metal("39", "Y"), Metal("40", "Zr"), Metal("41", "Nb"), Metal("41", "Mo"),
            Metal("43", "Tc"), Metal("44", "Ru"), Metal("45", "Rh"), Metal("46", "Pd"),
            Metal("47", "Ag"), Metal("48", "Cd"), Metal("49", "In"), Metal("50", "Sn"),
            Metal("55", "Cs"), Metal("56", "Ba"), Metal("72", "Hf"), Metal("73", "Ta"),
            Metal("74", "W"), Metal("75", "Re"), Metal("76", "Os"), Metal("77", "Ir"),
            Metal("78", "Pt"), Metal("79", "Au"), Metal("80", "Hg"), Metal("81", "Tl"),
            Metal("82", "Pb"), Metal("83", "Bi"), Metal("84", "Po"), Metal("87", "Fr"),
            Metal("88", "Ra"), Metal("104", "Rf"), Metal("105", "Db"), Metal("106", "Sg"),
            Metal("107", "Bh"), Metal("108", "Hs"), Metal("109", "Mt"), Metal("110", "Ds"),
            Metal("111", "Rg"), Metal("112", "Cn"), Metal("113", "Nh"), Metal("114", "Fl"),
            Metal("115", "Mc"), Metal("116", "Lv"), Metal("117", "Tn"), Metal("57", "La"),
            Metal("58", "Ce"), Metal("59", "Pr"), Metal("60", "Nd"), Metal("61", "Pm"),
            Metal("62", "Sm"), Metal("63", "Eu"), Metal("64", "Gd"), Metal("65", "Tb"),
            Metal("66", "Dy"), Metal("67", "Ho"), Metal("68", "Er"), Metal("69", "Tm"),
            Metal("70", "Yb"), Metal("71", "Lu"), Metal("89", "Ac"), Metal("90", "Th"),
            Metal("91", "Pa"), Metal("92", "U"), Metal("93", "Np"), Metal("94", "Pu"),
            Metal("95", "Am"), Metal("96", "Cm"), Metal("97", "Bk"), Metal("98", "Cf"),
            Metal("99", "Es"), Metal("100", "Fm"), Metal("101", "Md"), Metal("102", "No"),
            Metal("103", "Lr")
        )
    }

    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0D232E))
            .padding(16.dp)
    ) {
        TopNavigationBar(navController = navController)
        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            placeholder = { Text("Search Element") },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            textStyle = LocalTextStyle.current.copy(color = Color.White)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 80.dp)
        ) {
            metals.chunked(4).forEach { rowItems ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowItems.forEach { metal ->
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(1f)
                        ) {
                            MetalItem(metal, navController)
                        }
                    }

                    // Fill remaining space if row has fewer than 4 items
                    repeat(4 - rowItems.size) {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}


@Composable
fun MetalItem(metal: Metal, navController: NavController) {
    Box(
        modifier = Modifier
            .aspectRatio(1f)
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFF1D546A), Color(0xFF113543))
                ),
                shape = RoundedCornerShape(12.dp)
            )
            .clickable {
                when (metal.symbol) {
                    "Li" -> navController.navigate("li_screen")
                    "Be" -> navController.navigate("be_screen")
                    // Add more navigation cases here if needed
                }
            }
            .padding(8.dp)
    ) {
        Text(
            text = metal.number,
            color = Color.White.copy(alpha = 0.7f),
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier.align(Alignment.TopStart)
        )

        Text(
            text = metal.symbol,
            color = Color.White,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MetalsScreenPreview() {
    MetalsScreenContent(navController = rememberNavController())
}
