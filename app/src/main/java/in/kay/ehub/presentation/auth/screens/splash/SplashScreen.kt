package `in`.kay.ehub.presentation.auth.screens.splash

import `in`.kay.ehub.R
import `in`.kay.ehub.data.datastore.UserDatastore
import `in`.kay.ehub.presentation.navigation.auth.AuthNavRoutes
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var timerEnd by remember {
        mutableStateOf(false)
    }
    scope.launch {
        delay(1000L)
        timerEnd = true
    }
    if (timerEnd) {
        //Getting user loggedIn state using flow
        val user = UserDatastore(context).getUserLoggedIn()
        user.collectAsState(initial = null).value.let {
            LaunchedEffect(it) {
                when (it) {
                    true -> {
                        navController.navigate(AuthNavRoutes.Home.route)
                    }
                    false -> {
                        navController.navigate(AuthNavRoutes.Auth.route)
                    }
                    // This is the case when we don't have any use
                    null -> {}

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

