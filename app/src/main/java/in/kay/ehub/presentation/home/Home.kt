package `in`.kay.ehub.presentation.home

import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.presentation.navigation.home.BottomNavigationBar
import `in`.kay.ehub.presentation.navigation.home.HomeNavigationContainer
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Constants.TAG
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Home(viewModel: HomeViewModel = hiltViewModel()) {

    val navController = rememberAnimatedNavController()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorWhite),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        content = {
            HomeNavigationContainer(navController = navController,it,viewModel)
        }
    )
}

