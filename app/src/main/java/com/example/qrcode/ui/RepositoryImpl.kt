package com.example.qrcode.ui

import android.util.Log
import com.example.qrcode.ui.model.Embedded
import com.example.qrcode.ui.model.Quote
import com.example.qrcode.ui.model.QuoteX
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: Api): Repository {
    override suspend fun getQuote(query: String): Flow<List<QuoteX>> = flow{
        try {
            val result = api.searchQuote(query)
            emit(result.embedded.quotes)
        } catch (e: HttpException){
            Log.e("RepositoryError", "HTTP error: ${e.response()?.errorBody()?.string()}")
        }
    }
}