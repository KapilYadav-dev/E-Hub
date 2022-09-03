package `in`.kay.ehub.presentation.navigation.auth

import `in`.kay.ehub.presentation.auth.screens.auth.AuthLandingScreen
import `in`.kay.ehub.presentation.auth.screens.login.LoginScreen
import `in`.kay.ehub.presentation.auth.screens.signup.SignUpScreen
import `in`.kay.ehub.presentation.auth.screens.splash.SplashScreen
import `in`.kay.ehub.presentation.home.Home
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AuthNavigationContainer() {
    val navController = rememberAnimatedNavController()
    AnimatedNavHost(
        navController = navController,
        startDestination = AuthNavRoutes.Splash.route,
    ) {
        composable(AuthNavRoutes.Splash.route, exitTransition = {
            slideOutOfContainer(
                AnimatedContentScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        }) {
            SplashScreen(navController = navController)
        }
        composable(AuthNavRoutes.Auth.route, enterTransition = {
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
            AuthLandingScreen(navController = navController)
        }
        composable(AuthNavRoutes.Login.route, enterTransition = {
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
            LoginScreen(navController = navController)
        }
        composable(AuthNavRoutes.Register.route, enterTransition = {
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
            SignUpScreen(navController = navController)
        }
        composable(AuthNavRoutes.Home.route, enterTransition = {
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
            Home()
        }
    }
}

