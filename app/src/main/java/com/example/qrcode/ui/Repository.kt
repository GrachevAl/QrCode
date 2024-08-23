package com.example.qrcode.ui

import com.example.qrcode.ui.model.Quote
import com.example.qrcode.ui.model.QuoteX
import kotlinx.coroutines.flow.Flow
import retrofit2.http.Query

interface Repository {

    suspend fun getQuote(query: String): Flow<List<QuoteX>>
}