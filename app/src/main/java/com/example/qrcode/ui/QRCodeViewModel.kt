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

    suspend fun getQuote(){
        viewModelScope.launch {
            repository.getQuote("Barack").collect{ quote->
                _state.update {
                    it.copy(
                        quote = quote
                    )
                }
                Log.e("Quote", "${_state.value.quote}")
                Log.e("Quote", "${_state.value.errorMessage}")
                Log.e("Quote", "${state.value.quote}")
            }
        }
    }

    /*suspend fun getQuote() {
        repository.getQuote("Barack Obama").collect { quote ->
            Log.e("Quote", "${quote}")
            when(quote){
                is ResultClass.Success ->{
                    _state.update {
                        it.copy(
                          quote = quote.data.embedded.quotes
                        )
                    }
                }
                is ResultClass.Error ->{
                    _state.update {
                        it.copy(
                            errorMessage = "null"
                        )
                    }
                }
            }
        }


        Log.e("Quote", "${_state.value.quote}")
        Log.e("Quote", "${_state.value.errorMessage}")
        Log.e("Quote", "${state.value.quote}")
    }*/
}