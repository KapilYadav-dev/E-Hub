package `in`.kay.ehub.presentation.home.components

import `in`.kay.ehub.ui.theme.Typography
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NoDataFoundComponent() {

        Column(Modifier.fillMaxSize().padding(bottom = 24.dp),verticalArrangement = Arrangement.Center) {
            Text(text = "No data available",
                style= Typography.body1,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center)
            Text(text="We'll notify you once something is here to show you",
                style= Typography.body2,
                modifier = Modifier.padding(horizontal = 70.dp),
                textAlign = TextAlign.Center,
            )
        }

}

@Preview(showBackground = true)
@Composable
fun nodataPrev() {
    Column(modifier = Modifier.fillMaxSize()) {

        NoDataFoundComponent()

    }
}