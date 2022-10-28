package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.Mentors
import `in`.kay.ehub.domain.model.Resources
import `in`.kay.ehub.presentation.auth.components.PrimaryButton
import `in`.kay.ehub.presentation.home.components.NoDataFoundComponent
import `in`.kay.ehub.presentation.home.components.ReadMoreText
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.ui.theme.Typography
import `in`.kay.ehub.ui.theme.colorWhite
import `in`.kay.ehub.utils.Utils
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import java.util.*

@Composable
fun MentorsScreen(viewModel: HomeViewModel,
                  navController: NavHostController) {

    val isMentorVisible = remember{
        mutableStateOf(false)
    }

    val uriHandler = LocalUriHandler.current
    /*
     * Setting the mentor cards to UI
     */

    viewModel.mentorsStateList.value.let {
        it.data?.let {
            LaunchedEffect(key1 = Unit, block = {
                val list = it as List<Mentors>
                if (list.isNotEmpty()) {
                    isMentorVisible.value = true
                }
                viewModel.mentorsList.value = list
                Log.d("backdatatest", "Mentors: ${viewModel.mentorsList}")
            })
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFFFFF))
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
                text = "Contact Mentors",
                fontSize = 27.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
                    .align(Alignment.Top),
                style = Typography.h1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }



        if(isMentorVisible.value) {

            val mList = Utils.filterMentors(viewModel)



            if(mList.isNotEmpty()) {
                Text(
                    text = "Ask?",
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .fillMaxWidth(),
                    style = Typography.h1
                )

                Text(
                    text = "Ask your Queries to the mentors !!!",
                    Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp), style = Typography.body2, fontSize = 16.sp
                )
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .padding(bottom = 16.dp),
                    state = rememberLazyListState(),
                    verticalArrangement = Arrangement.spacedBy(21.dp)
                ) {


                    itemsIndexed(items = mList) { index, item ->
                        MentorCard(item, onItemClick =
                        {
                            uriHandler.openUri(item.linkedinUrl)
                        }
                        )
                    }
                }
            }else{
                Column(Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center) {
                    NoDataFoundComponent()
                }
            }
        }else{
            Column(Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center) {
                NoDataFoundComponent()
            }
        }
    }


}



@Composable
fun MentorCard(data:Mentors,onItemClick:()->Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = 8.dp,
        backgroundColor = colorWhite
    ){
        
        Column(modifier = Modifier.padding(24.dp)) {
            Row{
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.mentorImage)
                        .error(R.drawable.defaultmentor)
                        .crossfade(true)
                        .build(),
                    contentDescription = "Mentor image",
                    modifier = Modifier
                        .size(72.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.size(20.dp))
                Column(modifier = Modifier.padding(top = 8.dp)){
                    Text(text = data.mentorName,
                        style = Typography.h1,
                        fontSize = 24.sp)
                    Spacer(modifier = Modifier.size(1.dp))
                    Text(text = data.position,
                        style = Typography.body1)
                }

            }
            ReadMoreText(text =  data.about,
                modifier = Modifier.padding(vertical = 8.dp),
                style = Typography.body2 )

            PrimaryButton(text = "Connect", color = Color(0xFF002A36),modifier = Modifier, onClick = {
                onItemClick()
            })
        }

    }
}