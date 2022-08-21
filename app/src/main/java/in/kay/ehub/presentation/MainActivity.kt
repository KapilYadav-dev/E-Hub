@file:OptIn(ExperimentalAnimationApi::class)

package `in`.kay.ehub.presentation

import `in`.kay.ehub.presentation.auth.auth.AuthLandingScreen
import `in`.kay.ehub.presentation.auth.login.LoginScreen
import `in`.kay.ehub.presentation.auth.signup.SignUpScreen
import `in`.kay.ehub.presentation.auth.splash.SplashScreen
import `in`.kay.ehub.presentation.home.HomeScreen
import `in`.kay.ehub.presentation.navigation.NavRoutes
import `in`.kay.ehub.ui.theme.EhubTheme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EhubTheme {
                val navController = rememberAnimatedNavController()

                AnimatedNavHost(
                    navController = navController,
                    startDestination = NavRoutes.Splash.route,
                ) {
                    composable(NavRoutes.Splash.route, exitTransition = {
                        slideOutOfContainer(
                            AnimatedContentScope.SlideDirection.Left,
                            animationSpec = tween(700)
                        )
                    }) {
                        SplashScreen(navController = navController)
                    }
                    composable(NavRoutes.Auth.route, enterTransition = {
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
                    composable(NavRoutes.Login.route, enterTransition = {
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
                    composable(NavRoutes.Register.route, enterTransition = {
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
                    composable(NavRoutes.Home.route, enterTransition = {
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
                        HomeScreen(navController = navController)
                    }
                }
            }
        }
    }
}
