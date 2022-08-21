package `in`.kay.ehub.presentation.navigation.home

import `in`.kay.ehub.presentation.home.screens.home.HomeScreen
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeNavigationContainer(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel
) {
    AnimatedNavHost(
        navController = navController,
        startDestination = HomeNavRoutes.Home.route,
    ) {
        composable(HomeNavRoutes.Home.route, exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }) {
            HomeScreen(navController,paddingValues,viewModel)
        }
        composable(HomeNavRoutes.Domain.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }) {

        }
        composable(HomeNavRoutes.Course.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }) {

        }
        composable(HomeNavRoutes.Event.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        }) {

        }
        composable(HomeNavRoutes.Internship.route, exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }) {
            HomeScreen(navController, paddingValues, viewModel)
        }
    }
}

