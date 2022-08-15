package `in`.kay.ehub.presentation.home

import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorWhite),
        bottomBar = {},
        topBar = {},
        content = {
            HomeScreenContent()
        }
    )
}

