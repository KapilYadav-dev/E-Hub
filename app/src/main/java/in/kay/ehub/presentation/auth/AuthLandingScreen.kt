package `in`.kay.ehub.presentation.auth

import `in`.kay.ehub.R
import `in`.kay.ehub.presentation.auth.components.*
import `in`.kay.ehub.presentation.lifecycle.rememberLifecycleEvent
import `in`.kay.ehub.presentation.navigation.NavRoutes
import `in`.kay.ehub.ui.theme.colorWhite
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController

@Composable
fun AuthLandingScreen(navController: NavController) {
    val activity = (LocalContext.current as? Activity)
    var registerClicked by remember {
        mutableStateOf(false)
    }

    var loginClicked by remember {
        mutableStateOf(false)
    }

    var backClicked by remember {
        mutableStateOf(false)
    }

    val lifecycleEvent = rememberLifecycleEvent()
    /*
     * Handling our lifecycle events for onResume method.
     */
    LaunchedEffect(lifecycleEvent) {
        if (lifecycleEvent == Lifecycle.Event.ON_RESUME) {
            loginClicked = false
            registerClicked = false
            backClicked = false
        }
    }

    /*
     * Handling register button click and going to register screen
     */
    if (registerClicked)
        LaunchedEffect(Unit) {
            navController.navigate(NavRoutes.Register.route) {
                popUpTo(NavRoutes.Auth.route)
            }
        }

    /*
     * Handling login button click and going to login screen
     */
    if (loginClicked)
        LaunchedEffect(Unit) {
            navController.navigate(NavRoutes.Login.route) {
                popUpTo(NavRoutes.Auth.route)
            }
        }

    /*
     * BackPress handling for exit dialog
     */
    BackHandler {
        backClicked = true
    }

    /*
     * If backPressed then showing exit daig
     */
    if (backClicked) {
        AppDialog(
            modifier = Modifier.wrapContentWidth(),
            title = "Exit",
            message = "Are you sure you want to exit?",
            onDialogPositiveButtonClicked = {
                activity?.finish()
            },
            onDismissRequest = {
                backClicked = false
            })
    }

    BoxWithConstraints {
        ConstraintLayout(
            constrains(), modifier = Modifier
                .fillMaxSize()
                .background(colorWhite)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_header),
                contentDescription = "ivHeader",
                modifier = Modifier.layoutId("ivHeader")
            )
            Text(
                "let's you in",
                modifier = Modifier.layoutId("tvHeader"),
                style = `in`.kay.ehub.ui.theme.Typography.h1
            )
            SecondaryButton(
                "continue with google",
                modifier = Modifier.layoutId("btnGoogle"),
                onClick = {},
                painterResource = painterResource(id = R.drawable.ic_google)
            )
            SecondaryButton(
                "continue with facebook",
                modifier = Modifier.layoutId("btnFacebook"),
                onClick = {},
                painterResource = painterResource(id = R.drawable.ic_facebook)
            )
            OrDivider(modifier = Modifier.layoutId("divider"))
            PrimaryButton(
                "Sign in with password",
                modifier = Modifier.layoutId("btnSignIn"),
                onClick = {
                    loginClicked = true
                })
            AuthClickableText(
                modifier = Modifier.layoutId("tvSignUp"),
                onClick = {
                    registerClicked = true
                },
                "don't have an account?",
                "sign up"
            )
        }
    }
}

fun constrains() = ConstraintSet {
    val ivHeader = createRefFor("ivHeader")
    val tvHeader = createRefFor("tvHeader")
    val btnGoogle = createRefFor("btnGoogle")
    val btnFacebook = createRefFor("btnFacebook")
    val divider = createRefFor("divider")
    val btnSignIn = createRefFor("btnSignIn")
    val tvSkipForNow = createRefFor("tvSkipForNow")
    val tvSignUp = createRefFor("tvSignUp")

    constrain(ivHeader) {
        top.linkTo(parent.top, 120.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
    constrain(tvHeader) {
        top.linkTo(ivHeader.bottom, 32.dp)
        start.linkTo(parent.start)
        end.linkTo(parent.end)
    }
    constrain(btnGoogle) {
        top.linkTo(tvHeader.bottom, 18.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(btnFacebook) {
        top.linkTo(btnGoogle.bottom, 12.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(divider) {
        top.linkTo(btnFacebook.bottom, 40.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(btnSignIn) {
        top.linkTo(divider.bottom, 40.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(tvSignUp) {
        top.linkTo(btnSignIn.bottom, 18.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
}