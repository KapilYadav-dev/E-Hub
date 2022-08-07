package `in`.kay.ehub.presentation.auth.components


import `in`.kay.ehub.R
import `in`.kay.ehub.presentation.auth.components.ValidateType.*
import `in`.kay.ehub.ui.theme.*
import `in`.kay.ehub.utils.Utils.isValidEmail
import `in`.kay.ehub.utils.Utils.isValidPassword
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun EditText(modifier: Modifier, strInput: (String) -> Unit, strLabel: String, type: ValidateType,passwordToConfirm:String? = null) {
    var input by rememberSaveable {
        mutableStateOf("")
    }
    var isError by rememberSaveable { mutableStateOf(false) }

    var errorMsg by rememberSaveable {
        mutableStateOf("")
    }
    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    fun validate(text: String) {
        when (type) {
            EMAIL -> {
                if (!isValidEmail(text)) {
                    isError = true
                    errorMsg = "please enter correct email."
                } else {
                    isError = false
                    errorMsg = ""
                }
            }
            PASSWORD -> {
                if (!isValidPassword(text)) {
                    isError = true
                    errorMsg = "password length should more than 6 digits."
                } else {
                    isError = false
                    errorMsg = ""
                }
            }
            PHONE -> {
                if (text.length != 10) {
                    isError = true
                    errorMsg = "please enter valid phone number."
                } else {
                    isError = false
                    errorMsg = ""
                }
            }
            NONE -> {
                isError = false
                errorMsg = ""
            }
            NAME -> {
                if (text.isEmpty()) {
                    isError = true
                    errorMsg = "please enter name."
                } else {
                    isError = false
                    errorMsg = ""
                }
            }
            CONFIRM_PASSWORD -> {
                if (text != passwordToConfirm) {
                    isError = true
                    errorMsg = "password does not match."
                } else {
                    isError = false
                    errorMsg = ""
                }
            }
            COLLEGE -> TODO()
            BRANCH -> TODO()
        }
    }

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (type == NONE || type== PASSWORD) {
                if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation()
            } else VisualTransformation.None,
            trailingIcon = {
                if (isError && type != PASSWORD && type !=NONE)
                    Icon(
                        painter = painterResource(id = R.drawable.ic_error),
                        "error",
                        tint = MaterialTheme.colors.error
                    )
                if (type == NONE || type == PASSWORD) {
                    val image =
                        if (passwordVisible) painterResource(id = R.drawable.show) else painterResource(
                            id = R.drawable.hide
                        )
                    val description = if (passwordVisible) "Hide password" else "Show password"

                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(painter = image, description, Modifier.size(24.dp))
                    }
                }
            },
            value = input,
            singleLine = true,
            isError = isError,
            onValueChange = {
                input = it
                strInput(it)
                validate(input)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorBlack,
                unfocusedBorderColor = colorBorder
            ),
            label = {
                Text(
                    text = strLabel.lowercase(),
                    style = Typography.body1,
                    color = colorSecondaryText
                )
            },
        )
        Text(
            text = errorMsg,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            fontFamily = gilroy(),
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }

}

enum class ValidateType {
    EMAIL,
    PASSWORD,
    PHONE,
    NONE,
    COLLEGE,
    NAME,
    BRANCH,
    CONFIRM_PASSWORD
}