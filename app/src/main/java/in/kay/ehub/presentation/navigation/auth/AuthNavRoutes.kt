package `in`.kay.ehub.presentation.navigation.auth

sealed class AuthNavRoutes(val route:String) {
    object Splash : AuthNavRoutes("splash")
    object Auth : AuthNavRoutes("auth")
    object Login : AuthNavRoutes("login")
    object Register : AuthNavRoutes("register")
    object Home : AuthNavRoutes("home")

}
