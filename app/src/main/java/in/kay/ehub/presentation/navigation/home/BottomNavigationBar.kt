package `in`.kay.ehub.presentation.navigation.home

import `in`.kay.ehub.R
import `in`.kay.ehub.ui.theme.colorBlack
import `in`.kay.ehub.ui.theme.colorPrimary
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(
        backgroundColor = colorWhite
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItems().forEach { navItem ->
            BottomNavigationItem(
                selected = currentRoute == navItem.route,
                onClick = {
                    if (currentRoute != navItem.route)
                        navController.navigate(navItem.route) {
                            popUpTo(HomeNavRoutes.Home.route)
                        }
                },
                icon = {
                    Icon(
                        painter = navItem.img!!,
                        contentDescription = navItem.label,
                        modifier = Modifier.size(24.dp),
                        tint = if (currentRoute == navItem.route) colorPrimary else colorBlack
                    )
                },
                label = {
                    Text(
                        text = navItem.label,
                        fontWeight = FontWeight.SemiBold,
                        color = if (currentRoute == navItem.route) colorPrimary else colorBlack
                    )
                },
                alwaysShowLabel = false
            )
        }
    }
}

@Composable
fun BottomNavItems() = listOf(
    BottomNavItem(
        label = "Home",
        img = painterResource(id = R.drawable.ic_home),
        route = HomeNavRoutes.Home.route
    ),
    BottomNavItem(
        label = "Domain",
        img = painterResource(id = R.drawable.ic_domain),
        route = HomeNavRoutes.Domain.route
    ),
    BottomNavItem(
        label = "Courses",
        img = painterResource(id = R.drawable.ic_courses),
        route = HomeNavRoutes.Course.route
    ),
    BottomNavItem(
        label = "Events",
        img = painterResource(id = R.drawable.ic_events),
        route = HomeNavRoutes.Event.route
    ),
    BottomNavItem(
        label = "Intern",
        img = painterResource(id = R.drawable.ic_internship),
        route = HomeNavRoutes.Internship.route
    )

)


data class BottomNavItem(
    val label: String,
    val icon: ImageVector? = null,
    val img: Painter? = null,
    val route: String,
)