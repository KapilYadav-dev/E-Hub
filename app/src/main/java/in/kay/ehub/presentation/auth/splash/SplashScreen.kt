package `in`.kay.ehub.presentation.auth.splash

import `in`.kay.ehub.R
import `in`.kay.ehub.presentation.navigation.NavRoutes
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(navController: NavHostController) {
    val scope = rememberCoroutineScope()
    scope.launch {
        delay(1000L)
        navController.navigate(NavRoutes.Auth.route)
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

