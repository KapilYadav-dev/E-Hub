package `in`.kay.ehub.presentation.auth.signup

import `in`.kay.ehub.R
import `in`.kay.ehub.presentation.auth.components.*
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Utils
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId

@Preview
@Composable
fun SignUpScreen() {
    BoxWithConstraints() {
        var mUserName by rememberSaveable { mutableStateOf("") }
        var mEmail by rememberSaveable { mutableStateOf("") }
        var mPassword by rememberSaveable { mutableStateOf("") }
        var mConfirmPassword by rememberSaveable { mutableStateOf("") }
        var mSelectedCollege by rememberSaveable { mutableStateOf("") }
        var mSelectedBranch by rememberSaveable { mutableStateOf("") }
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
            )
            EditText(
                modifier = Modifier.layoutId("etPassword"),
                strInput = {
                    mPassword = it
                },
                "enter your password",
                ValidateType.PASSWORD,
            )
            EditText(
                modifier = Modifier.layoutId("etConfirmPassword"),
                strInput = {
                    mConfirmPassword = it
                },
                "confirm your password",
                ValidateType.CONFIRM_PASSWORD,
                mPassword
            )
            PrimaryButton(
                text = "join the community now",
                isEnabled = Utils.isValidEmail(mEmail) && mPassword.isNotEmpty() && mConfirmPassword.trim() == mPassword.trim() && mUserName.isNotEmpty(), //&& mSelectedCollege.isNotEmpty() && mSelectedBranch.isNotEmpty(),
                roundedCorner = 4.dp,
                modifier = Modifier.layoutId("btnLogin"),
                onClick = {

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
                onClick = { },
                secondaryText = "didn't have an account?",
                primaryText = "sign up"
            )
            /*
             * TODO(#ADD COLLEGE AND BRANCH DROPDOWN)
             */
        }
    }
}

fun constrains() = ConstraintSet {
    val tvHeader = createRefFor("tvHeader")
    val etUsername = createRefFor("etUsername")
    val etEmail = createRefFor("etEmail")
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
    constrain(etPassword) {
        top.linkTo(etEmail.bottom)
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