package `in`.kay.ehub.presentation.navigation

sealed class NavRoutes(val route:String) {
    object Splash : NavRoutes("splash")
    object Auth : NavRoutes("auth")
    object Login : NavRoutes("login")
    object Register : NavRoutes("register")
    object Home : NavRoutes("home")
}
