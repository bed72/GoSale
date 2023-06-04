package com.bed.go.sale.presentation.screens

sealed class Screens(val route: String) {
    object Home : Screens("home")
}
