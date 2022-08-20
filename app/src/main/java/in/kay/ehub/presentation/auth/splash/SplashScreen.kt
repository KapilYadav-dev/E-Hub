package `in`.kay.ehub.presentation.auth.splash

import `in`.kay.ehub.R
import `in`.kay.ehub.presentation.navigation.NavRoutes
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Constants.IS_USER_LOGGED_IN
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import dev.burnoo.compose.rememberpreference.rememberBooleanPreference
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val isUserLoggedIn by rememberBooleanPreference(
        keyName = IS_USER_LOGGED_IN,
        initialValue = null,
        defaultValue = false,
    )
    var timerEnd by remember {
        mutableStateOf(false)
    }
    scope.launch {
        delay(1000L)
        timerEnd = true
    }
    if (timerEnd) {
        LaunchedEffect(Unit) {
            when (isUserLoggedIn) {
                null -> {}
                false -> {
                    navController.navigate(NavRoutes.Auth.route)
                }
                true -> {
                    navController.navigate(NavRoutes.Home.route)
                }
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorWhite),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_logo), contentDescription = "logo")
    }
}

