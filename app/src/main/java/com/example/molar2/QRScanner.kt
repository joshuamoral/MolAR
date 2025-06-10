package com.example.molar2

import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.journeyapps.barcodescanner.ScanOptions
import com.journeyapps.barcodescanner.ScanContract

@Composable
fun QRCodeScanner(
    onQRCodeScanned: (String) -> Unit
) {
    val scanLauncher = rememberLauncherForActivityResult(contract = ScanContract()) { result ->
        try {
            if (!result.contents.isNullOrBlank()) {
                Log.d("QRCodeScanner", "Scanned QR code: ${result.contents}")
                onQRCodeScanned(result.contents)
            } else {
                Log.e("QRCodeScanner", "Scan returned empty or null result")
                // Optional fallback:
                // onQRCodeScanned("molar.glb") // fallback model
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
                    setPrompt("Scan a QR Code to load a 3D model")
                    setBeepEnabled(true)
                    setOrientationLocked(false)
                    setBarcodeImageEnabled(true)
                    setDesiredBarcodeFormats(ScanOptions.QR_CODE) // Optional: limit to QR codes only
                }
            )
        } catch (e: Exception) {
            Log.e("QRCodeScanner", "Failed to launch scanner: ${e.message}", e)
        }
    }
}
