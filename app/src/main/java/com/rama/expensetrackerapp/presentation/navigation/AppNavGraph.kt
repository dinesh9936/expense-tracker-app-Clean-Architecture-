package com.rama.expensetrackerapp.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rama.expensetrackerapp.presentation.home.HomeScreen
import com.rama.expensetrackerapp.presentation.addexpense.AddExpenseScreen
import com.rama.expensetrackerapp.presentation.insights.InsightsScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {

        composable(Screen.Home.route) {
            HomeScreen(
                onAddClick = {
                    navController.navigate(Screen.AddExpense.passId(-1))
                },
                onInsightsClick = {
                    navController.navigate(Screen.Insights.route)
                },
                onExpenseItemClick = { expense ->
                    navController.navigate(Screen.AddExpense.passId(expense.id))
                }
            )
        }


        composable(
            route = Screen.AddExpense.route,
            arguments = listOf(
                navArgument("id") { type = NavType.IntType }
            )
        ) { entry ->

            val id = entry.arguments?.getInt("id") ?: -1

            AddExpenseScreen(
                id = id,
                onBack = { navController.popBackStack() }
            )
        }


        composable(Screen.Insights.route) {
            InsightsScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
