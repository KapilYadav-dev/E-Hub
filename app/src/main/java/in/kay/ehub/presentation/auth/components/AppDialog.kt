package `in`.kay.ehub.presentation.auth.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import java.util.*

@Composable
fun AppDialog(
    modifier: Modifier = Modifier,
    title: String,
    message: String,
    onDialogPositiveButtonClicked: (() -> Unit)? = null,
    onDismissRequest: (() -> Unit)? = null,
) {
    val textPaddingAll = 16.dp
    val buttonPaddingAll = 16.dp
    val dialogShape = RoundedCornerShape(16.dp)
    var dialogState by remember {
        mutableStateOf(true)
    }

    if (dialogState) {
        AlertDialog(
            onDismissRequest = {
                onDismissRequest?.invoke()
            },
            title = {
                Text(
                    text = title.lowercase(Locale.getDefault()),
                    style = `in`.kay.ehub.ui.theme.Typography.h1,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(textPaddingAll)
                )
            },
            text = {
                Text(
                    text = message.lowercase(Locale.getDefault()),
                    style = `in`.kay.ehub.ui.theme.Typography.body1,
                    modifier = Modifier.padding(textPaddingAll)
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(buttonPaddingAll),
                ) {
                    SecondaryButton(
                        //TODO("Remove a line showing in buttons")
                        text = "cancel",
                        modifier = modifier.weight(1f).height(48.dp),
                        onClick = {
                            onDismissRequest?.invoke()
                            dialogState = false
                        },
                        painterResource = null
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                    PrimaryButton(
                        //TODO("Remove a line showing in buttons")
                        text = "yes",
                        modifier = modifier.weight(1f).height(48.dp),
                        onClick = {
                            onDialogPositiveButtonClicked?.invoke()
                            dialogState = false
                        }
                    )
                }
            },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = false),
            modifier = modifier,
            shape = dialogShape
        )
    }
}