package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.domain.model.Courses
import `in`.kay.ehub.domain.model.Resources
import `in`.kay.ehub.presentation.auth.components.PrimaryButton
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.presentation.navigation.home.HomeNavRoutes
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorPrimary
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Utils
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.toLowerCase
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.util.*
import `in`.kay.ehub.utils.Utils.filterMagazines

@Composable
fun ResourcesScreen(
    viewModel: HomeViewModel,
    navController: NavHostController
) {
    val isResourceVisible = remember{
        mutableStateOf(false)
    }
    /*
     * Setting the resources cards to UI
     */

    viewModel.resourcesStateList.value.let {
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<Resources>
                if (list.isNotEmpty()) {
                    isResourceVisible.value = true
                }
                viewModel.resourcesList.value = list
                Log.d("backdatatest", "Resource: ${viewModel.coursesList}")
            })
        }
    }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFFFFFF)) //0xEEEEEEEE
                .padding(horizontal = 20.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "back-button",
                    Modifier
                        .size(36.dp)
                        .align(Alignment.CenterVertically)
                        .clickable(true) {
                            navController.popBackStack()
                        })
                Text(
                    text = "Resources",
                    fontSize = 27.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 8.dp)
                        .align(Alignment.Top),
                    style = Typography.h1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
            Text(
                text = "Resources",
                modifier = Modifier
                    .padding(top = 40.dp)
                    .fillMaxWidth(),
                style = Typography.h1
            )

            Text(
                text = "Best resources for DSA for learning and practicing !!",  //TODO: change subject(DSA) as per catagory from viewmodel,when we get domain data in API
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp), style = Typography.body2, fontSize = 14.sp
            )

            if(isResourceVisible.value) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(bottom = 16.dp),
                    state = rememberLazyListState(),
                    verticalArrangement = Arrangement.spacedBy(21.dp)
                ) {



                    val mList = Utils.filterResources(viewModel)

                    itemsIndexed(items = mList) { index, item ->
                        ResourceCard(item)
                    }
                }
            }
        }
    }


@Composable
fun ResourceCard(data:Resources) {
    val uriHandler = LocalUriHandler.current
    PrimaryResourceCard(text = data.resourceName, modifier = Modifier
        .fillMaxWidth(),
        color =  Color(0xFF002A36),
        onClick = {
            uriHandler.openUri(data.resourceLink)
    })

}

@Composable
fun PrimaryResourceCard(
    text: String,
    modifier: Modifier,
    isEnabled: Boolean = true,
    roundedCorner: Dp = 16.dp,
    onClick: () -> Unit,
    color: Color = colorPrimary
) {
    Button(
        onClick = { onClick() },
        enabled = isEnabled,
        modifier = modifier
            .clip(RoundedCornerShape(roundedCorner))
            .background(color = color),
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Text(
            text.lowercase(Locale.getDefault()),
            style = Typography.body1,
            fontWeight = FontWeight.SemiBold,
            color = colorWhite,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(vertical = 16.dp)
        )
    }
}