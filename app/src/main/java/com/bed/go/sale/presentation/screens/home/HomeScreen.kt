package com.bed.go.sale.presentation.screens.home


import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.material3.MaterialTheme

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier.
        fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
    ) {
        Text(text = "Bed", color = MaterialTheme.colorScheme.primary)
    }
}
