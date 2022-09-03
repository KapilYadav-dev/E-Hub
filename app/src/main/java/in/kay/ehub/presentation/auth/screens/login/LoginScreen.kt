package `in`.kay.ehub.presentation.auth.screens.login

import `in`.kay.ehub.R
import `in`.kay.ehub.data.datastore.UserDatastore
import `in`.kay.ehub.data.model.auth.UserSignInRequestDTO
import `in`.kay.ehub.domain.model.User
import `in`.kay.ehub.presentation.auth.components.*
import `in`.kay.ehub.presentation.auth.viewModels.LoginViewModel
import `in`.kay.ehub.presentation.navigation.auth.AuthNavRoutes
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Constants
import `in`.kay.ehub.utils.Utils
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {
    val context = LocalContext.current
    /*
     * Checking is login clicked if yes then making an api call
     */
    if (viewModel.isLoginClicked.value) LaunchedEffect(Unit) {
        viewModel.userSignIn(viewModel.userSignInRequestDTO.value)
    }
    /*
     * Checking is signup clicked if yes then navigating to signup screen
     */
    if (viewModel.isSignupClicked.value) LaunchedEffect(Unit) {
        navController.navigate(AuthNavRoutes.Register.route) {
            popUpTo(AuthNavRoutes.Auth.route)
        }
    }
    /*
     * Loading our user with flow and observing it's state
     */
    viewModel.user.value.let { it ->
        if (it.isLoading) {
            viewModel.isLoginClicked.value =false
            viewModel.isEnabled.value = false
            viewModel.isLoading.value = true
            viewModel.resetVariables()
        }
        if (it.error.isNotBlank()) {
            LaunchedEffect(key1 = Unit) {
                viewModel.isLoading.value = false
                Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
            }
        }

        it.data?.let {
            viewModel.isLoading.value = false
            viewModel.userData.value = it as User
            val userDatastore = UserDatastore(context)
            LaunchedEffect(Unit) {
                userDatastore.setUserLoggedIn(true)
                userDatastore.saveUser(viewModel.userData.value)
                navController.navigate(AuthNavRoutes.Home.route) {
                    popUpTo(AuthNavRoutes.Splash.route)
                }
            }
        }
    }
    /*
     * Setting our isEnabled state with other variables
     */
    viewModel.isEnabled.value =
        Utils.isValidEmail(viewModel.mEmail.value) && viewModel.mPassword.value.isNotBlank()

    BoxWithConstraints {
        if (viewModel.isLoading.value) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        } else {
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
                        viewModel.mEmail.value = it
                    },
                    "enter your email",
                    ValidateType.EMAIL,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)

                )
                EditText(
                    modifier = Modifier.layoutId("etPassword"),
                    strInput = {
                        viewModel.mPassword.value = it
                    },
                    "enter your password",
                    ValidateType.NONE,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                PrimaryButton(
                    text = "login",
                    isEnabled = viewModel.isEnabled.value,
                    roundedCorner = 4.dp,
                    modifier = Modifier.layoutId("btnLogin"),
                    onClick = {
                        viewModel.userSignInRequestDTO.value = UserSignInRequestDTO(
                            email = viewModel.mEmail.value.trim(),
                            password = viewModel.mPassword.value.trim(),
                        )
                        viewModel.isLoginClicked.value = true
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
                        viewModel.isSignupClicked.value = true
                    },
                    secondaryText = "didn't have an account?",
                    primaryText = "sign up"
                )
            }
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