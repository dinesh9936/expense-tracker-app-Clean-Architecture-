package com.rama.expensetrackerapp.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object AddExpense : Screen("add_expense/{id}") {
        fun passId(id: Int) = "add_expense/$id"
    }
    object Insights : Screen("insights")
}