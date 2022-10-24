package `in`.kay.ehub.presentation.home.screens.home

import `in`.kay.ehub.R
import `in`.kay.ehub.domain.model.Handbook
import `in`.kay.ehub.presentation.auth.components.PrimaryButton
import `in`.kay.ehub.presentation.home.components.ReadMoreText
import `in`.kay.ehub.presentation.home.viewModels.HomeViewModel
import `in`.kay.ehub.ui.theme.Typography
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun HandbookDetailsScreen(
    viewModel: HomeViewModel,
    navController: NavHostController,
    handbookIndex:Int
) {
    val uriHandler = LocalUriHandler.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .padding(top = 16.dp, start = 24.dp, end = 24.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "back-button",
                Modifier
                    .size(36.dp)
                    .align(Alignment.CenterVertically)
                    .clickable(true) {
                        navController.popBackStack()
                    })
            Text(text = viewModel.handBookList.value[handbookIndex].bookTitle,
                fontSize=25.sp,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .fillMaxWidth()
                    .align(Alignment.Top),
                style = Typography.h1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

        }
        HandbookCard(data = viewModel.handBookList.value[handbookIndex])
        Column {
            SectionHeader(text = "About HandBook")
            ReadMoreText(modifier = Modifier
                .fillMaxWidth()
                .padding(start = 14.dp),
                text = viewModel.handBookList.value[handbookIndex].description,
                style = Typography.body1)
        }

        PrimaryButton(text = "Start Reading", modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 15.dp), color =  Color(0xFF002A36), onClick = {
            uriHandler.openUri(viewModel.handBookList.value[handbookIndex].pdfUrl)
//            Toast.makeText(context,"pdf Downloaded!",Toast.LENGTH_SHORT).show()
        })
    }
}


@Composable
fun HandbookCard(data: Handbook, onItemClick: (() -> Unit)? = null){

        Card(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .wrapContentHeight()
                .clickable {
                           if(onItemClick != null){
                               onItemClick()
                           }
                },
            shape = RoundedCornerShape(20.dp),
            elevation = 8.dp,
            backgroundColor = Color(0xFFFFFFFF),
        ) {
            Column {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.bookimgUrl)
                        .error(R.drawable.ic_no_book)
                        .crossfade(true)
                        .build(),
                    contentDescription = data.bookTitle,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 15.dp, vertical = 15.dp),
                    contentScale = ContentScale.Crop
                )
                Text(data.bookTitle,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    fontSize = 32.sp
                )
                Text(data.bookTagline,
                    modifier = Modifier.padding(horizontal = 20.dp),
                    style = Typography.body2
                )
                Spacer(Modifier.size(15.dp))

            }

        }


}