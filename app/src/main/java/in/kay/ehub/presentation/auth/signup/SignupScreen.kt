package `in`.kay.ehub.presentation.auth.signup

import `in`.kay.ehub.R
import `in`.kay.ehub.data.model.auth.UserSignUpRequestDTO
import `in`.kay.ehub.domain.model.User
import `in`.kay.ehub.presentation.auth.components.*
import `in`.kay.ehub.presentation.auth.viewModels.AuthViewModel
import `in`.kay.ehub.presentation.navigation.NavRoutes
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
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
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
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
fun SignUpScreen(navController: NavHostController, viewModel: AuthViewModel = hiltViewModel()) {
    val scope = rememberCoroutineScope()

    BoxWithConstraints {
        /*
         * Global variable declaration
         */
        var mUserName by rememberSaveable { mutableStateOf("") }
        var mEmail by rememberSaveable { mutableStateOf("") }
        var mPassword by rememberSaveable { mutableStateOf("") }
        var mConfirmPassword by rememberSaveable { mutableStateOf("") }
        var mCollegeList by rememberSaveable { mutableStateOf(emptyList<String>()) }
        var mBranchList by rememberSaveable { mutableStateOf(emptyList<String>()) }
        var mSelectedCollege by rememberSaveable { mutableStateOf("Ajay Kumar Garg Engineering College") }
        var mSelectedBranch by rememberSaveable { mutableStateOf("CSE") }
        var mMobile by rememberSaveable { mutableStateOf("") }
        var isLoading by rememberSaveable { mutableStateOf(false) }
        val context = LocalContext.current

        /*
         * user obtained from viewModel with 3 states
         * isLoading, error and data (success state)
         * Firstly our viewModel will fetch the college list and branch list from the server
         * and then we will update the state of their list @mCollegeList and @mBranchList and set it to ui
         */
        viewModel.collegeList.value.let { it ->
            if (it.error.isNotBlank()) {
                isLoading = false
                Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
            }
            it.data?.let {
                isLoading = false
                mCollegeList = it as List<String>
            }
        }

        viewModel.branchList.value.let { it ->
            if (it.error.isNotBlank()) {
                isLoading = false
                Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
            }
            it.data?.let {
                isLoading = false
                mBranchList = it as List<String>
            }
        }


        val user = viewModel.user.value
        if (user.isLoading) {
            isLoading = true
        }
        if (user.error.isNotBlank()) {
            isLoading = false
            Toast.makeText(context, user.error, Toast.LENGTH_LONG).show()
        }
        user.data?.let {
            LaunchedEffect(user.data) {
                navController.navigate(NavRoutes.Home.route) {
                    popUpTo(NavRoutes.Splash.route)
                }
            }
            isLoading = false
            val userData = it as User
        }

        if (isLoading) {
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
                        mUserName = it
                    },
                    "enter your username",
                    ValidateType.NAME,
                )
                EditText(
                    modifier = Modifier.layoutId("etEmail"),
                    strInput = {
                        mEmail = it
                    },
                    "enter your email",
                    ValidateType.EMAIL,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                )
                EditText(
                    modifier = Modifier.layoutId("etMobile"),
                    strInput = {
                        mMobile = it
                    },
                    "enter your mobile",
                    ValidateType.PHONE,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
                )
                EditText(
                    modifier = Modifier.layoutId("etPassword"),
                    strInput = {
                        mPassword = it
                    },
                    "enter your password",
                    ValidateType.PASSWORD,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                EditText(
                    modifier = Modifier.layoutId("etConfirmPassword"),
                    strInput = {
                        mConfirmPassword = it
                    },
                    "confirm your password",
                    ValidateType.CONFIRM_PASSWORD,
                    mPassword,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
                )
                PrimaryButton(
                    text = "join the community now",
                    isEnabled = Utils.isValidEmail(mEmail) && mPassword.isNotEmpty() && mConfirmPassword.trim() == mPassword.trim() && mUserName.isNotEmpty(), //&& mSelectedCollege.isNotEmpty() && mSelectedBranch.isNotEmpty(),
                    roundedCorner = 4.dp,
                    modifier = Modifier.layoutId("btnLogin"),
                    onClick = {
                        scope.apply {
                            val userSignInRequestDTO = UserSignUpRequestDTO(
                                userName = mUserName.trim(),
                                email = mEmail.trim(),
                                password = mPassword.trim(),
                                confirmPassword = mPassword.trim(),
                                institutionName = mSelectedCollege.trim(),
                                branch = mSelectedBranch.trim(),
                                mobile = mMobile.trim(),
                            )
                            viewModel.userSignUp(userSignInRequestDTO)
                        }
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
                        scope.apply {
                            navController.navigate(NavRoutes.Login.route)
                        }
                    },
                    secondaryText = "already have an account?",
                    primaryText = "login here"
                )
                /*
                 * TODO(#ADD COLLEGE AND BRANCH DROPDOWN)
                 */
            }
        }
    }
}

fun constrains() = ConstraintSet {
    val tvHeader = createRefFor("tvHeader")
    val etUsername = createRefFor("etUsername")
    val etEmail = createRefFor("etEmail")
    val etMobile = createRefFor("etMobile")
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
    constrain(etPassword) {
        top.linkTo(etMobile.bottom)
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