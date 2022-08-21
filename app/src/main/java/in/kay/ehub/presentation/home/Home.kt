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

    val context = LocalContext.current
    val navController = rememberAnimatedNavController()
    viewModel.newsList.value.let {
        if (it.isLoading) {
            Toast.makeText(context, "Loading me", Toast.LENGTH_LONG).show()
        }
        if (it.error.isNotBlank()) {
            Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
        }
        it.data?.let {
            val newsList = it as List<News>
            Log.d(TAG, "HomeScreen: ${newsList.size}")
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorWhite),
        bottomBar = {
            BottomNavigationBar(navController = navController)
        },
        content = {
            HomeNavigationContainer(navController = navController,it)
        }
    )
}

