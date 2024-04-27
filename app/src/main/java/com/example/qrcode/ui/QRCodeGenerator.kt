package com.example.qrcode.ui

import android.R
import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.os.Build
import android.widget.ImageView
import androidx.annotation.RequiresApi
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder


class QRCodeGenerator {
    
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    fun generateQRCode(content: String, size: Int): Bitmap? {

        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(content, BarcodeFormat.QR_CODE, size, size)
            return bitmap
        } catch (e: Exception) {
            e.printStackTrace()
        }

        
        return null
    }
}