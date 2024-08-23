package com.example.qrcode.ui.model

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("_links")
    val links: LinksXX,
    @SerializedName("created_at")
    val createdAt: String,
    val filename: Any,
    @SerializedName("quote_source_id")
    val quoteSourceId: String,
    val remarks: Any,
    @SerializedName("updated_at")
    val updatedAt: String,
    val url: String
)
