package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.domain.model.Handbook
import `in`.kay.ehub.domain.model.Mentors
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.utils.Utils
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import java.util.*

@Composable
fun MagazinesScreen(
    viewModel:HomeViewModel,
    navController:NavHostController
) {


    val isHandbookVisible = remember{
        mutableStateOf(false)
    }


    /*
     * Setting the mentor cards to UI
     */

    viewModel.handBookStateList.value.let {
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<Handbook>
                if (list.isNotEmpty()) {
                    isHandbookVisible.value = true
                }
                viewModel.handBookList.value = list
                Log.d("backdatatest", "Mentors(magazin): ${viewModel.handBookList}")
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
                text = "Magazines",
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

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            state = rememberLazyListState(),
            verticalArrangement = Arrangement.spacedBy(21.dp)
        ) {


            val mList = Utils.filterMagazines(viewModel)

            //change here too after filtering
            itemsIndexed(items = mList) { _, item ->
                HandbookCard(data = item)
            }
        }
    }
}