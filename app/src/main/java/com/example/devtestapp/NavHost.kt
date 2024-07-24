package com.example.devtestapp

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.devtestapp.ConstRoute.LINKS_ROUTE
import com.example.devtestapp.linksPage.ui.LinksScreen

@Composable
fun MyNavHost(navController: NavHostController, startDestination: String) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable(LINKS_ROUTE) {
            LinksScreen(navController = navController, LocalContext.current)
        }
    }


}