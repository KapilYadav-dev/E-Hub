package `in`.kay.ehub.presentation.auth

import `in`.kay.ehub.R
import `in`.kay.ehub.presentation.auth.components.AuthClickableText
import `in`.kay.ehub.presentation.auth.components.OrDivider
import `in`.kay.ehub.presentation.auth.components.PrimaryButton
import `in`.kay.ehub.presentation.auth.components.SecondaryButton
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId

@Preview
@Composable
fun LoginScreen() {
    BoxWithConstraints() {
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
                onClick = {})
            AuthClickableText(
                modifier = Modifier.layoutId("tvSignUp"),
                onClick = {},
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