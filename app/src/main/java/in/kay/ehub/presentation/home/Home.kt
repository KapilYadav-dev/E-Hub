package `in`.kay.ehub.presentation.home

import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.presentation.navigation.home.BottomNavigationBar
import `in`.kay.ehub.presentation.navigation.home.HomeNavigationContainer
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Home(viewModel: HomeViewModel = hiltViewModel()) {

    val navController = rememberAnimatedNavController()
    var showBottomNav by remember { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorWhite),
        bottomBar = {
            if (showBottomNav) {
                BottomNavigationBar(navController = navController)
            }
        },
        content = { it ->
            HomeNavigationContainer(
                navController = navController,
                it,
                viewModel,
                showBottomNav = {
                    showBottomNav = it
                }
            )
        }
    )
}

