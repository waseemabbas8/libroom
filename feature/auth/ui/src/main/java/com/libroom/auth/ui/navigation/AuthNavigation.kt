package com.libroom.auth.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.libroom.auth.ui.LoginRoute
import kotlinx.serialization.Serializable

@Serializable data object LoginRoute

@Composable
fun AuthNavGraph(
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = rememberNavController(),
        startDestination = LoginRoute,
        modifier = modifier
    ) {
        composable<LoginRoute> {
            LoginRoute()
        }
    }
}