package com.example.qrcode.ui

import android.graphics.Bitmap
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor(private val qrCodeGenerator: QRCodeGenerator) : ViewModel() {

    @RequiresApi(Build.VERSION_CODES.O)
    fun generateQRCode(content: String, size: Int): Bitmap? {
        return qrCodeGenerator.generateQRCode(content, size)
    }
}