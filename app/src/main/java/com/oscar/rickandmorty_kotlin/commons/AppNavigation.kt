package com.oscar.rickandmorty_kotlin.commons

enum class Screen {
    HOME,
    DETAIL,
}
sealed class NavigationScreen(val route: String) {
    object Home : NavigationScreen(Screen.HOME.name)
    object Detail : NavigationScreen(Screen.DETAIL.name)
}