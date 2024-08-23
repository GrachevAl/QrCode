package com.example.qrcode.ui

import com.example.qrcode.ui.model.Embedded
import com.example.qrcode.ui.model.Quote
import com.example.qrcode.ui.model.QuoteX
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface Api {

    @GET("/search/quote")
    @Headers("accept: application/hal+json")
    suspend fun searchQuote(
        @Query("query") query : String
    ) : List<Quote>
}