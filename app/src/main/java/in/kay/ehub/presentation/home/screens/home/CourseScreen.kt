package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.domain.model.Domains
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.presentation.lifecycle.rememberLifecycleEvent
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavHostController

@Composable
fun CourseScreen(viewModel: HomeViewModel, navController: NavHostController) {
    var isPgOne by remember {
        mutableStateOf(true)
    }
    var isPgTwo by remember {
        mutableStateOf(false)
    }
    var isPgThree by remember {
        mutableStateOf(false)
    }
    val lifecycle = rememberLifecycleEvent()
    LaunchedEffect(lifecycle) {
        if (lifecycle == Lifecycle.Event.ON_RESUME) {
            isPgOne = true
            isPgTwo = false
            isPgThree = false
        }
    }
    if (isPgOne) DomainPGOne()
    if (isPgTwo) DomainPGTwo()
    if (isPgThree) DomainPGThree()
}


@Composable
fun DomainPGThree() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorWhite)
    ) {

    }
}

@Composable
fun DomainPGTwo() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorWhite)
    ) {

    }
}

@Composable
fun DomainPGOne() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(colorWhite)
    ) {
        Text(text = "domains", style = `in`.kay.ehub.ui.theme.Typography.h1 )
    }
}

fun domainsList() = listOf(
    Domains(
        "Data Structures & Algorithms",
        "https://play-lh.googleusercontent.com/9zvNJHedNg_6lOdwcodODMVsyeHKxuTIpnbBzomRGGZAp_vKVXnd5SlF8XZcXyGYjQ"
    ),
    Domains("Web Development", "https://cdn-icons-png.flaticon.com/512/2210/2210153.png"),
    Domains(
        "App Development",
        "https://static.vecteezy.com/system/resources/previews/006/867/251/large_2x/service-cogwheels-inside-mobile-gradient-of-mobile-app-development-vector.jpg"
    ),
    Domains(
        "Machine Learning & AI",
        "https://cdn5.vectorstock.com/i/1000x1000/43/64/machine-learning-icon-artificial-intelligence-vector-35234364.jpg"
    ),
    Domains(
        "Cyber Security",
        "https://img.freepik.com/free-vector/global-data-security-personal-data-security-cyber-data-security-online-concept-illustration-internet-security-information-privacy-protection_1150-37375.jpg?w=200"
    ),
    Domains(
        "UI/UX Design",
        "https://img.freepik.com/free-vector/ui-ux-app-development-concept_52683-48848.jpg?w=200"
    ),
    Domains(
        "Block Chain",
        "https://img.freepik.com/free-psd/3d-nft-icon-chain_629802-28.jpg"
    ),
)