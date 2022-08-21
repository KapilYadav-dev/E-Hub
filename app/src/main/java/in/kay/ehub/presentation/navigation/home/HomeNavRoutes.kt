package `in`.kay.ehub.presentation.navigation.home

sealed class HomeNavRoutes(val route:String) {
    object Home : HomeNavRoutes("home")
    object Domain : HomeNavRoutes("domain")
    object Course : HomeNavRoutes("course")
    object Event : HomeNavRoutes("event")
    object Internship : HomeNavRoutes("internship")
}
