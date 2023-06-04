package com.bed.go.sale.presentation.navigation.routes

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

import androidx.compose.runtime.LaunchedEffect

import com.bed.go.sale.presentation.screens.Screens
import com.bed.go.sale.presentation.screens.home.HomeScreen

fun NavGraphBuilder.homeRoute(
    onDataLoaded: () -> Unit
) {
    composable(route = Screens.Home.route) {
        LaunchedEffect(key1 = null) {
            onDataLoaded()
        }

        HomeScreen()
    }
}
