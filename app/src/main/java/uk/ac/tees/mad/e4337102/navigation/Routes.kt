package uk.ac.tees.mad.e4337102.navigation

sealed class Routes(val route: String) {
    data object Splash : Routes("splash")
    data object Home : Routes("home")
    data object AddHabit : Routes("add_habit")

    data object HabitDetail : Routes("habit_detail/{habitId}") {
        fun create(habitId: Long) = "habit_detail/$habitId"
    }
}

