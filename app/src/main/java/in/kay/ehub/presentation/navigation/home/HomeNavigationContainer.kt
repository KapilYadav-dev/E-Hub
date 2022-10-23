package `in`.kay.ehub.presentation.navigation.home

import `in`.kay.ehub.presentation.home.screens.home.*
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavArgs
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun HomeNavigationContainer(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel,
    showBottomNav: (Boolean) -> Unit
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
            showBottomNav(true)
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
            showBottomNav(true)
            DomainScreen(viewModel = viewModel, navController = navController)
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
            showBottomNav(true)
            CourseScreen2(viewModel = viewModel, paddingValues = paddingValues,navController = navController)
        }

        composable(HomeNavRoutes.News.route+"/{item}",
            arguments = listOf(navArgument("item"){
                type = NavType.IntType
            }),
                exitTransition = {
                slideOutOfContainer(
                    AnimatedContentScope.SlideDirection.Left,
                    animationSpec = tween(700)
                )
            }
        ) {
            val index = it.arguments?.getInt("item")
            showBottomNav(false)
            NewsScreen(viewModel = viewModel,navController=navController ,newsIndex = index!!)
        }

        composable(HomeNavRoutes.CourseDetails.route+"/{item}",
            arguments = listOf(navArgument("item"){
                type = NavType.IntType
            }) ,
            enterTransition = {
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
            val index = it.arguments?.getInt("item")
            showBottomNav(false)
            CourseDetailScreen(viewModel = viewModel,navController = navController,index)
        }

        composable(HomeNavRoutes.EventMain.route, enterTransition = {
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
            showBottomNav(true)
            EventMainScreen(viewModel = viewModel, paddingValues = paddingValues, navController = navController)
        }
        composable(HomeNavRoutes.Internship.route, enterTransition = {
            slideIntoContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }, exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }) {
            showBottomNav(true)
            InternshipScreen(viewModel = viewModel, paddingValues=paddingValues,navController = navController)
        }
        composable(HomeNavRoutes.HandbookDetails.route+"/{item}",
            exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        arguments = listOf(navArgument("item"){
            type = NavType.IntType
        })
            ) {
            val index = it.arguments?.getInt("item")
            showBottomNav(false)
            HandbookDetailsScreen(viewModel = viewModel,navController = navController, handbookIndex = index!!)
        }



        composable(HomeNavRoutes.Youtube.route) {
            showBottomNav(false)
            YoutubePlayerScreen(paddingValues, viewModel)
        }
        composable(HomeNavRoutes.CampusActivities.route) {
            showBottomNav(false)
            CampusDetailScreen( viewModel, navController)
        }
        composable(HomeNavRoutes.Event.route) {
            showBottomNav(false)
            EventScreen( viewModel, navController)
        }

    }
}

