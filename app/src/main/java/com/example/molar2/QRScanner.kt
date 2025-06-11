package com.example.molar2

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.journeyapps.barcodescanner.ScanOptions
import com.journeyapps.barcodescanner.ScanContract

@Composable
fun QRCodeScanner(
    onQRCodeScanned: (Int) -> Unit  // Passing drawable resource ID
) {
    val scanLauncher = rememberLauncherForActivityResult(contract = ScanContract()) { result ->
        try {
            if (!result.contents.isNullOrBlank()) {
                Log.d("QRCodeScanner", "Scanned QR code: ${result.contents}")
                if (result.contents == "MolAR (1).png") {
                    onQRCodeScanned(R.drawable.molarqr)  // referencing molarQR drawable
                } else {
                    Log.e("QRCodeScanner", "Invalid QR code scanned: ${result.contents}")
                    // Optional: show a message or fallback
                }
            } else {
                Log.e("QRCodeScanner", "Scan returned empty or null result")
            }
        } catch (e: Exception) {
            Log.e("QRCodeScanner", "Error handling QR result: ${e.message}", e)
        }
    }

    // Automatically launch scanner when composable enters composition
    LaunchedEffect(Unit) {
        try {
            scanLauncher.launch(
                ScanOptions().apply {
                    setPrompt("Scan a QR Code to load an image")
                    setBeepEnabled(true)
                    setOrientationLocked(false)
                    setBarcodeImageEnabled(true)
                    setDesiredBarcodeFormats(ScanOptions.QR_CODE)
                }
            )
        } catch (e: Exception) {
            Log.e("QRCodeScanner", "Failed to launch scanner: ${e.message}", e)
        }
    }
}
