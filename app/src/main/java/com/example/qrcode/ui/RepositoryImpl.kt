package com.example.qrcode.ui

import android.util.Log
import com.example.qrcode.ui.model.Embedded
import com.example.qrcode.ui.model.Quote
import com.example.qrcode.ui.model.QuoteX
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val api: Api) : Repository {
    override suspend fun getQuote(query: String): Flow<ResultClass<List<QuoteX>>> = flow {
        // Check if the query is empty
        if (query.isEmpty()) {
            Log.e("RepositoryError", "Query is empty.")
            emit(ResultClass.Error(Exception("Query cannot be empty.")))
            return@flow // Exit early
        }

        try {
            val result = api.searchQuote(query)
            emit(ResultClass.Success(result.embedded.quotes))
        } catch (e: HttpException) {
            Log.e("RepositoryError", "HTTP error: ${e.code()} - ${e.response()?.errorBody()?.string()}")
            emit(ResultClass.Error(e))
        } catch (e: Exception) {
            Log.e("RepositoryError", "General error: ${e.message}")
            emit(ResultClass.Error(e))
        }
    }
}
