package `in`.kay.ehub.presentation.auth.signup

import `in`.kay.ehub.R
import `in`.kay.ehub.data.model.auth.UserSignUpRequestDTO
import `in`.kay.ehub.domain.model.User
import `in`.kay.ehub.presentation.auth.components.*
import `in`.kay.ehub.presentation.auth.viewModels.SignupViewModel
import `in`.kay.ehub.presentation.navigation.NavRoutes
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Constants
import `in`.kay.ehub.utils.Utils
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import dev.burnoo.compose.rememberpreference.rememberBooleanPreference


@Composable
fun SignUpScreen(
    navController: NavHostController,
    viewModel: SignupViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    /*
     * Loading our collegeList with flow and observing it's state
     */
    viewModel.collegeList.value.let { it ->
        if (it.error.isNotBlank()) {
            viewModel.isLoading.value = false
        }
        it.data?.let {
            viewModel.isLoading.value = false
            viewModel.mCollegeList.value = it as List<String>
        }
    }
    viewModel.branchList.value.let { it ->
        if (it.error.isNotBlank()) {
            viewModel.isLoading.value = false
        }
        it.data?.let {
            viewModel.isLoading.value = false
            viewModel.mBranchList.value = it as List<String>
        }
    }
    /*
     * Loading our user with flow and observing it's state
     */
    viewModel.user.value.let { it ->
        if (it.isLoading) {
            viewModel.isSignupClicked.value = false
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
            var isUserLoggedIn by rememberBooleanPreference(
                keyName = Constants.IS_USER_LOGGED_IN,
                initialValue = null,
                defaultValue = false,
            )
            isUserLoggedIn = true
            LaunchedEffect(isUserLoggedIn) {
                navController.navigate(NavRoutes.Home.route) {
                    popUpTo(NavRoutes.Splash.route)
                }
            }
        }
    }
    /*
     * Checking is login clicked if yes then navigating to login screen
     */
    if (viewModel.isLoginClicked.value) {
        LaunchedEffect(Unit) {
            navController.navigate(NavRoutes.Login.route) {
                popUpTo(NavRoutes.Auth.route)
            }
        }
    }
    /*
     * Checking is signup clicked if yes then navigating to signup screen
     */
    if (viewModel.isSignupClicked.value)
        LaunchedEffect(Unit) {
            viewModel.isEnabled.value = false
            viewModel.userSignUp(viewModel.userSignUpRequestDTO.value)
        }
    /*
     * Setting our isEnabled state with other variables
     */
    viewModel.isEnabled.value =
        Utils.isValidEmail(viewModel.mEmail.value) &&
                viewModel.mEmail.value.isNotEmpty() &&
                viewModel.mPassword.value.trim() == viewModel.mPassword.value.trim() &&
                viewModel.mUserName.value.isNotEmpty() &&
                viewModel.mMobile.value.length == 10 &&
                viewModel.mSelectedCollege.value.length > 3 &&
                viewModel.mSelectedBranch.value.length > 3
    /*
     * UI logic flows from here
     */
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
                    .verticalScroll(rememberScrollState())
                    .background(colorWhite)
            ) {
                Text(
                    text = "create your\naccount",
                    style = Typography.h1,
                    modifier = Modifier.layoutId("tvHeader")
                )
                EditText(
                    modifier = Modifier.layoutId("etUsername"),
                    strInput = {
                        viewModel.mUserName.value = it
                    },
                    "enter your username",
                    ValidateType.NAME,
                )
                EditText(
                    modifier = Modifier.layoutId("etEmail"),
                    strInput = {
                        viewModel.mEmail.value = it
                    },
                    "enter your email",
                    ValidateType.EMAIL,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                EditText(
                    modifier = Modifier.layoutId("etMobile"),
                    strInput = {
                        viewModel.mMobile.value = it
                    },
                    "enter your mobile",
                    ValidateType.PHONE,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Dropdown(
                    modifier = Modifier.layoutId("ddCollege"),
                    type = ValidateType.COLLEGE,
                    label = "please select your college",
                    list = viewModel.mCollegeList.value,
                    selectedText = {
                        viewModel.mSelectedCollege.value = it
                    })
                Dropdown(
                    modifier = Modifier.layoutId("ddBranch"),
                    type = ValidateType.BRANCH,
                    label = "please select your branch",
                    list = viewModel.mBranchList.value,
                    selectedText = {
                        viewModel.mSelectedBranch.value = it
                    })
                EditText(
                    modifier = Modifier.layoutId("etPassword"),
                    strInput = {
                        viewModel.mPassword.value = it
                    },
                    "enter your password",
                    ValidateType.PASSWORD,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                EditText(
                    modifier = Modifier.layoutId("etConfirmPassword"),
                    strInput = {
                        viewModel.mConfirmPassword.value = it
                    },
                    "confirm your password",
                    ValidateType.CONFIRM_PASSWORD,
                    viewModel.mPassword.value,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                PrimaryButton(
                    text = "join the community now",
                    isEnabled = viewModel.isEnabled.value,
                    roundedCorner = 4.dp,
                    modifier = Modifier.layoutId("btnLogin"),
                    onClick = {
                        viewModel.userSignUpRequestDTO.value = UserSignUpRequestDTO(
                            userName = viewModel.mUserName.value,
                            email = viewModel.mEmail.value.trim(),
                            password = viewModel.mPassword.value.trim(),
                            confirmPassword = viewModel.mPassword.value.trim(),
                            institutionName = viewModel.mSelectedCollege.value.trim(),
                            branch = viewModel.mSelectedBranch.value.trim(),
                            mobile = viewModel.mMobile.value.trim(),
                        )
                        viewModel.isSignupClicked.value = true
                    })
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
                    modifier = Modifier.layoutId("tvSignIn"),
                    onClick = {
                        viewModel.isLoginClicked.value = true
                    },
                    secondaryText = "already have an account?",
                    primaryText = "login here"
                )
            }
        }
    }
}

fun constrains() = ConstraintSet {
    val tvHeader = createRefFor("tvHeader")
    val etUsername = createRefFor("etUsername")
    val etEmail = createRefFor("etEmail")
    val etMobile = createRefFor("etMobile")
    val ddCollege = createRefFor("ddCollege")
    val ddBranch = createRefFor("ddBranch")
    val etPassword = createRefFor("etPassword")
    val etConfirmPassword = createRefFor("etConfirmPassword")
    val btnLogin = createRefFor("btnLogin")
    val divider = createRefFor("divider")
    val btnGoogle = createRefFor("btnGoogle")
    val btnFacebook = createRefFor("btnFacebook")
    val tvSignIn = createRefFor("tvSignIn")

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
    constrain(etEmail) {
        top.linkTo(etUsername.bottom)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(etMobile) {
        top.linkTo(etEmail.bottom)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(ddCollege) {
        top.linkTo(etMobile.bottom)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(ddBranch) {
        top.linkTo(ddCollege.bottom)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(etPassword) {
        top.linkTo(ddBranch.bottom)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(etConfirmPassword) {
        top.linkTo(etPassword.bottom)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(btnLogin) {
        top.linkTo(etConfirmPassword.bottom, 40.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        width = Dimension.matchParent
    }
    constrain(divider) {
        top.linkTo(btnLogin.bottom, 24.dp)
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
    constrain(tvSignIn) {
        top.linkTo(btnFacebook.bottom, 16.dp)
        start.linkTo(parent.start, 24.dp)
        end.linkTo(parent.end, 24.dp)
        bottom.linkTo(parent.bottom, 56.dp)
        width = Dimension.matchParent
    }
}