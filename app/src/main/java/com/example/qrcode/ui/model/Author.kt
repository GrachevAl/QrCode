package com.example.qrcode.ui.model

import com.google.gson.annotations.SerializedName

data class Author(
    @SerializedName("_links")
    val links: LinksXX,
    @SerializedName("author_id")
    val authorId: String,
    val bio: Any,
    @SerializedName("created_at")
    val createdAt: String,
    val name: String,
    val slug: String,
    @SerializedName("updated_at")
    val updatedAt: String
)