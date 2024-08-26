package com.example.qrcode.ui

import com.example.qrcode.ui.model.Embedded
import com.example.qrcode.ui.model.Quote
import com.example.qrcode.ui.model.QuoteX
import java.io.IOException

data class State(
    val query : String = "",
    val quote: List<QuoteX> = emptyList(),
    val errorMessage: String = ""
)
