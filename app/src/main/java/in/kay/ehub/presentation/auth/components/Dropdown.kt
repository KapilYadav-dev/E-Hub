package `in`.kay.ehub.presentation.auth.components

import `in`.kay.ehub.ui.theme.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import java.util.*

@Composable
fun Dropdown(
    list: List<Any>,
    selectedText: (String) -> Unit,
    modifier: Modifier,
    label: String,
    type: ValidateType,
) {
    var mExpanded by remember { mutableStateOf(false) }
    var mSelectedText by remember { mutableStateOf("") }
    var mTextFieldSize by remember { mutableStateOf(Size.Zero) }
    var isError by remember { mutableStateOf(false) }
    var errorMsg by remember {
        mutableStateOf("")
    }

    fun validate(text: String) {
        when (type) {
            ValidateType.COLLEGE -> {
                if( text.length < 3) {
                    isError = true
                    errorMsg = "please choose a valid college name"
                } else {
                    isError = false
                    errorMsg = ""
                }

            }
            ValidateType.BRANCH -> {
                if( text.length < 3) {
                    isError = true
                    errorMsg = "please choose a valid course name"
                } else {
                    isError = false
                    errorMsg = ""
                }
            }
            else -> {
                isError = false
                errorMsg = ""
            }
        }
    }

    val icon = if (mExpanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown
    Column(modifier = modifier) {
        OutlinedTextField(
            value = mSelectedText,
            onValueChange = {
                mSelectedText = it
                selectedText(it)
                validate(mSelectedText)
            },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    mTextFieldSize = coordinates.size.toSize()
                },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorBlack,
                unfocusedBorderColor = colorBorder
            ),
            label = {
                Text(
                    text = label.lowercase(),
                    style = Typography.body1,
                    color = colorSecondaryText
                )
            },
            singleLine = true,
            isError = isError,
            trailingIcon = {
                Icon(icon, "contentDescription",
                    Modifier.clickable { mExpanded = !mExpanded })
            }
        )
        Text(
            text = errorMsg,
            color = MaterialTheme.colors.error,
            style = MaterialTheme.typography.caption,
            fontFamily = gilroy(),
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
        DropdownMenu(
            expanded = mExpanded,
            onDismissRequest = { mExpanded = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { mTextFieldSize.width.toDp() })
        ) {
            list.forEach { label ->
                DropdownMenuItem(onClick = {
                    mSelectedText = label as String
                    selectedText(mSelectedText)
                    mExpanded = false
                }) {
                    Text(text = label.toString())
                    validate(label.toString())
                    selectedText(label.toString())
                }
            }
        }
    }

}
