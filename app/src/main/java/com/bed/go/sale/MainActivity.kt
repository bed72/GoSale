package com.bed.go.sale

import android.os.Bundle

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController

import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

import com.bed.go.sale.presentation.screens.Screens
import com.bed.go.sale.presentation.theme.GoSaleTheme
import com.bed.go.sale.presentation.navigation.Navigation

class MainActivity : ComponentActivity() {

    private var keepSplashOpened = true

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            keepSplashOpened
        }

        setContent {
            GoSaleTheme(isDynamicColor = false) {
                val navigationController = rememberNavController()

                Navigation(
                    startDestination = Screens.Home.route,
                    navigationController = navigationController
                ) {
                    keepSplashOpened = false
                }
            }
        }
    }
}
