package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.domain.model.News
import `in`.kay.ehub.presentation.auth.components.AppDialog
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorBlack
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Constants
import android.app.Activity
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController


@Composable
fun HomeScreen(
    navController: NavHostController,
    paddingValues: PaddingValues,
    viewModel: HomeViewModel
) {
    val context = LocalContext.current
    val activity = (LocalContext.current as? Activity)
    var backClicked by remember {
        mutableStateOf(false)
    }
    viewModel.newsStateList.value.let {
        if (it.isLoading) {
           LaunchedEffect(key1 = it.isLoading, block = {
               Toast.makeText(context, "Loading me", Toast.LENGTH_LONG).show()
           })
        }
        if (it.error.isNotBlank()) {
            LaunchedEffect(key1 = it.error.isNotBlank(), block = {
                Toast.makeText(context, it.error, Toast.LENGTH_LONG).show()
            })
        }
        it.data?.let {
            val newsList = it as List<News>
            viewModel.newsList.value = newsList
        }
    }
    if(backClicked) {
        AppDialog(
            modifier = Modifier.wrapContentWidth(),
            title = "Exit",
            message = "Are you sure you want to exit?",
            onDialogPositiveButtonClicked = {
                activity?.finish()
            },
            onDismissRequest = {
                backClicked = false
            })
    }
    BackHandler {
        backClicked = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorWhite)
            .padding(horizontal = 24.dp)
    ) {
        TopSection()
        Text(
            text = "let's learn together.",
            style = Typography.body1,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF282020),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        Text(
            text = "events",
            style = Typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp)
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(24.dp)
        ) {

        }
    }
}

@Composable
fun TopSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Hi, mrkaydev",
            style = Typography.body1,
            fontWeight = FontWeight.SemiBold,
            fontSize = 24.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "ic_notifications",
            tint = colorBlack,
            modifier = Modifier
                .weight(0.2f)
                .size(32.dp)
        )
        Icon(
            imageVector = Icons.Outlined.Person,
            contentDescription = "ic_profile",
            tint = colorBlack,
            modifier = Modifier
                .weight(0.2f)
                .size(32.dp)
        )
    }
}