package com.example.qrcode.ui

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.qrcode.ui.model.QuoteX

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun QRCodeContent(content: String, size: Int) {
    val viewModel = hiltViewModel<QRCodeViewModel>()
    val bitmap = viewModel.generateQRCode(content, size)


    if (bitmap != null) {
        Image(bitmap = bitmap.asImageBitmap(), contentDescription = "QR Code")
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PreviewQRCodeContent(
    viewModel: QRCodeViewModel = hiltViewModel<QRCodeViewModel>()
) {
    Render(viewModel = viewModel)
}

@Composable
fun ItemQuote(quoteX: QuoteX) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            // Отображение тегов
            Text(
                text = "Tags: ${quoteX.tags.joinToString(", ")}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = Modifier.height(8.dp))

            // Отображение цитаты
            Text(
                text = "Quote: ${quoteX.value}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldQuery(
    query: String, onValueChange: (String) -> Unit
) {
    TextField(value = query, onValueChange = { onValueChange(it) })
}


@Composable
fun Render(viewModel: QRCodeViewModel) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.query) {
        viewModel.getQuote()
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(16.dp)) {
            TextFieldQuery(query = state.query, onValueChange = viewModel::setQuery)

            if (state.loading) {
                CircularProgressIndicator()
            } else if (state.errorMessage.isNotEmpty()) {
                Text(
                    "Error: ${state.errorMessage}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.error
                )
            } else if (state.quote.isNotEmpty()) {
                LazyColumn {
                    items(state.quote, key = { it.quoteId }) { quote ->
                        ItemQuote(quoteX = quote)
                    }
                }
            }
        }
    }
}
