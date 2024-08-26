package com.example.qrcode.ui.model

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("_embedded")
    val embedded: Embedded,
    val count: Int,
    val total: Int
)
