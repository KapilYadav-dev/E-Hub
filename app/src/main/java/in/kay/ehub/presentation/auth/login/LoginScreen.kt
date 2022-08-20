package `in`.kay.ehub.presentation.auth.login

import `in`.kay.ehub.R
import `in`.kay.ehub.presentation.auth.components.*
import `in`.kay.ehub.presentation.navigation.NavRoutes
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Utils
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController) {

    var isLoginClicked by remember { mutableStateOf(false) }
    var isSignupClicked by remember { mutableStateOf(false) }

    if (isLoginClicked) LaunchedEffect(Unit) {
        navController.navigate(NavRoutes.Home.route) {
            popUpTo(NavRoutes.Splash.route)
        }
    }

    if (isSignupClicked) LaunchedEffect(Unit) {
        navController.navigate(NavRoutes.Register.route) {
            popUpTo(NavRoutes.Auth.route)
        }
    }

    BoxWithConstraints {
        var strEmail by rememberSaveable { mutableStateOf("") }
        var strPassword by rememberSaveable { mutableStateOf("") }
        ConstraintLayout(
            constrains(),
            modifier = Modifier
                .fillMaxSize()
                .background(colorWhite)
        ) {
            Text(
                text = "login to your\naccount",
                style = Typography.h1,
                modifier = Modifier.layoutId("tvHeader")
            )
            EditText(
                modifier = Modifier.layoutId("etUsername"),
                strInput = {
                    strEmail = it
                },
                "enter your email",
                ValidateType.EMAIL,
            )
            EditText(
                modifier = Modifier.layoutId("etPassword"),
                strInput = {
                    strPassword = it
                },
                "enter your password",
                ValidateType.NONE,
            )
            PrimaryButton(
                text = "login",
                isEnabled = Utils.isValidEmail(strEmail) && strPassword.isNotEmpty(),
                roundedCorner = 4.dp,
                modifier = Modifier.layoutId("btnLogin"),
                onClick = {
                    isLoginClicked = true
                })
            AuthClickableText(
                modifier = Modifier.layoutId("tvForgotPassword"),
                onClick = { },
                secondaryText = "forgot password?",
                primaryText = "reset now"
            )
            OrDivider(modifier = Modifier.layoutId("divider"))
            SecondaryButton(
                "continue with google",
                roundedCorner = 4.dp,
                modifier = Modifier.layoutId("btnGoogle"),
                onClick = {},
                painterResource(id = R.drawable.ic_google)
            )
            SecondaryButton(
                "continue with facebook",
                roundedCorner = 4.dp,
                modifier = Modifier.layoutId("btnFacebook"),
                onClick = {},
                painterResource(id = R.drawable.ic_facebook)
            )
            AuthClickableText(
                modifier = Modifier.layoutId("tvSignUp"),
                onClick = {
                    isSignupClicked = true
                },
                secondaryText = "didn't have an account?",
                primaryText = "sign up"
            )
        }
    }
}

fun constrains() = ConstraintSet {
    val tvHeader = createRefFor("tvHeader")
    val etUsername = createRefFor("etUsername")
    val etPassword = createRefFor("etPassword")
    val btnLogin = createRefFor("btnLogin")
    val tvForgotPassword = createRefFor("tvForgotPassword")
    val divider = createRefFor("divider")
    val btnGoogle = createRefFor("btnGoogle")
    val btnFacebook = createRefFor("btnFacebook")
    val tvSignUp = createRefFor("tvSignUp")

    constrain(tvHeader) {
        top.linkTo(parent.top, 32.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(etUsername) {
        top.linkTo(tvHeader.bottom, 56.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(etPassword) {
        top.linkTo(etUsername.bottom)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(btnLogin) {
        top.linkTo(etPassword.bottom, 40.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(tvForgotPassword) {
        top.linkTo(btnLogin.bottom, 16.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(divider) {
        top.linkTo(tvForgotPassword.bottom, 24.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(btnGoogle) {
        top.linkTo(divider.bottom, 40.dp)
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
    constrain(tvSignUp) {
        top.linkTo(btnFacebook.bottom, 16.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        bottom.linkTo(parent.bottom, 56.dp)
        width = Dimension.matchParent
        height = Dimension.fillToConstraints
    }
}