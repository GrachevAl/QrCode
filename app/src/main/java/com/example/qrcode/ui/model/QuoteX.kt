package com.example.qrcode.ui.model

import com.google.gson.annotations.SerializedName

data class QuoteX(
    @SerializedName("_embedded")
    val embedded: EmbeddedX,
    @SerializedName("_links")
    val links: LinksXX,
    @SerializedName("appeared_at")
    val appearedAt: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("quote_id")
    val quoteId: String,
    val tags: List<String>,
    @SerializedName("updated_at")
    val updatedAt: String,
    val value: String
)



