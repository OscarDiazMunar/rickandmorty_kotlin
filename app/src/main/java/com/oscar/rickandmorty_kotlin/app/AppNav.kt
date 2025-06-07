package com.oscar.rickandmorty_kotlin.app

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.oscar.rickandmorty_kotlin.commons.NavigationScreen
import com.oscar.rickandmorty_kotlin.presentation.detail.DetailCharacterScreen
import com.oscar.rickandmorty_kotlin.presentation.list.ListCharactersScreen

@Composable
fun App(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavigationScreen.Home.route){
        composable(
            NavigationScreen.Home.route
        ){
            ListCharactersScreen(hiltViewModel(), navController )
        }

        composable(NavigationScreen.Detail.route + "/{itemId}",
            arguments = listOf(navArgument("itemId"){
                type = NavType.StringType
            })){
            DetailCharacterScreen(
                id = it.arguments?.getString("itemId"),
                viewModel = hiltViewModel(),
                navController = navController)
        }
    }
}