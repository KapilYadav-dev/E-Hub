package `in`.kay.ehub.presentation.navigation.home

sealed class HomeNavRoutes(val route:String) {
    object Home : HomeNavRoutes("home")

    object Domain : HomeNavRoutes("domain")
    object DomainSubScreen: HomeNavRoutes("domainSub")
    object ResourceScreen:HomeNavRoutes("resourceScreen")
    object MentorsScreen:HomeNavRoutes("mentorsScreen")
    object MagazinesScreen:HomeNavRoutes("magazinesScreen")

    object Course : HomeNavRoutes("course")
    object SeeAllScreen : HomeNavRoutes("seeAllScreen")
    object CourseDetails: HomeNavRoutes("courseDetailScreen")
    object Event : HomeNavRoutes("event")
    object HandbookDetails : HomeNavRoutes("handbookDetails")
    object EventMain : HomeNavRoutes("eventMain")
    object Internship : HomeNavRoutes("internship")
    object News : HomeNavRoutes("news")
    object Youtube : HomeNavRoutes("youtube")
    object CampusActivities : HomeNavRoutes("campusActivities")
}
