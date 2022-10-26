package `in`.kay.ehub.presentation.auth.login

import `in`.kay.ehub.R
import `in`.kay.ehub.ui.theme.colorBorder
import `in`.kay.ehub.ui.theme.colorPrimary
import `in`.kay.ehub.ui.theme.colorWhite
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
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
                "Let's you in",
                modifier = Modifier.layoutId("tvHeader"),
                style = `in`.kay.ehub.ui.theme.Typography.h1
            )
            CustomButton("Continue with Google", modifier = Modifier.layoutId("btnGoogle"))
            CustomButton("Continue with Facebook", modifier = Modifier.layoutId("btnFacebook"))
        }
    }
}

@Composable
fun CustomButton(text: String, modifier: Modifier) {
    Button(
        onClick = { },
        modifier = modifier
            .fillMaxWidth()
            .background(colorWhite, RoundedCornerShape(16.dp))
            .border(
                1.dp, colorBorder,
                RoundedCornerShape(16.dp)
            ),
        colors = ButtonDefaults.buttonColors(backgroundColor = colorWhite)
    ) {
        Text(text, style = `in`.kay.ehub.ui.theme.Typography.body1)
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
}