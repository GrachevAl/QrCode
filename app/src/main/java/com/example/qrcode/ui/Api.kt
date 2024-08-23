package com.example.qrcode.ui

import com.example.qrcode.ui.model.Quote
import com.example.qrcode.ui.model.QuoteX
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {

    @GET("/search/quote")
    suspend fun searchQuote(
        @Query("query") query : String
    ) : List<QuoteX>
}