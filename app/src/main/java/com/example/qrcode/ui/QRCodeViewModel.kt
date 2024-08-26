package com.example.qrcode.ui

import android.graphics.Bitmap
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QRCodeViewModel @Inject constructor(
    private val qrCodeGenerator: QRCodeGenerator,
    private val repository: Repository
) : ViewModel() {

    private val _state = MutableStateFlow(State())
    val state = _state.asStateFlow()


    @RequiresApi(Build.VERSION_CODES.O)
    fun generateQRCode(content: String, size: Int): Bitmap? {
        return qrCodeGenerator.generateQRCode(content, size)
    }

    fun setQuery(query: String) {
        _state.update { it.copy(query = query) }

    }


    /* suspend fun getQuote(){
         val query = state.value.query
         viewModelScope.launch {
             repository.getQuote(query).collect{ quote->
                 _state.update {
                     it.copy(
                         quote = quote
                     )
                 }

             }
         }
     }*/

    suspend fun getQuote() {
        val query = state.value.query.trim()

        repository.getQuote(query).collect { quote ->
            when (quote) {
                is ResultClass.Success -> {
                    _state.update {
                        it.copy(quote = quote.data, loading = false, errorMessage = "")
                    }
                }
                is ResultClass.Error -> {
                    val errorMessage = if (quote.exception.message == "Query cannot be empty.") {
                        "Please enter a query."
                    } else {
                        "Nothing was found"
                    }
                    _state.update {
                        it.copy(errorMessage = errorMessage, loading = false, quote = emptyList())
                    }
                }
            }
        }
    }
}