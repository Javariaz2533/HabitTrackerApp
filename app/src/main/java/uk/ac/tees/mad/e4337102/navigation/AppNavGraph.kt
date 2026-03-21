package uk.ac.tees.mad.e4337102.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import uk.ac.tees.mad.e4337102.screens.AddHabitScreen
import uk.ac.tees.mad.e4337102.screens.HabitDetailScreen
import uk.ac.tees.mad.e4337102.screens.HomeScreen
import uk.ac.tees.mad.e4337102.screens.SplashScreen
import uk.ac.tees.mad.e4337102.viewmodel.HabitViewModel

@Composable
fun AppNavGraph() {
    val navController = rememberNavController()
    val habitViewModel: HabitViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        composable(Routes.Splash.route) {
            SplashScreen(
                onFinished = {
                    navController.navigate(Routes.Home.route) {
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Routes.Home.route) {
            HomeScreen(
                habitViewModel = habitViewModel,
                onAddHabitClick = { navController.navigate(Routes.AddHabit.route) },
                onHabitClick = { id -> navController.navigate(Routes.HabitDetail.create(id)) }
            )
        }

        composable(Routes.AddHabit.route) {
            AddHabitScreen(
                habitViewModel = habitViewModel,
                onBack = { navController.popBackStack() }
            )
        }

        composable(
            route = Routes.HabitDetail.route,
            arguments = listOf(navArgument("habitId") { type = NavType.LongType })
        ) { backStackEntry ->
            val habitId = backStackEntry.arguments?.getLong("habitId") ?: -1L
            HabitDetailScreen(
                habitId = habitId,
                habitViewModel = habitViewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
