package com.example.qrcode.ui.model

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("_embedded")
    val embedded: Embedded,
    @SerializedName("_links")
    val links: LinksXXX,
    val count: Int,
    val total: Int
)
